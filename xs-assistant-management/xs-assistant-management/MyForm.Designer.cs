
namespace xs_assistant_management
{
    partial class MyForm
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
            this.My_Machine_lbl = new System.Windows.Forms.Label();
            this.My_FLow_Layout_Panel = new System.Windows.Forms.FlowLayoutPanel();
            this.My_Icon_Img = new System.Windows.Forms.PictureBox();
            this.My_Name_lbl = new System.Windows.Forms.Label();
            this.My_Id_Number_lbl = new System.Windows.Forms.Label();
            this.My_Email_lbl = new System.Windows.Forms.Label();
            this.My_Level_Progress_Bar = new System.Windows.Forms.ProgressBar();
            ((System.ComponentModel.ISupportInitialize)(this.My_Icon_Img)).BeginInit();
            this.SuspendLayout();
            // 
            // My_Machine_lbl
            // 
            this.My_Machine_lbl.AutoSize = true;
            this.My_Machine_lbl.Location = new System.Drawing.Point(22, 106);
            this.My_Machine_lbl.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.My_Machine_lbl.Name = "My_Machine_lbl";
            this.My_Machine_lbl.Size = new System.Drawing.Size(47, 12);
            this.My_Machine_lbl.TabIndex = 1;
            this.My_Machine_lbl.Text = "Devices";
            // 
            // My_FLow_Layout_Panel
            // 
            this.My_FLow_Layout_Panel.Location = new System.Drawing.Point(24, 121);
            this.My_FLow_Layout_Panel.Name = "My_FLow_Layout_Panel";
            this.My_FLow_Layout_Panel.Size = new System.Drawing.Size(606, 266);
            this.My_FLow_Layout_Panel.TabIndex = 3;
            // 
            // My_Icon_Img
            // 
            this.My_Icon_Img.Location = new System.Drawing.Point(24, 12);
            this.My_Icon_Img.Name = "My_Icon_Img";
            this.My_Icon_Img.Size = new System.Drawing.Size(80, 74);
            this.My_Icon_Img.TabIndex = 4;
            this.My_Icon_Img.TabStop = false;
            // 
            // My_Name_lbl
            // 
            this.My_Name_lbl.AutoSize = true;
            this.My_Name_lbl.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.My_Name_lbl.Location = new System.Drawing.Point(122, 22);
            this.My_Name_lbl.Name = "My_Name_lbl";
            this.My_Name_lbl.Size = new System.Drawing.Size(43, 16);
            this.My_Name_lbl.TabIndex = 5;
            this.My_Name_lbl.Text = "Name";
            // 
            // My_Id_Number_lbl
            // 
            this.My_Id_Number_lbl.AutoSize = true;
            this.My_Id_Number_lbl.Location = new System.Drawing.Point(123, 74);
            this.My_Id_Number_lbl.Name = "My_Id_Number_lbl";
            this.My_Id_Number_lbl.Size = new System.Drawing.Size(59, 12);
            this.My_Id_Number_lbl.TabIndex = 6;
            this.My_Id_Number_lbl.Text = "id number";
            // 
            // My_Email_lbl
            // 
            this.My_Email_lbl.AutoSize = true;
            this.My_Email_lbl.Location = new System.Drawing.Point(123, 52);
            this.My_Email_lbl.Name = "My_Email_lbl";
            this.My_Email_lbl.Size = new System.Drawing.Size(35, 12);
            this.My_Email_lbl.TabIndex = 7;
            this.My_Email_lbl.Text = "Email";
            // 
            // My_Level_Progress_Bar
            // 
            this.My_Level_Progress_Bar.Location = new System.Drawing.Point(217, 22);
            this.My_Level_Progress_Bar.Name = "My_Level_Progress_Bar";
            this.My_Level_Progress_Bar.Size = new System.Drawing.Size(413, 23);
            this.My_Level_Progress_Bar.TabIndex = 8;
            // 
            // MyForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(660, 399);
            this.Controls.Add(this.My_Level_Progress_Bar);
            this.Controls.Add(this.My_Email_lbl);
            this.Controls.Add(this.My_Id_Number_lbl);
            this.Controls.Add(this.My_Name_lbl);
            this.Controls.Add(this.My_Icon_Img);
            this.Controls.Add(this.My_FLow_Layout_Panel);
            this.Controls.Add(this.My_Machine_lbl);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "MyForm";
            this.Text = "MyForm";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.MyForm_FormClosed);
            this.Load += new System.EventHandler(this.MyForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.My_Icon_Img)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label My_Machine_lbl;
        private System.Windows.Forms.FlowLayoutPanel My_FLow_Layout_Panel;
        private System.Windows.Forms.PictureBox My_Icon_Img;
        private System.Windows.Forms.Label My_Name_lbl;
        private System.Windows.Forms.Label My_Id_Number_lbl;
        private System.Windows.Forms.Label My_Email_lbl;
        private System.Windows.Forms.ProgressBar My_Level_Progress_Bar;
    }
}