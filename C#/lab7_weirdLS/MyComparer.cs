using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab7
{
    [Serializable]
    public class MyComparer : IComparer<string>
    {
        public int Compare(string a, string b)
        {
            int dif = a.Length.CompareTo(b.Length);
            if (dif != 0)
                return dif;
            return a.CompareTo(b);
        }
    }
}
