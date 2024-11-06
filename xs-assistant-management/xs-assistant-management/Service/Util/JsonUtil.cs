using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace xs_assistant_management.Service.Util
{
    public static class JsonUtil
    {
        public static T jsonToBean<T>(string json)
        {
            JsonSerializerOptions options = new JsonSerializerOptions();
            options.PropertyNameCaseInsensitive = true;
            return JsonSerializer.Deserialize<T>(json,options);
        }

        public static string beanToJson<T>(T bean)
        {
            return JsonSerializer.Serialize(bean);
        }
    }
}
