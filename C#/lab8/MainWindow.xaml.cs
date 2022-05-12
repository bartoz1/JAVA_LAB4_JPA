using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Forms;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace lab8_WPF_APP
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {

        }
        private void Open(object sender, RoutedEventArgs e)
        {
            FolderBrowserDialog dlg = new FolderBrowserDialog() { Description = "Select directory to open" };

            DialogResult resoult = dlg.ShowDialog();

            if (resoult == System.Windows.Forms.DialogResult.OK)
            {
                treeView.Items.Clear();
                treeView.Items.Add(LoadFiles(dlg.SelectedPath));
                
            }

        }

        private TreeViewItem LoadFiles(string path)
        {
            if (Directory.Exists(path))
            {   
                DirectoryInfo dirInfo = new DirectoryInfo(path);

                var element = new TreeViewItem
                {
                    Header = dirInfo.Name,
                    Tag = path,
                    //IsExpanded = true,
                    ContextMenu = CreateContextMenuDir()
                };

                foreach (var fileInfo in dirInfo.GetFiles())
                {
                    element.Items.Add(LoadFiles(fileInfo.FullName));

                }
                foreach (var subDirInfo in dirInfo.GetDirectories())
                {
                    element.Items.Add(LoadFiles(subDirInfo.FullName));

                }

                
                return element;

            } 
            else if (File.Exists(path))
            {
                FileInfo fileInfo = new FileInfo(path);

                var element = new TreeViewItem
                {
                    Header = fileInfo.Name,
                    Tag = path,
                    ContextMenu = CreateContextMenuFile()
                };

                return element;
            }
            return null;
        }
        private System.Windows.Controls.ContextMenu CreateContextMenuDir()
        {
            System.Windows.Controls.ContextMenu cm = new System.Windows.Controls.ContextMenu();
            cm.Items.Add(new System.Windows.Controls.MenuItem() { Header = "Create" });
            //cm.Items.Add(new System.Windows.Controls.MenuItem() { Header = "Delete" });
            var btn2 = new System.Windows.Controls.MenuItem() { Header = "Delete" };
            btn2.Click += new RoutedEventHandler(DeleteHandle);
            cm.Items.Add(btn2);

            return cm;
        }
        private System.Windows.Controls.ContextMenu CreateContextMenuFile()
        {
            System.Windows.Controls.ContextMenu cm = new System.Windows.Controls.ContextMenu();

            var btn1 = new System.Windows.Controls.MenuItem() { Header = "Open" };
            btn1.Click += new RoutedEventHandler(OpenFile);

            cm.Items.Add(btn1);
            var btn2 = new System.Windows.Controls.MenuItem() { Header = "Delete" };
            btn2.Click += new RoutedEventHandler(DeleteHandle);
            cm.Items.Add(btn2);


            return cm;
        }
        private void OpenFile(object sender, RoutedEventArgs e)
        {
            TreeViewItem item = (TreeViewItem)treeView.SelectedItem;
            string fileData = File.ReadAllText((string)item.Tag);
            FileViewer.Text = fileData;
        }
        private void DeleteHandle(object sender, RoutedEventArgs e)
        {
            TreeViewItem item = (TreeViewItem)treeView.SelectedItem;
            string path = (string)item.Tag;
            DeleteElements(path);

        }

        private void DeleteElements(string path)
        {
            if (Directory.Exists(path))
            {
                foreach(string element in Directory.GetFiles(path))
                {
                    File.Delete(element);
                }
                foreach(string element in Directory.GetDirectories(path)) {
                    DeleteElements(element);
                }
                Directory.Delete(path);
            } 
            else if (File.Exists(path))
            {
                File.Delete(path);
            }
        }
    }
}
