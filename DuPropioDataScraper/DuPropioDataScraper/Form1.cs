using EricUtility;
using EricUtility.Networking.Gathering;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DuPropioDataScraper
{
    public partial class Form1 : Form
    {
        DuProprioSession m_Session = null;
        FavItemSummary m_Current = null;
        Dictionary<int, FavItemSummary> m_All = new Dictionary<int,FavItemSummary>();
        public Form1()
        {
            InitializeComponent();
        }

        private async void btnConnect_Click(object sender, EventArgs e)
        {
            if( String.IsNullOrWhiteSpace(txtUser.Text) || String.IsNullOrWhiteSpace(txtPass.Text) )
                return;

            EnableAll(false);
            m_All.Clear();
            if (m_Session == null || !m_Session.Connected)
            {
                m_Session = new DuProprioSession(txtUser.Text, txtPass.Text);
                if (chkRemember.Checked)
                {
                    Properties.Settings.Default.login_User = txtUser.Text;
                    Properties.Settings.Default.login_Pass = txtPass.Text;
                    Properties.Settings.Default.login_Remember = chkRemember.Checked;
                    Properties.Settings.Default.login_Automatic = checkBox1.Checked;
                }
                else
                {
                    Properties.Settings.Default.login_User = "";
                    Properties.Settings.Default.login_Pass = "";
                    Properties.Settings.Default.login_Remember = chkRemember.Checked;
                    Properties.Settings.Default.login_Automatic = false;
                }
                Properties.Settings.Default.Save();
            }
            IEnumerable<FavItemSummary> favs = await m_Session.GetFavs();
            if (favs != null)
            {
                foreach (FavItemSummary fav in favs)
                {
                    m_All.Add(fav.NoAnnonce, fav);
                    dataGridView1.Rows.Add(fav.NoAnnonce, fav.Prix.ToString("C0"), fav.Ville, fav.Type, fav.Adresse);
                }
                dataGridView1.Sort(dataGridView1.Columns[0], ListSortDirection.Ascending);
            }

            EnableAll(true);
        }

        private void EnableAll(bool enable)
        {
            txtUser.Enabled = enable && (m_Session == null || !m_Session.Connected);
            txtPass.Enabled = enable && (m_Session == null || !m_Session.Connected);
            btnConnect.Enabled = enable;
            dataGridView1.Enabled = enable;
            panel1.Enabled = enable;
            pbWait.Visible = !enable;
            chkRemember.Enabled = enable && (m_Session == null || !m_Session.Connected);
            checkBox1.Enabled = enable && (m_Session == null || !m_Session.Connected) && chkRemember.Checked;
        }

        private void btnViewMap_Click(object sender, EventArgs e)
        {
            Process.Start(m_Current.GMapURL);
        }

        private void btnViewOnline_Click(object sender, EventArgs e)
        {
            Process.Start(m_Current.DetailsURL);
        }

        private void FullPanel()
        {
            if (m_Current.ImageURL != null)
                webBrowser1.Url = new Uri(m_Current.ImageURL);
            else
                webBrowser1.DocumentText = "";
            lblNo.Text = "#" + m_Current.NoAnnonce;
            lblTitle.Text = String.Format("{0:C0} - {1} - {2}", m_Current.Prix.ToString("C0"), m_Current.Ville, m_Current.Type);
            lblSubTitle.Text = String.Format("{0} - {1}", m_Current.Region, m_Current.Adresse);
            lblPrecision.Text = m_Current.Precison;
            label1.Text = m_Current.Summary;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            txtUser.Text = Properties.Settings.Default.login_User;
            txtPass.Text = Properties.Settings.Default.login_Pass;
            chkRemember.Checked = Properties.Settings.Default.login_Remember;
            checkBox1.Checked = Properties.Settings.Default.login_Automatic;

            if (Properties.Settings.Default.login_Automatic)
                btnConnect_Click(btnConnect, new EventArgs());
        }

        private void chkRemember_CheckedChanged(object sender, EventArgs e)
        {
            checkBox1.Enabled = chkRemember.Checked;
        }

        private void txtUser_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                e.Handled = true;
                e.SuppressKeyPress = true;
                btnConnect_Click(btnConnect, new EventArgs());
            }
        }

        private void dataGridView1_SelectionChanged(object sender, EventArgs e)
        {
            if (dataGridView1.SelectedRows.Count > 0)
            {
                m_Current = m_All[int.Parse(dataGridView1.SelectedRows[0].Cells["colId"].Value.ToString())];
                FullPanel();
                panel1.Visible = true;
            }
            else
            {
                m_Current = null;
                panel1.Visible = false;
            }
        }
    }
}
