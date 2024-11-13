namespace xs_assistant_management
{
    partial class ArticleInfoForm
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
            this.Article_Title_lbl = new System.Windows.Forms.Label();
            this.Article_SubTitle_lbl = new System.Windows.Forms.Label();
            this.Article_Description_lbl = new System.Windows.Forms.Label();
            this.Article_content_richTextBox = new System.Windows.Forms.RichTextBox();
            this.Article_State_lbl = new System.Windows.Forms.Label();
            this.Article_Author_Linklbl = new System.Windows.Forms.LinkLabel();
            this.SuspendLayout();
            // 
            // Article_Title_lbl
            // 
            this.Article_Title_lbl.AutoSize = true;
            this.Article_Title_lbl.Font = new System.Drawing.Font("宋体", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Article_Title_lbl.Location = new System.Drawing.Point(21, 9);
            this.Article_Title_lbl.Name = "Article_Title_lbl";
            this.Article_Title_lbl.Size = new System.Drawing.Size(64, 19);
            this.Article_Title_lbl.TabIndex = 0;
            this.Article_Title_lbl.Text = "Title";
            // 
            // Article_SubTitle_lbl
            // 
            this.Article_SubTitle_lbl.AutoSize = true;
            this.Article_SubTitle_lbl.Font = new System.Drawing.Font("宋体", 12F);
            this.Article_SubTitle_lbl.Location = new System.Drawing.Point(22, 42);
            this.Article_SubTitle_lbl.Name = "Article_SubTitle_lbl";
            this.Article_SubTitle_lbl.Size = new System.Drawing.Size(71, 16);
            this.Article_SubTitle_lbl.TabIndex = 1;
            this.Article_SubTitle_lbl.Text = "SubTitle";
            // 
            // Article_Description_lbl
            // 
            this.Article_Description_lbl.AutoSize = true;
            this.Article_Description_lbl.Font = new System.Drawing.Font("宋体", 9F);
            this.Article_Description_lbl.Location = new System.Drawing.Point(23, 73);
            this.Article_Description_lbl.Name = "Article_Description_lbl";
            this.Article_Description_lbl.Size = new System.Drawing.Size(71, 12);
            this.Article_Description_lbl.TabIndex = 2;
            this.Article_Description_lbl.Text = "Description";
            // 
            // Article_content_richTextBox
            // 
            this.Article_content_richTextBox.Location = new System.Drawing.Point(25, 103);
            this.Article_content_richTextBox.Name = "Article_content_richTextBox";
            this.Article_content_richTextBox.Size = new System.Drawing.Size(720, 308);
            this.Article_content_richTextBox.TabIndex = 4;
            this.Article_content_richTextBox.Text = "";
            // 
            // Article_State_lbl
            // 
            this.Article_State_lbl.AutoSize = true;
            this.Article_State_lbl.Font = new System.Drawing.Font("宋体", 10F);
            this.Article_State_lbl.Location = new System.Drawing.Point(657, 42);
            this.Article_State_lbl.Name = "Article_State_lbl";
            this.Article_State_lbl.Size = new System.Drawing.Size(42, 14);
            this.Article_State_lbl.TabIndex = 5;
            this.Article_State_lbl.Text = "State";
            // 
            // Article_Author_Linklbl
            // 
            this.Article_Author_Linklbl.AutoSize = true;
            this.Article_Author_Linklbl.Font = new System.Drawing.Font("宋体", 10F, System.Drawing.FontStyle.Bold);
            this.Article_Author_Linklbl.LinkColor = System.Drawing.Color.Black;
            this.Article_Author_Linklbl.Location = new System.Drawing.Point(627, 73);
            this.Article_Author_Linklbl.Name = "Article_Author_Linklbl";
            this.Article_Author_Linklbl.Size = new System.Drawing.Size(87, 14);
            this.Article_Author_Linklbl.TabIndex = 6;
            this.Article_Author_Linklbl.TabStop = true;
            this.Article_Author_Linklbl.Text = "AuthorName";
            this.Article_Author_Linklbl.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.Article_Author_Linklbl_LinkClicked);
            // 
            // ArticleInfoForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.Article_Author_Linklbl);
            this.Controls.Add(this.Article_State_lbl);
            this.Controls.Add(this.Article_content_richTextBox);
            this.Controls.Add(this.Article_Description_lbl);
            this.Controls.Add(this.Article_SubTitle_lbl);
            this.Controls.Add(this.Article_Title_lbl);
            this.Name = "ArticleInfoForm";
            this.Text = "ArticleInfoForm";
            this.Load += new System.EventHandler(this.ArticleInfoForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label Article_Title_lbl;
        private System.Windows.Forms.Label Article_SubTitle_lbl;
        private System.Windows.Forms.Label Article_Description_lbl;
        private System.Windows.Forms.RichTextBox Article_content_richTextBox;
        private System.Windows.Forms.Label Article_State_lbl;
        private System.Windows.Forms.LinkLabel Article_Author_Linklbl;
    }
}