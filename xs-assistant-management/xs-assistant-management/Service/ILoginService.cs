using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using xs_assistant_management.Data.POJO;

namespace xs_assistant_management.Service
{
    internal interface ILoginService
    {
        string loggedData();
        Task<Result<LoginData>> login(string username, string password);
    }
}
