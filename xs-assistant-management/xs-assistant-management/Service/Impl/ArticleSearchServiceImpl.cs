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
    internal class ArticleSearchServiceImpl : IArticleSearchService
    {
        private ArticleSearchServiceImpl(){}
        private static IArticleSearchService instance;
        static readonly object lockObject = new object();

        private string baseUrl = "/article/search";

        public static IArticleSearchService Instance()
        {
            if(instance != null)
            {
                return instance;
            }
            lock (lockObject)
            {
                if (instance != null)
                {
                    return instance;
                }
                instance = new ArticleSearchServiceImpl();
            }
            return instance;
        }
        public async Task<Result<List<Article>>> GetArticlesAsync(int page, int size)
        {
            string url = AssistantProjectData.baseUrl + this.baseUrl + $"/get/page?page={page}&size={size}";
            string json = await HttpUtil.get(url);
            Result<List<Article>> articles = JsonUtil.jsonToBean<Result<List<Article>>>(json);
            List<Article> result = ResultMessageUtil.CheckData(articles);
            return articles;
        }

        public async Task<List<Article>> GetArticlesByIdNumber(string idNumber,int page, int size)
        {
            string url = AssistantProjectData.baseUrl + this.baseUrl + $"/get/by/idNumber?id_number={idNumber}&page={page}&size={size}";
            string json = await HttpUtil.get(url);
            return ResultMessageUtil.GetData<List<Article>>(json);
        }

    }
}
