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

        private string baseUrl = "/query";

        public async Task<Customer> GetCustomer(string idNumber)
        {
            string url = AssistantProjectData.baseUrl + this.baseUrl + $"/byNumberId?id={idNumber}";
            string json = await HttpUtil.get(url);
            return ResultMessageUtil.GetData<Customer>(json);
        }
    }
}
