using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using xs_assistant_management.Data.POJO;

namespace xs_assistant_management.Service
{
    public interface ICustomerService
    {
        Task<Customer> GetCustomer(string idNumber);

        Task<bool> uploadDevice(SystemInfo systemInfo);
    }
}
