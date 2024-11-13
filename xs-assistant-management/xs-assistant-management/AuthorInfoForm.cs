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
    public partial class AuthorInfoForm : Form
    {
        private string idNumber;

        private static AuthorInfoForm instance;
        public static AuthorInfoForm Instance(string idNumber)
        {
            if (instance == null)
            {
                instance = new AuthorInfoForm(idNumber);
            }
            return instance;
        }

        ICustomerService customerService = CustomerServiceImpl.Instance();
        private Customer customer;

        private AuthorInfoForm(string idNumber)
        {
            this.idNumber = idNumber;
            InitializeComponent();
        }

        private void AuthorInfoForm_Load(object sender, EventArgs e)
        {
            this.Text = AssistantProjectData.projectName + $" - {this.Text}";
            loadAuthorData();
        }

        private async void loadAuthorData()
        {
            customer = await customerService.GetCustomer(this.idNumber);
            this.Author_Icon.ImageLocation = customer.IconPath;
            this.Author_Icon.SizeMode = PictureBoxSizeMode.Zoom;
            this.Author_Name_lbl.Text = customer.FirstName + " - " + customer.LastName;
            this.Author_Id_lbl.Text = customer.IdNumber;
            this.Author_Area_lbl.Text = customer.AreaId.ToString();
            this.Author_Birth_lbl.Text = customer.Birth.ToString();
        }

        private void AuthorInfoForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            instance = null;
        }
    }
}
