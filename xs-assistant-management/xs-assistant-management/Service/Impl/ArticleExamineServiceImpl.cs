using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using xs_assistant_management.Data;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service.Util;

namespace xs_assistant_management.Service.Impl
{
    public class ArticleExamineServiceImpl : IArticleExamineService
    {
        private string baseUrl = "/article/examine";

        private static ArticleExamineServiceImpl instance;
        private ArticleExamineServiceImpl() { }
        public static ArticleExamineServiceImpl Instance()
        {
            if (instance == null)
            {
                instance = new ArticleExamineServiceImpl();
            }
            return instance;
        }

        public async Task<bool> addArticleExamine(string articleId, int examineState, string description)
        {
            string url = AssistantProjectData.baseUrl + baseUrl + "/add";
            KeyValuePair<string, string> articleIdParam = new KeyValuePair<string, string>("article_id", articleId)
                , descriptionParam = new KeyValuePair<string, string>("description", description);
            KeyValuePair<string, string> examineParam = new KeyValuePair<string, string>("examine_state", examineState.ToString());
            string json = await HttpUtil.post(url, articleIdParam, descriptionParam, examineParam);
            Console.WriteLine(json);
            return ResultMessageUtil.GetData<bool>(json);
        }

        public async Task<List<ArticleExamine>> GetArticleExamines(string articleId)
        {
            string url = AssistantProjectData.baseUrl + this.baseUrl + $"/get/byArticleId?article_id={articleId}";
            string json = await HttpUtil.get(url);
            return ResultMessageUtil.GetData<List<ArticleExamine>>(json);
        }
    }
}
