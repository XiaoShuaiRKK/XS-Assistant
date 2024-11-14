using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using xs_assistant_management.Data.POJO;

namespace xs_assistant_management.Service
{
    public interface IArticleExamineService
    {
        Task<Boolean> addArticleExamine(string articleId, int examineState, string description);
        Task<List<ArticleExamine>> GetArticleExamines(string articleId);
    }
}
