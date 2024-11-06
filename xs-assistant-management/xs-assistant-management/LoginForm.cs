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
    public partial class LoginForm : Form
    {
        private ILoginService loginService = LoginServiceImpl.GetInstance();

        public LoginForm()
        {
            InitializeComponent();
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {
            this.Text = AssistantProjectData.projectName + $" - {this.Text}";
            this.login_password_tb.PasswordChar = '*';
            loadLogged();
        }

        private async void login_login_bt_Click(object sender, EventArgs e)
        {
            string username = Login_username_cmb.Text.Replace("\r\n","").Trim();
            string password = login_password_tb.Text.Replace("\r\n", "").Trim();
            login_password_tb.Text = "";
            Result<LoginData> customer = await loginService.login(username, password);
            if (customer == null) {
                MessageBox.Show(customer.Message);
            }
            if (customer.Data.Customer.Level <= 1)
            {
                MessageBox.Show("登录成功");
                HomeForm homeForm = new HomeForm(customer.Data.Customer,this);
                this.Hide();
                homeForm.Show();
            }
            else
            {
                MessageBox.Show("权限不足");
            }
        }

        private void loadLogged()
        {
            string loggedInfo = loginService.loggedData();
            if(loggedInfo != null)
            {
                this.Login_username_cmb.Items.Add(loggedInfo);
            }
        }
    }
}
