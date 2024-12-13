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
        public static string GetOSInfo()
        {
            int marjor = Environment.OSVersion.Version.Major;
            int minor = Environment.OSVersion.Version.Minor;
            if (marjor == 10)
            {
                return "Windows 10 / Windows 11";
            }
            else if (marjor == 6 && minor == 3)
            {
                return "Windows 8.1";
            }
            else if (marjor == 6 && minor == 2)
            {
                return "Windows 8";
            }
            else if (marjor == 6 && minor == 1)
            {
                return "Windows 7";
            }
            else if (marjor == 6 && minor == 0)
            {
                return "Windows Vista";
            }
            else if (marjor == 5 && minor == 2)
            {
                return "Windows Server 2003 R2 / XP 64-bit";
            }
            else if (marjor == 5 && minor == 1)
            {
                return "Windows XP";
            }
            else if (marjor == 5 && minor == 0)
            {
                return "Windows 2000";
            }
            else
            {
                return "未知的 Windows 版本";
            }
        }

        public static string GetComputerName()
        {
            return SystemInformation.ComputerName;
        }
    }
}
