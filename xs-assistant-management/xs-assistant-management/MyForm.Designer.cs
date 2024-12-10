
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
            this.My_OS_lbl = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // My_OS_lbl
            // 
            this.My_OS_lbl.AutoSize = true;
            this.My_OS_lbl.Location = new System.Drawing.Point(29, 26);
            this.My_OS_lbl.Name = "My_OS_lbl";
            this.My_OS_lbl.Size = new System.Drawing.Size(55, 15);
            this.My_OS_lbl.TabIndex = 0;
            this.My_OS_lbl.Text = "label1";
            // 
            // MyForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.My_OS_lbl);
            this.Name = "MyForm";
            this.Text = "MyForm";
            this.Load += new System.EventHandler(this.MyForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label My_OS_lbl;
    }
}