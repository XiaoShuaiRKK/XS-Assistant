using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using xs_assistant_management.Data;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service;
using xs_assistant_management.Service.Impl;

namespace xs_assistant_management
{
    public partial class HomeForm : Form
    {
        private IArticleSearchService articleSearchService = ArticleSearchServiceImpl.Instance();
        private Customer customer;
        private LoginForm loginForm;

        public HomeForm(Customer customer,LoginForm loginForm)
        {
            this.customer = customer;
            this.loginForm = loginForm;
            InitializeComponent();
        }

        private void HomeForm_Load(object sender, EventArgs e)
        {
            this.Text = AssistantProjectData.projectName + $" - {this.Text}";
            this.Home_Name_txt.Text = $"{customer.FirstName}  {customer.LastName}";
            this.Home_Icon_Image.ImageLocation = customer.IconPath;
            this.Home_Icon_Image.SizeMode = PictureBoxSizeMode.Zoom;
            loadArticles();
        }

        private void Home_login_out_Menu_Click(object sender, EventArgs e)
        {
            loginForm.Show();
            this.Dispose();
        }

        private async void loadArticles()
        {
            Result<List<Article>> articles = await articleSearchService.GetArticlesAsync(1, 10);
            this.Home_Articles_dvg.DataSource = articles;
        }
    }
}
