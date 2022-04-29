using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace lab7
{
    public static class FileExtensions
    {
        public static DateTime GetOldestFileDate(this DirectoryInfo directoryInfo)
        {
            DateTime date = DateTime.MaxValue;
            
            foreach (FileInfo file in directoryInfo.GetFiles())
            {
                if (file.Exists)
                {
                    if (file.CreationTime < date)
                        date = file.CreationTime;
                }
            }
            foreach (DirectoryInfo subDirectoryInfo in directoryInfo.GetDirectories())
            {
                if (subDirectoryInfo.Exists)
                {
                    DateTime subMinDate = GetOldestFileDate((DirectoryInfo)subDirectoryInfo);
                    if (subMinDate < date)
                        date = subMinDate;
                }
            }
            return date;
        }
        public static String GetAttributes(this FileSystemInfo finfo)
        {
            StringBuilder sb = new StringBuilder();
            sb.Append(finfo.Attributes.HasFlag(FileAttributes.ReadOnly) ? 'r' : "-");
            sb.Append(finfo.Attributes.HasFlag(FileAttributes.Archive) ? 'a' : "-");
            sb.Append(finfo.Attributes.HasFlag(FileAttributes.Hidden) ? 'h' : "-");
            sb.Append(finfo.Attributes.HasFlag(FileAttributes.System) ? 's' : "-");
            return sb.ToString();
        }
        

    }
}
