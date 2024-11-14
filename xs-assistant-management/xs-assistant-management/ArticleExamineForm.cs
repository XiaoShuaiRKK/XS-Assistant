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
using xs_assistant_management.Data.Enum;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service;
using xs_assistant_management.Service.Impl;

namespace xs_assistant_management
{
    public partial class ArticleExamineForm : Form
    {
        private Article article;
        private IArticleExamineService articleExamineService = ArticleExamineServiceImpl.Instance();

        public ArticleExamineForm(Article article)
        {
            this.article = article;
            InitializeComponent();
        }

        private void ArticleExamineForm_Load(object sender, EventArgs e)
        {
            this.Text = AssistantProjectData.projectName + $" - {this.Text}";
            loadContro();
            loadExamineHistory();
        }

        private void loadContro()
        {
            this.Examine_Content_richtxt.ReadOnly = true;
            this.Examine_Title_lbl.Text = article.Title;
            this.Examine_SubTitle_lbl.Text = article.SubTitle;
            this.Examine_Description_lbl.Text = article.Description;
            this.Examine_Content_richtxt.Text = article.Context;
        }

        private async void loadExamineHistory()
        {
            List<ArticleExamine> examines = await articleExamineService.GetArticleExamines(article.ArticleId);
            this.Examine_History_dvg.DataSource = examines;
        }

        private void Examine_Pass_bt_Click(object sender, EventArgs e)
        {
            sendExamine(ArticleStateEnum.VIOLATION);
        }

        private void Examine_Approve_bt_Click(object sender, EventArgs e)
        {
            sendExamine(ArticleStateEnum.NORMAL);
        }

        private async void sendExamine(ArticleStateEnum articleState)
        {
            string articleId = article.ArticleId;
            string description = this.Examine_Descripiton_richtxt.Text;
            bool isSuccess = await articleExamineService.addArticleExamine(articleId, (int)articleState, description);
            if (isSuccess)
            {
                MessageBox.Show("操作成功","提示",MessageBoxButtons.OK, MessageBoxIcon.Information);
                this.Dispose();
            }
        }
    }
}
