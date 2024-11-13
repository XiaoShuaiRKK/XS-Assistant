using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class Article
    {
        string id;
        string context;
        string title;
        string subTitle;
        string authorId;
        string authorName;
        string stateName;
        string image;
        string background;
        string description;

        public Article(string id, string context, string title, string subTitle,
            string authorId, string authorName, string stateName,
            string image, string background, string description)
        {
            this.id = id;
            this.context = context;
            this.title = title;
            this.subTitle = subTitle;
            this.authorId = authorId;
            this.authorName = authorName;
            this.StateName = stateName;
            this.image = image;
            this.background = background;
            this.description = description;
        }

        [JsonPropertyName("id")]
        public string Id { get => id; set => id = value; }
        [JsonPropertyName("context")]
        public string Context { get => context; set => context = value; }
        [JsonPropertyName("title")]
        public string Title { get => title; set => title = value; }
        [JsonPropertyName("subTitle")]
        public string SubTitle { get => subTitle; set => subTitle = value; }
        [JsonPropertyName("authorId")]
        public string AuthorId { get => authorId; set => authorId = value; }
        [JsonPropertyName("image")]
        public string Image { get => image; set => image = value; }
        [JsonPropertyName("background")]
        public string Background { get => background; set => background = value; }
        [JsonPropertyName("description")]
        public string Description { get => description; set => description = value; }
        [JsonPropertyName("authorName")]
        public string AuthorName { get => authorName; set => authorName = value; }
        [JsonPropertyName("stateName")]
        public string StateName { get => stateName; set => stateName = value; }
    }
}
