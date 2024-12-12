using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using xs_assistant_management.Data;
using xs_assistant_management.Data.POJO;
using xs_assistant_management.Service.Util;

namespace xs_assistant_management.Service.Impl
{
    public class CustomerServiceImpl : ICustomerService
    {
        private static ICustomerService instance;
        private CustomerServiceImpl() { }
        private static object lockObject = new object();
        public static ICustomerService Instance()
        {
            if(instance == null)
            {
                lock (lockObject)
                {
                    if(instance == null)
                    {
                        instance = new CustomerServiceImpl();
                    }
                }
            }
            return instance;
        }

        private readonly string customerBaseUrl = "/query";
        private readonly string deviceBaseUrl = "/device";

        public async Task<Customer> GetCustomer(string idNumber)
        {
            string url = AssistantProjectData.baseUrl + this.customerBaseUrl + $"/byNumberId?id={idNumber}";
            string json = await HttpUtil.get(url);
            Console.WriteLine(json);
            return ResultMessageUtil.GetData<Customer>(json);
        }

        public async Task<bool> uploadDevice(SystemInfo systemInfo)
        {
            try
            {
                string url = AssistantProjectData.baseUrl + this.deviceBaseUrl + $"/add";
                string json = await HttpUtil.postByJson<SystemInfo>(url, systemInfo);
                Console.WriteLine(json);
                return ResultMessageUtil.GetData<bool>(json);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
            return false;
        }
    }
}
