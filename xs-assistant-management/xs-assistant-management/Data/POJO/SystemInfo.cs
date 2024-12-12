using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class SystemInfo
    {
        private string customerId;
        private string system;
        private string computerName;
        private string sysId;
        private DateTime lastTime;
        private int version;

        public SystemInfo(string customerId, string system, string computerName, string sysId, DateTime lastTime, int version)
        {
            this.CustomerId = customerId;
            this.System = system;
            this.ComputerName = computerName;
            this.SysId = sysId;
            this.LastTime = lastTime;
            this.Version = version;
        }

        [JsonPropertyName("customerId")]
        public string CustomerId { get => customerId; set => customerId = value; }
        [JsonPropertyName("system")]
        public string System { get => system; set => system = value; }
        [JsonPropertyName("computerName")]
        public string ComputerName { get => computerName; set => computerName = value; }
        [JsonPropertyName("sysId")]
        public string SysId { get => sysId; set => sysId = value; }
        [JsonPropertyName("lastTime")]
        public DateTime LastTime { get => lastTime; set => lastTime = value; }
        [JsonPropertyName("version")]
        public int Version { get => version; set => version = value; }
    }
}
