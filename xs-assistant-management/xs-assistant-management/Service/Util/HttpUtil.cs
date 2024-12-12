using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace xs_assistant_management.Service.Util
{
    public static class HttpUtil
    {
        private static readonly HttpClient client = new HttpClient();

        public static void addDafaultHeaders(KeyValuePair<string,string> header)
        {
            client.DefaultRequestHeaders.Add(header.Key, header.Value);
        }

        public static async Task<string> get(string url)
        {
            return await client.GetStringAsync(url);
        }

        public static async Task<string> post(string url, params KeyValuePair<string,string>[] param){
            string json = "";
            try
            {
                Dictionary<string, string> values = new Dictionary<string, string>();
                foreach (KeyValuePair<string, string> kvp in param)
                {
                    values.Add(kvp.Key, kvp.Value);
                }
                FormUrlEncodedContent content = new FormUrlEncodedContent(values);
                HttpResponseMessage reponse = await client.PostAsync(url, content);
                json = await reponse.Content.ReadAsStringAsync();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            return json;
        }

        public static async Task<string> postByJson<T>(string url,T obj)
        {
            string json = "";
            try
            {
                string paramsJson = JsonUtil.beanToJson(obj);
                StringContent content = new StringContent(paramsJson,Encoding.UTF8,"application/json");
                HttpResponseMessage reponse = await client.PostAsync(url, content);
                json = await reponse.Content.ReadAsStringAsync();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            return json;
        }
    }
}
