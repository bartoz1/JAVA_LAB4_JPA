using System;

namespace lab7
{
    class lab7
    {
        public static void Main()
        {

            string[] arguments = Environment.GetCommandLineArgs();
            string path = arguments[1];

            if (Directory.Exists(path))
            {
                
                var directory = new SimpleDirectory(new DirectoryInfo(path));
                directory.Print(0);
                directory.PrintOldestFileInfo();
                Console.WriteLine("\nSerializacja i deserializacja");
                directory.CreateSortedCollectionAndSerialize();
                directory.DeserializeAndPrintCollection();
            }
            else
            {
                Console.WriteLine("{0} is not a valid directory.", path);
            }
        }
    }
}