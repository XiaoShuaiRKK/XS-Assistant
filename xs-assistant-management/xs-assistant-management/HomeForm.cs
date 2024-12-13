using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web.UI.WebControls;
using System.Windows.Forms;
using xs_assistant_management.Data;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service;
using xs_assistant_management.Service.Impl;
using xs_assistant_management.Service.Util;

namespace xs_assistant_management
{
    public partial class HomeForm : Form
    {
        private bool isSetTag = false;
        private float X;
        private float Y;

        private IArticleSearchService articleSearchService = ArticleSearchServiceImpl.Instance();
        private ICustomerService customerService = CustomerServiceImpl.Instance();
        private Customer customer;
        private LoginForm loginForm;
        private List<Article> nowPageArticles;

        public HomeForm(Customer customer,LoginForm loginForm)
        {
            this.customer = customer;
            this.loginForm = loginForm;
            InitializeComponent();
        }

        /// <summary>
        /// 将控件的宽，高，左边距，顶边距和字体大小暂存到tag属性中
        /// </summary>
        /// <param name="control"></param>
        private void setTag(Control control) {
            foreach (Control item in control.Controls)
            {
                item.Tag = $"{item.Width}:{item.Height}:{item.Left}:{item.Top}:{item.Font.Size}";
                if (item.Controls.Count > 0)
                {
                    setTag(item);
                }
            }
            isSetTag = true;
        }

        private void setControls(float newx, float newy,Control cons)
        {
            foreach (Control con in cons.Controls)
            {

                string[] mytag = con.Tag.ToString().Split(new char[] { ':' });//获取控件的Tag属性值，并分割后存储字符串数组
                float a = System.Convert.ToSingle(mytag[0]) * newx;//根据窗体缩放比例确定控件的值，宽度
                con.Width = (int)a;//宽度
                a = System.Convert.ToSingle(mytag[1]) * newy;//高度
                con.Height = (int)(a);
                a = System.Convert.ToSingle(mytag[2]) * newx;//左边距离
                con.Left = (int)(a);
                a = System.Convert.ToSingle(mytag[3]) * newy;//上边缘距离
                con.Top = (int)(a);
                Single currentSize = System.Convert.ToSingle(mytag[4]) * newy;//字体大小
                con.Font = new Font(con.Font.Name, currentSize, con.Font.Style, con.Font.Unit);
                if (con.Controls.Count > 0)
                {
                    setControls(newx, newy, con);
                }
            }
        }

        private void setAutoForm()
        {
            X = this.Width;
            Y = this.Height;
            setTag(this);
        }

        private void HomeForm_Load(object sender, EventArgs e)
        {
            this.Text = AssistantProjectData.projectName + $" - {this.Text}";
            this.Home_Name_txt.Text = $"{customer.FirstName}  {customer.LastName}";
            this.Home_Icon_Image.ImageLocation = customer.IconPath;
            this.Home_Icon_Image.SizeMode = PictureBoxSizeMode.Zoom;
            this.Home_Check_Article_Menu.Enabled = false;
            setAutoForm();
            loadArticles();
            loadHomeControlr();
            loadDevice();
        }

        private void Home_login_out_Menu_Click(object sender, EventArgs e)
        {
            loginForm.Show();
            this.Dispose();
        }

        private void loadHomeControlr()
        {
            this.Home_Page_Num.Minimum = 1;
            this.Home_Page_Num.Controls[0].Visible = false;
        }

        private async void loadArticles()
        {
            List<Article> articles = await changeLoadDvg(1, 10);
            this.Home_Articles_dvg.ReadOnly = true;
            this.Home_Articles_dvg.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            this.Home_Articles_dvg.MultiSelect = false;
            this.Home_Articles_dvg.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            this.Home_Articles_dvg.RowHeadersVisible = false;
            if (articles != null)
            {
                this.Home_Articles_dvg.DataSource = articles;
            }
        }

        private async void loadDevice()
        {
            SystemInfo systemInfo = new SystemInfo(customer.IdNumber,ManagementUtil.GetOSInfo(),
                ManagementUtil.GetComputerName(),null,new DateTime(),0);
            await customerService.uploadDevice(systemInfo);
        }
        private async Task<List<Article>> changeLoadDvg(int page,int size)
        {
            Result<List<Article>> articles = await articleSearchService.GetArticlesAsync(page, size);
            return nowPageArticles = articles.Data;
        }

        private void HomeForm_Resize(object sender, EventArgs e)
        {
            float newx = (this.Width) / X;
            float newy = (this.Height) / Y;
            if (isSetTag)
            {
                setControls(newx, newy, this);
            }
        }

        private void Home_Page_Right_Click(object sender, EventArgs e)
        {
            decimal value = this.Home_Page_Num.Value;
            if(value + 1 <= this.Home_Page_Num.Maximum)
            {
                this.Home_Page_Num.Value += 1;
            }
        }

        private void Home_Page_Left_Click(object sender, EventArgs e)
        {
            decimal value = this.Home_Page_Num.Value;
            if(value - 1 >= this.Home_Page_Num.Minimum)
            {
                this.Home_Page_Num.Value -= 1;
            }
        }

        private async void Home_Page_Num_ValueChanged(object sender, EventArgs e)
        {
            int page = (int)this.Home_Page_Num.Value;
            List<Article> articles = await changeLoadDvg(page, 10);
            if(articles == null)
            {
                return;
            }
            if(articles.Count == 0)
            {
                this.Home_Page_Num.Value -= 1;
            }
            this.Home_Articles_dvg.DataSource = articles;
        }

        private void Home_Check_Article_Menu_Click(object sender, EventArgs e)
        {
            int index = Home_Articles_dvg.SelectedRows[0].Index;
            ArticleInfoForm articleInfoForm = new ArticleInfoForm(nowPageArticles[index]);
            articleInfoForm.ShowDialog();
        }

        private void Home_Articles_dvg_SelectionChanged(object sender, EventArgs e)
        {
            if (Home_Articles_dvg.SelectedRows.Count > 0)
            {
                this.Home_Check_Article_Menu.Enabled = true;
            }
        }

        private void HomeForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            Application.Exit();
        }

        private void Home_My_Device_Menu_Click(object sender, EventArgs e)
        {
            MyForm.Instance(customer.IdNumber).Show();
        }
    }
}
