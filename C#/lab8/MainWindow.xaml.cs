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
        private string rootPath = "";
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
                rootPath = dlg.SelectedPath;
                treeView.Items.Clear();
                treeView.Items.Add(LoadFiles(dlg.SelectedPath));
                //treeView.MouseDown += (sender, args) => treeView.SelectedItem = args.Node;

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
                    ContextMenu = CreateContextMenuDir()
                };
                if (rootPath == path)
                {
                    element.IsExpanded = true;
                }

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
            var btn1 = new System.Windows.Controls.MenuItem() { Header = "Create" };
            btn1.Click += new RoutedEventHandler(CreateHandle);
            cm.Items.Add(btn1);
            var btn2 = new System.Windows.Controls.MenuItem() { Header = "Delete" };
            btn2.Click += new RoutedEventHandler(DeleteHandle);
            cm.Items.Add(btn2);

            return cm;
        }
        private System.Windows.Controls.ContextMenu CreateContextMenuFile()
        {
            System.Windows.Controls.ContextMenu cm = new System.Windows.Controls.ContextMenu();

            var btn1 = new System.Windows.Controls.MenuItem() { Header = "Open" };
            btn1.Click += new RoutedEventHandler(OpenHandle);

            cm.Items.Add(btn1);
            var btn2 = new System.Windows.Controls.MenuItem() { Header = "Delete" };
            btn2.Click += new RoutedEventHandler(DeleteHandle);
            cm.Items.Add(btn2);


            return cm;
        }
        private void OpenHandle(object sender, RoutedEventArgs e)
        {
            TreeViewItem item = (TreeViewItem)treeView.SelectedItem;
            string fileData = File.ReadAllText((string)item.Tag);
            FileViewer.Text = fileData;
        }
        private void DeleteHandle(object sender, RoutedEventArgs e)
        {
            TreeViewItem item = (TreeViewItem)treeView.SelectedItem;
            if (item != null)
            {
                string path = (string)item.Tag;
                DeleteElements(path);
                ReloadFilesView();
            }

        }
        private void CreateHandle(object sender, RoutedEventArgs e)
        {
            NewElementDiagol newElementDiagol = new NewElementDiagol();
            var resoult = newElementDiagol.ShowDialog();

            if (resoult == true)
            {
                TreeViewItem item = (TreeViewItem)treeView.SelectedItem;
                string currPath = (string)item.Tag;
                string newFilePath = currPath + "\\" + newElementDiagol.newFileName;

                // if creating file ; 0 - directory
                if (newElementDiagol.fileInfo[0] == 1)
                {
                    File.Create(newFilePath);
                }
                else
                {
                    Directory.CreateDirectory(newFilePath);
                }
                // setting attributes
                FileAttributes attributes = FileAttributes.Normal;
                if (newElementDiagol.fileInfo[1] == 1)
                {
                    attributes |= FileAttributes.ReadOnly;
                }
                if (newElementDiagol.fileInfo[2] == 1)
                {
                    attributes |= FileAttributes.Archive;
                }
                if (newElementDiagol.fileInfo[3] == 1)
                {
                    attributes |= FileAttributes.Hidden;
                }
                if (newElementDiagol.fileInfo[4] == 1)
                {
                    attributes |= FileAttributes.System;
                }
                File.SetAttributes(newFilePath, attributes);
                ReloadFilesView();
            }
        }

        private void DeleteElements(string path)
        {
            FileAttributes attributes = File.GetAttributes(path);
            File.SetAttributes(path, attributes & ~FileAttributes.ReadOnly);

            if (Directory.Exists(path))
            {
                foreach(string element in Directory.GetFiles(path))
                {
                    FileAttributes attributes2 = File.GetAttributes(element);
                    File.SetAttributes(element, attributes2 & ~FileAttributes.ReadOnly);
                    File.Delete(element);
                }
                foreach(string element in Directory.GetDirectories(path)) {
                    FileAttributes attributes2 = File.GetAttributes(element);
                    File.SetAttributes(element, attributes2 & ~FileAttributes.ReadOnly);
                    DeleteElements(element);
                }
                Directory.Delete(path);
            } 
            else if (File.Exists(path))
            {
                File.Delete(path);
            }
        }

        private void ExitProgram(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
        private void ReloadFilesView()
        {
            if (rootPath != "")
            {
                treeView.Items.Clear();
                treeView.Items.Add(LoadFiles(rootPath));

            }
        }
       
        private void FileViewer_Scroll(object sender, System.Windows.Controls.Primitives.ScrollEventArgs e)
        {

        }
        private void SelectedItenChangeHandle(object sender, RoutedPropertyChangedEventArgs<Object> e)
        {
            TreeViewItem item = (TreeViewItem)treeView.SelectedItem;
            if (item == null)
                return;

            string currPath = (string)item.Tag;
            FileAttributes attributes;
            
            if (File.Exists(currPath))
            {
                attributes = File.GetAttributes(currPath);
            } 
            else
            {
                DirectoryInfo dir = new DirectoryInfo(currPath);
                attributes = dir.Attributes;
            }

            //attributes.ToString();
            string rash = "";
            if (attributes.HasFlag(FileAttributes.ReadOnly))
                rash += "r";
            else
                rash += "-";
            if (attributes.HasFlag(FileAttributes.Archive))
                rash += "a";
            else
                rash += "-";
            if (attributes.HasFlag(FileAttributes.System))
                rash += "s";
            else
                rash += "-";
            if (attributes.HasFlag(FileAttributes.Hidden))
                rash += "h";
            else
                rash += "-";
            rashInfo.Text = rash;
        }
    }
}
