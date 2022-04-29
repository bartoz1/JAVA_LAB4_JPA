using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace lab7
{
    public class SimpleDirectory : SimpleElement
    {
        List<SimpleElement> subElements;
        private SortedList sortedCollection;
        public SimpleDirectory(DirectoryInfo dirInfo)
        {
            this.type = "directory";
            this.file = dirInfo;
            subElements = new List<SimpleElement>();
            //sortedCollection = new SortedList();

            foreach(var fileInfo in dirInfo.GetFiles())
            {
                subElements.Add(new SimpleFile(fileInfo));
                //sortedCollection.Add(fileInfo.Name, fileInfo.Length);
            }
            foreach (var subDirInfo in dirInfo.GetDirectories())
            {
                subElements.Add(new SimpleDirectory(subDirInfo));
                //sortedCollection.Add(subDirInfo.Name, subDirInfo.GetFiles().Length);
            }
            this.size = subElements.Count;

            

        }
        public void CreateSortedCollectionAndSerialize()
        {
            SortedList<String, long> sortedCollection = new SortedList<String, long>(new MyComparer());

            foreach(var subElement in subElements)
            {
                sortedCollection.Add(subElement.file.Name, subElement.size);
            }

            FileStream fs = new FileStream("SerializedDataFile.dat", FileMode.Create);
            BinaryFormatter formatter = new BinaryFormatter();
            try
            {
                formatter.Serialize(fs, sortedCollection);
            }
            catch (SerializationException e)
            {
                Console.WriteLine("Failed to serialize. Reason: " + e.Message);
                throw;
            }
            finally
            {
                fs.Close();
            }
        }

        public void DeserializeAndPrintCollection()
        {
            SortedList<String, long> sortedCollection = new SortedList<String, long>(new MyComparer());
            FileStream fs = new FileStream("SerializedDataFile.dat", FileMode.Open);
            try
            {
                BinaryFormatter formatter = new BinaryFormatter();

                // Deserialize the hashtable from the file and
                // assign the reference to the local variable.
                sortedCollection = (SortedList<String, long>)formatter.Deserialize(fs);
            }
            catch (SerializationException e)
            {
                Console.WriteLine("Failed to deserialize. Reason: " + e.Message);
                throw;
            }
            finally
            {
                fs.Close();
            }

            foreach(var data in sortedCollection)
            {
                Console.WriteLine("{0} -> {1}", data.Key, data.Value);
            }
        }


        internal void PrintOldestFileInfo()
        {
            Console.WriteLine("Najstarszy plik: {0}",((DirectoryInfo)file).GetOldestFileDate());
        }

        public override void Print(int depth)
        {
            string dir = "";
            for (int i = 0; i < depth; i++)
                dir += '\t';

            Console.WriteLine("{0}{1} ({2})",dir, file.Name, subElements.Count);
            foreach (var subElement in subElements)
            {
                subElement.Print(depth + 1);
            }
        }
        
    }
}
