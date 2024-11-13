using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Windows.Forms;
using xs_assistant_management.Data;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service.Util;

namespace xs_assistant_management.Service.Impl
{
    internal class LoginServiceImpl : ILoginService
    {
        private readonly string _userfile = Application.StartupPath + "\\Resources\\userdata.txt";

        private LoginServiceImpl() { }
        private static ILoginService instance;
        static readonly object lockObject = new object();

        public static ILoginService GetInstance()
        {
            if(instance != null)
            {
                return instance;
            }
            lock (lockObject)
            {
                if(instance != null)
                {
                    return instance;
                }
                instance = new LoginServiceImpl();
            }
            return instance;
        }

        public async Task<Result<LoginData>> login(string username, string password)
        {
            string url = AssistantProjectData.baseUrl + "/account/login";
            KeyValuePair<string, string> usernamePamram = new KeyValuePair<string, string>("nameOrEmail", username);
            KeyValuePair<string, string> passwordParam = new KeyValuePair<string, string>("password", password);
            string json = await HttpUtil.post(url, usernamePamram, passwordParam);
            Console.WriteLine(json);
            Result<LoginData> result = JsonUtil.jsonToBean<Result<LoginData>>(json);
            if (!result.Status.Equals("200"))
            {
                MessageBox.Show(result.Message,result.Status,MessageBoxButtons.OK,MessageBoxIcon.Error);
                return null;
            }
            HttpUtil.addDafaultHeaders(new KeyValuePair<string, string>("token", result.Data.Token));
            FileUtil.write(_userfile,username);
            return result;
        }

        public string loggedData()
        {
            return FileUtil.read(_userfile);
        }
    }
}
