using DuPropioDataScraper.Entities;
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

namespace DuPropioDataScraper.Windows.Forms
{
    public partial class MainForm : Form
    {
        SessionInfo m_Session = null;
        HouseSummary m_Current = null;
        Dictionary<int, HouseSummary> m_All = new Dictionary<int,HouseSummary>();
        public MainForm()
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
                m_Session = new SessionInfo(txtUser.Text, txtPass.Text);
                if (chkRemember.Checked)
                {
                    Properties.Settings.Default.login_User = txtUser.Text;
                    Properties.Settings.Default.login_Pass = txtPass.Text;
                    Properties.Settings.Default.login_Remember = chkRemember.Checked;
                    Properties.Settings.Default.login_Automatic = chkAutomatic.Checked;
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
            IEnumerable<HouseSummary> favs = await m_Session.GetFavs();
            if (favs != null)
            {
                foreach (HouseSummary fav in favs)
                {
                    m_All.Add(fav.NoAnnonce, fav);
                    dgvListFavs.Rows.Add(fav.NoAnnonce, fav.Prix.ToString("C0"), fav.Ville, fav.Type, fav.Adresse);
                }
                dgvListFavs.Sort(dgvListFavs.Columns[0], ListSortDirection.Ascending);
            }
            dgvListFavs.Rows[0].Selected = true;
            EnableAll(true);
        }

        private void EnableAll(bool enable)
        {
            txtUser.Enabled = enable && (m_Session == null || !m_Session.Connected);
            txtPass.Enabled = enable && (m_Session == null || !m_Session.Connected);
            btnConnect.Enabled = enable;
            dgvListFavs.Enabled = enable;
            pnlContent.Enabled = enable;
            pbWait.Visible = !enable;
            chkRemember.Enabled = enable && (m_Session == null || !m_Session.Connected);
            chkAutomatic.Enabled = enable && (m_Session == null || !m_Session.Connected) && chkRemember.Checked;
        }

        private void btnViewMap_Click(object sender, EventArgs e)
        {
            Process.Start(m_Current.GMapURL);
        }

        private void btnViewOnline_Click(object sender, EventArgs e)
        {
            Process.Start(m_Current.DetailsURL);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            txtUser.Text = Properties.Settings.Default.login_User;
            txtPass.Text = Properties.Settings.Default.login_Pass;
            chkRemember.Checked = Properties.Settings.Default.login_Remember;
            chkAutomatic.Checked = Properties.Settings.Default.login_Automatic;

            if (Properties.Settings.Default.login_Automatic)
                btnConnect_Click(btnConnect, new EventArgs());
        }

        private void chkRemember_CheckedChanged(object sender, EventArgs e)
        {
            chkAutomatic.Enabled = chkRemember.Checked;
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
            if (dgvListFavs.SelectedRows.Count > 0)
            {
                m_Current = m_All[int.Parse(dgvListFavs.SelectedRows[0].Cells["colId"].Value.ToString())];
                FullPanel();
                pnlContent.Visible = true;
            }
            else
            {
                m_Current = null;
                pnlContent.Visible = false;
            }
        }

        private async void btnDetails_Click(object sender, EventArgs e)
        {
            if (await m_Current.LoadDetails())
                FullDetails(m_Current.Details);
            else
                VisibleDetails(false);
        }

        private void FullPanel()
        {
            if (m_Current.ImageURL != null)
                wbVignette.Url = new Uri(m_Current.ImageURL);
            else
                wbVignette.DocumentText = "";
            lblNo.Text = "#" + m_Current.NoAnnonce;
            lblTitle.Text = String.Format("{0:C0} - {1} - {2}", m_Current.Prix.ToString("C0"), m_Current.Ville, m_Current.Type);
            lblSubTitle.Text = String.Format("{0} - {1}", m_Current.Region, m_Current.Adresse);
            lblPrecision.Text = m_Current.Precison;
            lblSummary.Text = m_Current.Summary;
            if (m_Current.Details != null)
                FullDetails(m_Current.Details);
            else
                VisibleDetails(false);
        }

        private void VisibleDetails(bool vis)
        {
            btnDetails.Visible = !vis;
            wbDescription.Visible = vis;
            lblPhone.Visible = vis;
            splitContainer1.Visible = vis;
        }

        private void FullDetails(HouseInfo h)
        {
            VisibleDetails(false);
            wbDescription.DocumentText = h.LongHTMLDescription;
            lblPhone.Text = h.PhoneNumber;
            dgvRooms.Rows.Clear();
            tvProperties.Nodes.Clear();
            h.Rooms.ToList().ForEach(r => dgvRooms.Rows.Add(r.Name, r.Etage, r.DimMetres.Width + "x" + r.DimMetres.Height, r.DimPieds.Width + "x" + r.DimPieds.Height, r.Plancher));
            List<IHouseProperty> props = h.HouseProperty.ToList();
            props.Sort();
            foreach (IHouseProperty prop in props)
            {
                if (prop is SingleHouseProperty)
                {
                    SingleHouseProperty shp = (SingleHouseProperty)prop;
                    tvProperties.Nodes.Add(shp.Name + ": " + shp.Item2);
                }
                else
                {
                    TreeNode tn = new TreeNode(prop.Name);
                    tvProperties.Nodes.Add(tn);
                    ListHouseProperty lhp = (ListHouseProperty)prop;
                    tn.Nodes.AddRange(lhp.Item2.Select(x => new TreeNode(x)).ToArray());
                    tn.Expand();
                }
            }
            VisibleDetails(true);
        }

        private void wbDescription_Navigating(object sender, WebBrowserNavigatingEventArgs e)
        {
            if (e.Url != null && !String.IsNullOrWhiteSpace(e.Url.AbsoluteUri) && e.Url.AbsoluteUri != "about:blank")
            {
                Process.Start(e.Url.AbsoluteUri);
                e.Cancel = true;
            }
        }
    }
}
