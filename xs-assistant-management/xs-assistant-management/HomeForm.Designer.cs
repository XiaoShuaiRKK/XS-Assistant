namespace xs_assistant_management
{
    partial class HomeForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.Home_Name_txt = new System.Windows.Forms.Label();
            this.Home_Icon_Image = new System.Windows.Forms.PictureBox();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.Home_login_out_Menu = new System.Windows.Forms.ToolStripMenuItem();
            this.Home_Check_Article_Menu = new System.Windows.Forms.ToolStripMenuItem();
            this.Home_My_Device_Menu = new System.Windows.Forms.ToolStripMenuItem();
            this.Home_Articles_dvg = new System.Windows.Forms.DataGridView();
            this.Home_Page_Num = new System.Windows.Forms.NumericUpDown();
            this.Home_Page_Right = new System.Windows.Forms.Label();
            this.Home_Page_Left = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Icon_Image)).BeginInit();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Articles_dvg)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Page_Num)).BeginInit();
            this.SuspendLayout();
            // 
            // Home_Name_txt
            // 
            this.Home_Name_txt.AutoSize = true;
            this.Home_Name_txt.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Home_Name_txt.Location = new System.Drawing.Point(140, 43);
            this.Home_Name_txt.Name = "Home_Name_txt";
            this.Home_Name_txt.Size = new System.Drawing.Size(43, 16);
            this.Home_Name_txt.TabIndex = 0;
            this.Home_Name_txt.Text = "Name";
            // 
            // Home_Icon_Image
            // 
            this.Home_Icon_Image.Location = new System.Drawing.Point(12, 28);
            this.Home_Icon_Image.Name = "Home_Icon_Image";
            this.Home_Icon_Image.Size = new System.Drawing.Size(100, 50);
            this.Home_Icon_Image.TabIndex = 1;
            this.Home_Icon_Image.TabStop = false;
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.Home_login_out_Menu,
            this.Home_Check_Article_Menu,
            this.Home_My_Device_Menu});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Padding = new System.Windows.Forms.Padding(4, 2, 0, 2);
            this.menuStrip1.Size = new System.Drawing.Size(955, 25);
            this.menuStrip1.TabIndex = 2;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // Home_login_out_Menu
            // 
            this.Home_login_out_Menu.Name = "Home_login_out_Menu";
            this.Home_login_out_Menu.Size = new System.Drawing.Size(68, 21);
            this.Home_login_out_Menu.Text = "退出登录";
            this.Home_login_out_Menu.Click += new System.EventHandler(this.Home_login_out_Menu_Click);
            // 
            // Home_Check_Article_Menu
            // 
            this.Home_Check_Article_Menu.Name = "Home_Check_Article_Menu";
            this.Home_Check_Article_Menu.Size = new System.Drawing.Size(68, 21);
            this.Home_Check_Article_Menu.Text = "查看文章";
            this.Home_Check_Article_Menu.Click += new System.EventHandler(this.Home_Check_Article_Menu_Click);
            // 
            // Home_My_Device_Menu
            // 
            this.Home_My_Device_Menu.Name = "Home_My_Device_Menu";
            this.Home_My_Device_Menu.Size = new System.Drawing.Size(68, 21);
            this.Home_My_Device_Menu.Text = "我的设备";
            this.Home_My_Device_Menu.TextDirection = System.Windows.Forms.ToolStripTextDirection.Horizontal;
            this.Home_My_Device_Menu.Click += new System.EventHandler(this.Home_My_Device_Menu_Click);
            // 
            // Home_Articles_dvg
            // 
            this.Home_Articles_dvg.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Home_Articles_dvg.Location = new System.Drawing.Point(12, 93);
            this.Home_Articles_dvg.Name = "Home_Articles_dvg";
            this.Home_Articles_dvg.RowHeadersWidth = 51;
            this.Home_Articles_dvg.RowTemplate.Height = 23;
            this.Home_Articles_dvg.Size = new System.Drawing.Size(929, 378);
            this.Home_Articles_dvg.TabIndex = 3;
            this.Home_Articles_dvg.SelectionChanged += new System.EventHandler(this.Home_Articles_dvg_SelectionChanged);
            // 
            // Home_Page_Num
            // 
            this.Home_Page_Num.Location = new System.Drawing.Point(696, 57);
            this.Home_Page_Num.Name = "Home_Page_Num";
            this.Home_Page_Num.Size = new System.Drawing.Size(45, 21);
            this.Home_Page_Num.TabIndex = 4;
            this.Home_Page_Num.ValueChanged += new System.EventHandler(this.Home_Page_Num_ValueChanged);
            // 
            // Home_Page_Right
            // 
            this.Home_Page_Right.AutoSize = true;
            this.Home_Page_Right.Location = new System.Drawing.Point(757, 61);
            this.Home_Page_Right.Name = "Home_Page_Right";
            this.Home_Page_Right.Size = new System.Drawing.Size(11, 12);
            this.Home_Page_Right.TabIndex = 5;
            this.Home_Page_Right.Text = ">";
            this.Home_Page_Right.Click += new System.EventHandler(this.Home_Page_Right_Click);
            // 
            // Home_Page_Left
            // 
            this.Home_Page_Left.AutoSize = true;
            this.Home_Page_Left.Location = new System.Drawing.Point(670, 61);
            this.Home_Page_Left.Name = "Home_Page_Left";
            this.Home_Page_Left.Size = new System.Drawing.Size(11, 12);
            this.Home_Page_Left.TabIndex = 6;
            this.Home_Page_Left.Text = "<";
            this.Home_Page_Left.Click += new System.EventHandler(this.Home_Page_Left_Click);
            // 
            // HomeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(955, 482);
            this.Controls.Add(this.Home_Page_Left);
            this.Controls.Add(this.Home_Page_Right);
            this.Controls.Add(this.Home_Page_Num);
            this.Controls.Add(this.Home_Articles_dvg);
            this.Controls.Add(this.Home_Icon_Image);
            this.Controls.Add(this.Home_Name_txt);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "HomeForm";
            this.Text = "Home";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.HomeForm_FormClosed);
            this.Load += new System.EventHandler(this.HomeForm_Load);
            this.Resize += new System.EventHandler(this.HomeForm_Resize);
            ((System.ComponentModel.ISupportInitialize)(this.Home_Icon_Image)).EndInit();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Articles_dvg)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Page_Num)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label Home_Name_txt;
        private System.Windows.Forms.PictureBox Home_Icon_Image;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem Home_login_out_Menu;
        private System.Windows.Forms.DataGridView Home_Articles_dvg;
        private System.Windows.Forms.NumericUpDown Home_Page_Num;
        private System.Windows.Forms.Label Home_Page_Right;
        private System.Windows.Forms.Label Home_Page_Left;
        private System.Windows.Forms.ToolStripMenuItem Home_Check_Article_Menu;
        private System.Windows.Forms.ToolStripMenuItem Home_My_Device_Menu;
    }
}