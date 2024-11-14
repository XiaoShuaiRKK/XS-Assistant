using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using xs_assistant_management.Data.POJO;

namespace xs_assistant_management.Service.Util
{
    public class ResultMessageUtil
    {
        public static Result<T> JsonToResult<T>(string json)
        {
            return JsonUtil.jsonToBean<Result<T>>(json);
        }

        public static E CheckData<E>(Result<E> result){
            if (!result.Status.Equals("200"))
            {
                MessageBox.Show(result.Status,result.Message,MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            return result.Data;
        }

        public static T GetData<T>(string json)
        {
            Result<T> result = JsonToResult<T>(json);
            T data = CheckData(result);
            return data;
        }
    }
}
