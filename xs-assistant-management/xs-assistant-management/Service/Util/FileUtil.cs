using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace xs_assistant_management.Service.Util
{
    public static class FileUtil
    {
        public static void write(string path, string content)
        {
            using (StreamWriter sw = new StreamWriter(path))
            {
                sw.WriteLine(content);
            }
        }

        public static string read(string path) {
            createDirectory(Path.GetDirectoryName(path));
            if (!File.Exists(path))
            {
                FileStream fs = File.Create(path);
                fs.Close();
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            using (StreamReader sr = new StreamReader(path)) { 
                stringBuilder.AppendLine(sr.ReadToEnd());
            }
            return stringBuilder.ToString();
        }

        public static void createDirectory(string path) {
            string directory = Path.GetDirectoryName(path);
            if (Directory.Exists(directory))
            {
                return;
            }
            else
            {
                createDirectory(directory);
                Directory.CreateDirectory(path);
            }
        }
    }
}
