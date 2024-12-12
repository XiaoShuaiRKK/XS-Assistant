using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Management;
using System.Windows.Forms;

namespace xs_assistant_management.Service.Util
{
    public static class ManagementUtil
    {
        public static OperatingSystem GetOSInfo()
        {
            return Environment.OSVersion;
        }

        public static string GetComputerName()
        {
            return SystemInformation.ComputerName;
        }
    }
}
