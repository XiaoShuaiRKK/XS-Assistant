using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class ArticleExamine
    {
        int id;
        string articleId;
        int examineState;
        string examineName;
        string description;
        DateTime createTime;
        DateTime updateTime;

        public ArticleExamine(int id, string articleId, int examineState, string examineName,
            string description, DateTime createTime, DateTime updateTime)
        {
            this.Id = id;
            this.ArticleId = articleId;
            this.ExamineState = examineState;
            this.ExamineName = examineName;
            this.Description = description;
            this.CreateTime = createTime;
            this.UpdateTime = updateTime;
        }

        [JsonPropertyName("id")]
        public int Id { get => id; set => id = value; }
        [JsonPropertyName("articleId")]
        public string ArticleId { get => articleId; set => articleId = value; }
        [JsonPropertyName("examineState")]
        public int ExamineState { get => examineState; set => examineState = value; }
        [JsonPropertyName("examineName")]
        public string ExamineName { get => examineName; set => examineName = value; }
        [JsonPropertyName("description")]
        public string Description { get => description; set => description = value; }
        [JsonPropertyName("createTime")]
        public DateTime CreateTime { get => createTime; set => createTime = value; }
        [JsonPropertyName("updateTime")]
        public DateTime UpdateTime { get => updateTime; set => updateTime = value; }
    }
}
