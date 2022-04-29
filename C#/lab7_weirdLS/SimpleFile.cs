using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab7
{
    public class SimpleFile : SimpleElement
    {
        public SimpleFile(FileInfo fileinfo)
        {
            this.type = "file";
            this.file = fileinfo;
            this.size = (int)fileinfo.Length;
        }
        public override void Print(int depth)
        {
            string dir = "";
            for (int i = 0; i < depth; i++)
                dir += '\t';
            Console.WriteLine("{0}{1} {2} bajtów {3}", dir, file.Name, ((FileInfo)file).Length, file.GetAttributes());
        }
    }
}
