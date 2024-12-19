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

        private readonly string customerBaseURL = "/query";
        private readonly string deviceBaseURL = "/device";
        private readonly string pointsBaseURL = "/account/points";

        public async Task<Customer> GetCustomer(string idNumber)
        {
            string url = AssistantProjectData.baseUrl + this.customerBaseURL + $"/byNumberId?id={idNumber}";
            string json = await HttpUtil.get(url);
            Console.WriteLine(json);
            return ResultMessageUtil.GetData<Customer>(json);
        }

        public async Task<bool> uploadDevice(SystemInfo systemInfo)
        {
            try
            {
                string url = AssistantProjectData.baseUrl + this.deviceBaseURL + $"/add";
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

        public async Task<List<SystemInfo>> systemInfos(string idNumber)
        {
            try
            {
                string url = AssistantProjectData.baseUrl + this.deviceBaseURL + $"/list?customer_id={idNumber}";
                string json = await HttpUtil.get(url);
                Console.WriteLine(json);
                return ResultMessageUtil.GetData<List<SystemInfo>>(json);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
            return new List<SystemInfo>();
        }

        public async Task<bool> customerClockIn(string idNumber)
        {
            bool result = false;
            try
            {
                string url = AssistantProjectData.baseUrl + this.pointsBaseURL + $"/clock/in?id_number={idNumber}";
                string json = await HttpUtil.get(url);
                result = ResultMessageUtil.GetDataAndSendMessageBox<bool>(json);
            }
            catch (Exception e)
            {
                Console.WriteLine($"{e.Message}");
            }
            return result;
        }

        public async Task<bool> customerClockInCheck(string idNumber)
        {
            bool result = false;
            try
            {
                string url = AssistantProjectData.baseUrl + this.pointsBaseURL + $"/clock/check?id_number={idNumber}";
                string json = await HttpUtil.get(url);
                result = ResultMessageUtil.GetData<bool>(json);

            }
            catch(Exception e)
            {
                Console.WriteLine(e);
            }
            return result;
        }
    }
}
