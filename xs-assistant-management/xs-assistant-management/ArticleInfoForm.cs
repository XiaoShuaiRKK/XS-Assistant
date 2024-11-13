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

namespace xs_assistant_management
{
    public partial class ArticleInfoForm : Form
    {
        private Article article;
        public ArticleInfoForm(Article article)
        {
            this.article = article;
            InitializeComponent();
        }

        private void ArticleInfoForm_Load(object sender, EventArgs e)
        {
            this.Text = AssistantProjectData.projectName + $" - {this.Text}";
            this.Article_content_richTextBox.ReadOnly = true;
            this.Article_Author_Linklbl.LinkBehavior = LinkBehavior.NeverUnderline;
            articlesDataLoad();
        }

        private void articlesDataLoad()
        {
            this.Article_Title_lbl.Text = article.Title;
            this.Article_SubTitle_lbl.Text = article.SubTitle;
            this.Article_Description_lbl.Text = article.Description;
            this.Article_Author_Linklbl.Text = article.AuthorName;
            this.Article_content_richTextBox.Text = article.Context;
            this.Article_State_lbl.Text = article.StateName;
        }

        private void Article_Author_Linklbl_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            AuthorInfoForm authorInfo = AuthorInfoForm.Instance(article.AuthorId);
            authorInfo.Show();
        }
    }
}
