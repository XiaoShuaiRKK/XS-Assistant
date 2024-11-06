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
            this.Home_Articles_dvg = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Icon_Image)).BeginInit();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Articles_dvg)).BeginInit();
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
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.Home_login_out_Menu});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(970, 25);
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
            // Home_Articles_dvg
            // 
            this.Home_Articles_dvg.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Home_Articles_dvg.Location = new System.Drawing.Point(12, 93);
            this.Home_Articles_dvg.Name = "Home_Articles_dvg";
            this.Home_Articles_dvg.RowTemplate.Height = 23;
            this.Home_Articles_dvg.Size = new System.Drawing.Size(929, 378);
            this.Home_Articles_dvg.TabIndex = 3;
            // 
            // HomeForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(970, 499);
            this.Controls.Add(this.Home_Articles_dvg);
            this.Controls.Add(this.Home_Icon_Image);
            this.Controls.Add(this.Home_Name_txt);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "HomeForm";
            this.Text = "Home";
            this.Load += new System.EventHandler(this.HomeForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.Home_Icon_Image)).EndInit();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.Home_Articles_dvg)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label Home_Name_txt;
        private System.Windows.Forms.PictureBox Home_Icon_Image;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem Home_login_out_Menu;
        private System.Windows.Forms.DataGridView Home_Articles_dvg;
    }
}