using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service;
using xs_assistant_management.Service.Impl;
using xs_assistant_management.Service.Util;

namespace xs_assistant_management
{
    public partial class MyForm : Form
    {
        private ICustomerService customerService = CustomerServiceImpl.Instance();
        Customer customer;
        string idNumber;

        private static MyForm instance;
        private MyForm(string idNumber)
        {
            this.idNumber = idNumber;
            InitializeComponent();
        }

        public static MyForm Instance(string idNumber)
        {
            if (instance == null)
            {
               instance = new MyForm(idNumber);
            }
            return instance;
        }

        private async void MyForm_Load(object sender, EventArgs e)
        {
            customer = await customerService.GetCustomer(this.idNumber);
            this.My_Icon_Img.ImageLocation = customer.IconPath;
            this.My_Icon_Img.SizeMode = PictureBoxSizeMode.Zoom;
            this.My_Name_lbl.Text = customer.FirstName + " " + customer.LastName;
            this.My_Email_lbl.Text = customer.Email;
            this.My_Id_Number_lbl.Text = customer.IdNumber;
            loadDevices();
        }

        //private void loadCustomer(string idNumber)
        //{
        //    customer = .Result;
        //}

        private void loadDevices()
        {
            My_FLow_Layout_Panel.AutoScroll = true;
            My_FLow_Layout_Panel.FlowDirection = FlowDirection.BottomUp;
            int baseHeignt = My_FLow_Layout_Panel.Height;
            int baseWidth = My_FLow_Layout_Panel.Width;
            for (int i = 0; i < 5; i++)
            {
                Button b = new Button();
                b.Height = baseHeignt - 100;
                b.Width = baseWidth - 100;
                b.Text = i.ToString();
                b.Location = new Point(32, 20 + i * 150);
                My_FLow_Layout_Panel.Controls.Add(b);
            }
        }

        private void MyForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            instance = null;
        }
    }
}
