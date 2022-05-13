using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace lab8_WPF_APP
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class NewElementDiagol : Window
    {
        public string newFileName { get; set; }
        public byte[] fileInfo { get; set; } = new byte[5];
        public NewElementDiagol()
        {
            InitializeComponent();
        }

        private void okButtonClick(object sender, RoutedEventArgs e)
        {
            string input = inputBox.Text;
            if ((!Regex.IsMatch(input, "^[a-zA-Z0-9_~-]{1,8}\\.(txt|php|html)$") && fileRadio.IsChecked == true)||input =="")
            {
                badNameBorder.Visibility = Visibility.Visible;
                wrongNameInfo.Visibility = Visibility.Visible;
                return;
            }
            
            this.newFileName = input;
            
            if (fileRadio.IsChecked == true)
            {
                fileInfo[0] = 1;
            }
            if (readOnlyCheckBox.IsChecked == true)
            {
                fileInfo[1] = 1;
            }
            if (archiveCheckBox.IsChecked == true)
            {
                fileInfo[2] = 1;
            }
            if (hiddenCheckBox.IsChecked == true)
            {
                fileInfo[3] = 1;
            }
            if (systemCheckBox.IsChecked == true)
            {
                fileInfo[4] = 1;
            }
            this.DialogResult = true;
        }
    }
}
