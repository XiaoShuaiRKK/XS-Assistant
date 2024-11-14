namespace xs_assistant_management
{
    partial class ArticleExamineForm
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
            this.Examine_Content_richtxt = new System.Windows.Forms.RichTextBox();
            this.Examine_Pass_bt = new System.Windows.Forms.Button();
            this.Examine_Description_lbl = new System.Windows.Forms.Label();
            this.Examine_SubTitle_lbl = new System.Windows.Forms.Label();
            this.Examine_Title_lbl = new System.Windows.Forms.Label();
            this.Examine_d_lbl = new System.Windows.Forms.Label();
            this.Examine_Descripiton_richtxt = new System.Windows.Forms.RichTextBox();
            this.Examine_Approve_bt = new System.Windows.Forms.Button();
            this.Examine_History_dvg = new System.Windows.Forms.DataGridView();
            this.Examine_History_lbl = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.Examine_History_dvg)).BeginInit();
            this.SuspendLayout();
            // 
            // Examine_Content_richtxt
            // 
            this.Examine_Content_richtxt.Location = new System.Drawing.Point(449, 23);
            this.Examine_Content_richtxt.Name = "Examine_Content_richtxt";
            this.Examine_Content_richtxt.Size = new System.Drawing.Size(429, 496);
            this.Examine_Content_richtxt.TabIndex = 0;
            this.Examine_Content_richtxt.Text = "";
            // 
            // Examine_Pass_bt
            // 
            this.Examine_Pass_bt.BackColor = System.Drawing.Color.Red;
            this.Examine_Pass_bt.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Examine_Pass_bt.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.Examine_Pass_bt.Location = new System.Drawing.Point(218, 476);
            this.Examine_Pass_bt.Name = "Examine_Pass_bt";
            this.Examine_Pass_bt.Size = new System.Drawing.Size(136, 43);
            this.Examine_Pass_bt.TabIndex = 2;
            this.Examine_Pass_bt.Text = "驳回";
            this.Examine_Pass_bt.UseVisualStyleBackColor = false;
            this.Examine_Pass_bt.Click += new System.EventHandler(this.Examine_Pass_bt_Click);
            // 
            // Examine_Description_lbl
            // 
            this.Examine_Description_lbl.AutoSize = true;
            this.Examine_Description_lbl.Font = new System.Drawing.Font("宋体", 9F);
            this.Examine_Description_lbl.Location = new System.Drawing.Point(33, 105);
            this.Examine_Description_lbl.Name = "Examine_Description_lbl";
            this.Examine_Description_lbl.Size = new System.Drawing.Size(71, 12);
            this.Examine_Description_lbl.TabIndex = 5;
            this.Examine_Description_lbl.Text = "Description";
            // 
            // Examine_SubTitle_lbl
            // 
            this.Examine_SubTitle_lbl.AutoSize = true;
            this.Examine_SubTitle_lbl.Font = new System.Drawing.Font("宋体", 12F);
            this.Examine_SubTitle_lbl.Location = new System.Drawing.Point(33, 71);
            this.Examine_SubTitle_lbl.Name = "Examine_SubTitle_lbl";
            this.Examine_SubTitle_lbl.Size = new System.Drawing.Size(71, 16);
            this.Examine_SubTitle_lbl.TabIndex = 4;
            this.Examine_SubTitle_lbl.Text = "SubTitle";
            // 
            // Examine_Title_lbl
            // 
            this.Examine_Title_lbl.AutoSize = true;
            this.Examine_Title_lbl.Font = new System.Drawing.Font("宋体", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Examine_Title_lbl.Location = new System.Drawing.Point(32, 33);
            this.Examine_Title_lbl.Name = "Examine_Title_lbl";
            this.Examine_Title_lbl.Size = new System.Drawing.Size(64, 19);
            this.Examine_Title_lbl.TabIndex = 3;
            this.Examine_Title_lbl.Text = "Title";
            // 
            // Examine_d_lbl
            // 
            this.Examine_d_lbl.AutoSize = true;
            this.Examine_d_lbl.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Examine_d_lbl.Location = new System.Drawing.Point(33, 267);
            this.Examine_d_lbl.Name = "Examine_d_lbl";
            this.Examine_d_lbl.Size = new System.Drawing.Size(44, 12);
            this.Examine_d_lbl.TabIndex = 6;
            this.Examine_d_lbl.Text = "描述：";
            // 
            // Examine_Descripiton_richtxt
            // 
            this.Examine_Descripiton_richtxt.Location = new System.Drawing.Point(35, 305);
            this.Examine_Descripiton_richtxt.Name = "Examine_Descripiton_richtxt";
            this.Examine_Descripiton_richtxt.Size = new System.Drawing.Size(318, 129);
            this.Examine_Descripiton_richtxt.TabIndex = 7;
            this.Examine_Descripiton_richtxt.Text = "";
            // 
            // Examine_Approve_bt
            // 
            this.Examine_Approve_bt.Location = new System.Drawing.Point(35, 476);
            this.Examine_Approve_bt.Name = "Examine_Approve_bt";
            this.Examine_Approve_bt.Size = new System.Drawing.Size(137, 43);
            this.Examine_Approve_bt.TabIndex = 1;
            this.Examine_Approve_bt.Text = "通过";
            this.Examine_Approve_bt.UseVisualStyleBackColor = true;
            this.Examine_Approve_bt.Click += new System.EventHandler(this.Examine_Approve_bt_Click);
            // 
            // Examine_History_dvg
            // 
            this.Examine_History_dvg.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.Examine_History_dvg.Location = new System.Drawing.Point(33, 177);
            this.Examine_History_dvg.Name = "Examine_History_dvg";
            this.Examine_History_dvg.RowTemplate.Height = 23;
            this.Examine_History_dvg.Size = new System.Drawing.Size(320, 72);
            this.Examine_History_dvg.TabIndex = 8;
            // 
            // Examine_History_lbl
            // 
            this.Examine_History_lbl.AutoSize = true;
            this.Examine_History_lbl.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Examine_History_lbl.Location = new System.Drawing.Point(34, 150);
            this.Examine_History_lbl.Name = "Examine_History_lbl";
            this.Examine_History_lbl.Size = new System.Drawing.Size(70, 12);
            this.Examine_History_lbl.TabIndex = 9;
            this.Examine_History_lbl.Text = "审核记录：";
            // 
            // ArticleExamineForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(911, 569);
            this.Controls.Add(this.Examine_History_lbl);
            this.Controls.Add(this.Examine_History_dvg);
            this.Controls.Add(this.Examine_Descripiton_richtxt);
            this.Controls.Add(this.Examine_d_lbl);
            this.Controls.Add(this.Examine_Description_lbl);
            this.Controls.Add(this.Examine_SubTitle_lbl);
            this.Controls.Add(this.Examine_Title_lbl);
            this.Controls.Add(this.Examine_Pass_bt);
            this.Controls.Add(this.Examine_Approve_bt);
            this.Controls.Add(this.Examine_Content_richtxt);
            this.Name = "ArticleExamineForm";
            this.Text = "ArticleExamineForm";
            this.Load += new System.EventHandler(this.ArticleExamineForm_Load);
            ((System.ComponentModel.ISupportInitialize)(this.Examine_History_dvg)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RichTextBox Examine_Content_richtxt;
        private System.Windows.Forms.Button Examine_Pass_bt;
        private System.Windows.Forms.Label Examine_Description_lbl;
        private System.Windows.Forms.Label Examine_SubTitle_lbl;
        private System.Windows.Forms.Label Examine_Title_lbl;
        private System.Windows.Forms.Label Examine_d_lbl;
        private System.Windows.Forms.RichTextBox Examine_Descripiton_richtxt;
        private System.Windows.Forms.Button Examine_Approve_bt;
        private System.Windows.Forms.DataGridView Examine_History_dvg;
        private System.Windows.Forms.Label Examine_History_lbl;
    }
}