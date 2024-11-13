namespace xs_assistant_management
{
    partial class AuthorInfoForm
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
            this.Author_Icon = new System.Windows.Forms.PictureBox();
            this.Author_Name_lbl = new System.Windows.Forms.Label();
            this.Author_Birth_lbl = new System.Windows.Forms.Label();
            this.Author_Area_lbl = new System.Windows.Forms.Label();
            this.Author_Id_lbl = new System.Windows.Forms.Label();
            this.Author_Articles_dvg = new System.Windows.Forms.DataGridView();
            ((System.ComponentModel.ISupportInitialize)(this.Author_Icon)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.Author_Articles_dvg)).BeginInit();
            this.SuspendLayout();
            // 
            // Author_Icon
            // 
            this.Author_Icon.Location = new System.Drawing.Point(41, 30);
            this.Author_Icon.Name = "Author_Icon";
            this.Author_Icon.Size = new System.Drawing.Size(130, 130);
            this.Author_Icon.TabIndex = 0;
            this.Author_Icon.TabStop = false;
            // 
            // Author_Name_lbl
            // 
            this.Author_Name_lbl.AutoSize = true;
            this.Author_Name_lbl.Font = new System.Drawing.Font("宋体", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Author_Name_lbl.Location = new System.Drawing.Point(209, 43);
            this.Author_Name_lbl.Name = "Author_Name_lbl";
            this.Author_Name_lbl.Size = new System.Drawing.Size(142, 21);
            this.Author_Name_lbl.TabIndex = 1;
            this.Author_Name_lbl.Text = "Author Name";
            // 
            // Author_Birth_lbl
            // 
            this.Author_Birth_lbl.AutoSize = true;
            this.Author_Birth_lbl.Location = new System.Drawing.Point(210, 139);
            this.Author_Birth_lbl.Name = "Author_Birth_lbl";
            this.Author_Birth_lbl.Size = new System.Drawing.Size(35, 12);
            this.Author_Birth_lbl.TabIndex = 2;
            this.Author_Birth_lbl.Text = "birth";
            // 
            // Author_Area_lbl
            // 
            this.Author_Area_lbl.AutoSize = true;
            this.Author_Area_lbl.Font = new System.Drawing.Font("宋体", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Author_Area_lbl.Location = new System.Drawing.Point(210, 111);
            this.Author_Area_lbl.Name = "Author_Area_lbl";
            this.Author_Area_lbl.Size = new System.Drawing.Size(35, 13);
            this.Author_Area_lbl.TabIndex = 3;
            this.Author_Area_lbl.Text = "Area";
            // 
            // Author_Id_lbl
            // 
            this.Author_Id_lbl.AutoSize = true;
            this.Author_Id_lbl.Font = new System.Drawing.Font("宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Author_Id_lbl.Location = new System.Drawing.Point(209, 83);
            this.Author_Id_lbl.Name = "Author_Id_lbl";
            this.Author_Id_lbl.Size = new System.Drawing.Size(79, 13);
            this.Author_Id_lbl.TabIndex = 4;
            this.Author_Id_lbl.Text = "Id Number";
            // 
            // Author_Articles_dvg
            // 
            this.Author_Articles_dvg.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Author_Articles_dvg.Location = new System.Drawing.Point(41, 191);
            this.Author_Articles_dvg.Name = "Author_Articles_dvg";
            this.Author_Articles_dvg.RowTemplate.Height = 23;
            this.Author_Articles_dvg.Size = new System.Drawing.Size(781, 339);
            this.Author_Articles_dvg.TabIndex = 5;
            // 
            // AuthorInfoForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(866, 551);
            this.Controls.Add(this.Author_Articles_dvg);
            this.Controls.Add(this.Author_Id_lbl);
            this.Controls.Add(this.Author_Area_lbl);
            this.Controls.Add(this.Author_Birth_lbl);
            this.Controls.Add(this.Author_Name_lbl);
            this.Controls.Add(this.Author_Icon);
            this.Name = "AuthorInfoForm";
            this.Text = "AuthorInfoForm";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.AuthorInfoForm_FormClosed);
            this.Load += new System.EventHandler(this.AuthorInfoForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.Author_Icon)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.Author_Articles_dvg)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox Author_Icon;
        private System.Windows.Forms.Label Author_Name_lbl;
        private System.Windows.Forms.Label Author_Birth_lbl;
        private System.Windows.Forms.Label Author_Area_lbl;
        private System.Windows.Forms.Label Author_Id_lbl;
        private System.Windows.Forms.DataGridView Author_Articles_dvg;
    }
}