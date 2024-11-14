using System;
using System.Collections.Generic;
using System.Drawing.Design;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class ArticleState
    {

        private int id;
        private string stateName;

        public ArticleState(int id, string stateName)
        {
            this.Id = id;
            this.StateName = stateName;
        }

        [JsonPropertyName("id")]
        public int Id { get => id; set => id = value; }
        [JsonPropertyName("stateName")]
        public string StateName { get => stateName; set => stateName = value; }
    }
}
