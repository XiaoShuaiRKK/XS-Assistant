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
            string url = AssistantProjectData.baseUrl + $"/article/search/get/page?page={page}&size={size}";
            string json = await HttpUtil.get(url);
            Console.WriteLine(json);
            Result<List<Article>> articles = JsonUtil.jsonToBean<Result<List<Article>>>(json);
            return articles;
        }

        public Task<Result<List<Article>>> GetArticlesByIdNumber(string idNumber)
        {
            throw new NotImplementedException();
        }
    }
}
