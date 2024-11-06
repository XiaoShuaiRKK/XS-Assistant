using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace xs_assistant_management.Data.POJO
{
    public class Result<T>
    {
        long timestamp;
        string status;
        string message;
        T data;

        public Result(long timestamp, string status, string message, T data)
        {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public long Timestamp { get => timestamp; set => timestamp = value; }
        public string Status { get => status; set => status = value; }
        public string Message { get => message; set => message = value; }
        public T Data { get => data; set => data = value; }
    }
}
