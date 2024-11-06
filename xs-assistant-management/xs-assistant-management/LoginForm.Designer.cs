namespace xs_assistant_management
{
    partial class LoginForm
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
            this.login_password_tb = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.login_login_bt = new System.Windows.Forms.Button();
            this.Login_username_cmb = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // login_password_tb
            // 
            this.login_password_tb.Font = new System.Drawing.Font("宋体", 12F);
            this.login_password_tb.Location = new System.Drawing.Point(128, 226);
            this.login_password_tb.Name = "login_password_tb";
            this.login_password_tb.Size = new System.Drawing.Size(279, 26);
            this.login_password_tb.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(39, 163);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(83, 12);
            this.label1.TabIndex = 2;
            this.label1.Text = "User/Email ：";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(39, 233);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(65, 12);
            this.label2.TabIndex = 3;
            this.label2.Text = "Password :";
            // 
            // login_login_bt
            // 
            this.login_login_bt.Location = new System.Drawing.Point(201, 318);
            this.login_login_bt.Name = "login_login_bt";
            this.login_login_bt.Size = new System.Drawing.Size(75, 23);
            this.login_login_bt.TabIndex = 4;
            this.login_login_bt.Text = "Login";
            this.login_login_bt.UseVisualStyleBackColor = true;
            this.login_login_bt.Click += new System.EventHandler(this.login_login_bt_Click);
            // 
            // Login_username_cmb
            // 
            this.Login_username_cmb.Font = new System.Drawing.Font("宋体", 12F);
            this.Login_username_cmb.FormattingEnabled = true;
            this.Login_username_cmb.Location = new System.Drawing.Point(128, 156);
            this.Login_username_cmb.Name = "Login_username_cmb";
            this.Login_username_cmb.Size = new System.Drawing.Size(279, 24);
            this.Login_username_cmb.TabIndex = 5;
            // 
            // LoginForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(518, 421);
            this.Controls.Add(this.Login_username_cmb);
            this.Controls.Add(this.login_login_bt);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.login_password_tb);
            this.Name = "LoginForm";
            this.Text = "Login";
            this.Load += new System.EventHandler(this.LoginForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.TextBox login_password_tb;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button login_login_bt;
        private System.Windows.Forms.ComboBox Login_username_cmb;
    }
}