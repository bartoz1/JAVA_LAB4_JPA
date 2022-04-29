using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab7
{
    public abstract class SimpleElement
    {
        public FileSystemInfo file;
        public string type = "element";
        public int size = 0;
        public abstract void Print(int depth);

    }
}
