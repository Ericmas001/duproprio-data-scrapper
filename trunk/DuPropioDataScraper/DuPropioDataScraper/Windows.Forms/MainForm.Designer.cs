namespace DuPropioDataScraper.Windows.Forms
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.txtPass = new System.Windows.Forms.TextBox();
            this.txtUser = new System.Windows.Forms.TextBox();
            this.lblPass = new System.Windows.Forms.Label();
            this.lblUser = new System.Windows.Forms.Label();
            this.pnlContent = new System.Windows.Forms.Panel();
            this.lblSummary = new System.Windows.Forms.Label();
            this.lblPrecision = new System.Windows.Forms.Label();
            this.wbVignette = new System.Windows.Forms.WebBrowser();
            this.btnViewMap = new System.Windows.Forms.Button();
            this.btnViewOnline = new System.Windows.Forms.Button();
            this.lblSubTitle = new System.Windows.Forms.Label();
            this.lblTitle = new System.Windows.Forms.Label();
            this.lblNo = new System.Windows.Forms.Label();
            this.btnConnect = new System.Windows.Forms.Button();
            this.pbWait = new System.Windows.Forms.PictureBox();
            this.chkRemember = new System.Windows.Forms.CheckBox();
            this.chkAutomatic = new System.Windows.Forms.CheckBox();
            this.splitContainer = new System.Windows.Forms.SplitContainer();
            this.dgvListFavs = new System.Windows.Forms.DataGridView();
            this.colId = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colPrix = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colVille = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colType = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colAdresse = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.btnDetails = new System.Windows.Forms.Button();
            this.wbDescription = new System.Windows.Forms.WebBrowser();
            this.lblPhone = new System.Windows.Forms.Label();
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.tvProperties = new System.Windows.Forms.TreeView();
            this.dgvRooms = new System.Windows.Forms.DataGridView();
            this.colRoomName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colRoomEtage = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colRoomDimM = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colDimP = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.colRoomFloor = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.pnlContent.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbWait)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer)).BeginInit();
            this.splitContainer.Panel1.SuspendLayout();
            this.splitContainer.Panel2.SuspendLayout();
            this.splitContainer.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvListFavs)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvRooms)).BeginInit();
            this.SuspendLayout();
            // 
            // txtPass
            // 
            this.txtPass.Location = new System.Drawing.Point(332, 6);
            this.txtPass.Name = "txtPass";
            this.txtPass.PasswordChar = '•';
            this.txtPass.Size = new System.Drawing.Size(220, 22);
            this.txtPass.TabIndex = 7;
            this.txtPass.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtUser_KeyDown);
            // 
            // txtUser
            // 
            this.txtUser.Location = new System.Drawing.Point(57, 6);
            this.txtUser.Name = "txtUser";
            this.txtUser.Size = new System.Drawing.Size(220, 22);
            this.txtUser.TabIndex = 6;
            this.txtUser.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtUser_KeyDown);
            // 
            // lblPass
            // 
            this.lblPass.AutoSize = true;
            this.lblPass.Location = new System.Drawing.Point(283, 9);
            this.lblPass.Name = "lblPass";
            this.lblPass.Size = new System.Drawing.Size(43, 17);
            this.lblPass.TabIndex = 5;
            this.lblPass.Text = "Pass:";
            // 
            // lblUser
            // 
            this.lblUser.AutoSize = true;
            this.lblUser.Location = new System.Drawing.Point(9, 9);
            this.lblUser.Name = "lblUser";
            this.lblUser.Size = new System.Drawing.Size(42, 17);
            this.lblUser.TabIndex = 4;
            this.lblUser.Text = "User:";
            // 
            // pnlContent
            // 
            this.pnlContent.AutoScroll = true;
            this.pnlContent.BackColor = System.Drawing.Color.White;
            this.pnlContent.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.pnlContent.Controls.Add(this.splitContainer1);
            this.pnlContent.Controls.Add(this.lblPhone);
            this.pnlContent.Controls.Add(this.wbDescription);
            this.pnlContent.Controls.Add(this.btnDetails);
            this.pnlContent.Controls.Add(this.lblSummary);
            this.pnlContent.Controls.Add(this.lblPrecision);
            this.pnlContent.Controls.Add(this.wbVignette);
            this.pnlContent.Controls.Add(this.btnViewMap);
            this.pnlContent.Controls.Add(this.btnViewOnline);
            this.pnlContent.Controls.Add(this.lblSubTitle);
            this.pnlContent.Controls.Add(this.lblTitle);
            this.pnlContent.Controls.Add(this.lblNo);
            this.pnlContent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlContent.Location = new System.Drawing.Point(0, 0);
            this.pnlContent.Name = "pnlContent";
            this.pnlContent.Size = new System.Drawing.Size(646, 321);
            this.pnlContent.TabIndex = 12;
            this.pnlContent.Visible = false;
            // 
            // lblSummary
            // 
            this.lblSummary.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.lblSummary.Location = new System.Drawing.Point(311, 77);
            this.lblSummary.Name = "lblSummary";
            this.lblSummary.Size = new System.Drawing.Size(307, 80);
            this.lblSummary.TabIndex = 16;
            this.lblSummary.Text = "3 chambre(s), 2 salle(s) de bain, Aire habitable 1 000 pi², Terrain 6 300 pi², Pi" +
    "scine, Vue panoramique, Stationnement privé";
            this.lblSummary.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblPrecision
            // 
            this.lblPrecision.AutoSize = true;
            this.lblPrecision.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPrecision.ForeColor = System.Drawing.Color.Red;
            this.lblPrecision.Location = new System.Drawing.Point(308, 3);
            this.lblPrecision.Name = "lblPrecision";
            this.lblPrecision.Size = new System.Drawing.Size(66, 17);
            this.lblPrecision.TabIndex = 15;
            this.lblPrecision.Text = "Précision";
            // 
            // wbVignette
            // 
            this.wbVignette.IsWebBrowserContextMenuEnabled = false;
            this.wbVignette.Location = new System.Drawing.Point(3, 9);
            this.wbVignette.Margin = new System.Windows.Forms.Padding(0);
            this.wbVignette.MinimumSize = new System.Drawing.Size(20, 20);
            this.wbVignette.Name = "wbVignette";
            this.wbVignette.ScrollBarsEnabled = false;
            this.wbVignette.Size = new System.Drawing.Size(174, 148);
            this.wbVignette.TabIndex = 14;
            this.wbVignette.WebBrowserShortcutsEnabled = false;
            // 
            // btnViewMap
            // 
            this.btnViewMap.BackgroundImage = global::DuPropioDataScraper.Properties.Resources.gmap_icon;
            this.btnViewMap.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnViewMap.Location = new System.Drawing.Point(241, 72);
            this.btnViewMap.Name = "btnViewMap";
            this.btnViewMap.Size = new System.Drawing.Size(49, 47);
            this.btnViewMap.TabIndex = 13;
            this.btnViewMap.UseVisualStyleBackColor = true;
            this.btnViewMap.Click += new System.EventHandler(this.btnViewMap_Click);
            // 
            // btnViewOnline
            // 
            this.btnViewOnline.BackgroundImage = global::DuPropioDataScraper.Properties.Resources.logo_small;
            this.btnViewOnline.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnViewOnline.Location = new System.Drawing.Point(186, 72);
            this.btnViewOnline.Name = "btnViewOnline";
            this.btnViewOnline.Size = new System.Drawing.Size(49, 47);
            this.btnViewOnline.TabIndex = 12;
            this.btnViewOnline.UseVisualStyleBackColor = true;
            this.btnViewOnline.Click += new System.EventHandler(this.btnViewOnline_Click);
            // 
            // lblSubTitle
            // 
            this.lblSubTitle.AutoSize = true;
            this.lblSubTitle.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSubTitle.Location = new System.Drawing.Point(183, 47);
            this.lblSubTitle.Name = "lblSubTitle";
            this.lblSubTitle.Size = new System.Drawing.Size(203, 20);
            this.lblSubTitle.TabIndex = 3;
            this.lblSubTitle.Text = "250 000$ - Lévis - Maison";
            // 
            // lblTitle
            // 
            this.lblTitle.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.lblTitle.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblTitle.Location = new System.Drawing.Point(183, 22);
            this.lblTitle.Name = "lblTitle";
            this.lblTitle.Size = new System.Drawing.Size(435, 25);
            this.lblTitle.TabIndex = 2;
            this.lblTitle.Text = "250 000$ - Lévis - Maison";
            // 
            // lblNo
            // 
            this.lblNo.AutoSize = true;
            this.lblNo.Location = new System.Drawing.Point(183, 3);
            this.lblNo.Name = "lblNo";
            this.lblNo.Size = new System.Drawing.Size(68, 17);
            this.lblNo.TabIndex = 1;
            this.lblNo.Text = "# 424242";
            // 
            // btnConnect
            // 
            this.btnConnect.BackgroundImage = global::DuPropioDataScraper.Properties.Resources.logo;
            this.btnConnect.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnConnect.Location = new System.Drawing.Point(558, 6);
            this.btnConnect.Name = "btnConnect";
            this.btnConnect.Size = new System.Drawing.Size(50, 50);
            this.btnConnect.TabIndex = 11;
            this.btnConnect.UseVisualStyleBackColor = true;
            this.btnConnect.Click += new System.EventHandler(this.btnConnect_Click);
            // 
            // pbWait
            // 
            this.pbWait.Image = global::DuPropioDataScraper.Properties.Resources.wait;
            this.pbWait.Location = new System.Drawing.Point(614, 6);
            this.pbWait.Name = "pbWait";
            this.pbWait.Size = new System.Drawing.Size(51, 50);
            this.pbWait.TabIndex = 0;
            this.pbWait.TabStop = false;
            this.pbWait.Visible = false;
            // 
            // chkRemember
            // 
            this.chkRemember.AutoSize = true;
            this.chkRemember.Location = new System.Drawing.Point(192, 34);
            this.chkRemember.Name = "chkRemember";
            this.chkRemember.Size = new System.Drawing.Size(214, 21);
            this.chkRemember.TabIndex = 13;
            this.chkRemember.Text = "Remember login informations";
            this.chkRemember.UseVisualStyleBackColor = true;
            this.chkRemember.CheckedChanged += new System.EventHandler(this.chkRemember_CheckedChanged);
            this.chkRemember.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtUser_KeyDown);
            // 
            // chkAutomatic
            // 
            this.chkAutomatic.AutoSize = true;
            this.chkAutomatic.Location = new System.Drawing.Point(412, 34);
            this.chkAutomatic.Name = "chkAutomatic";
            this.chkAutomatic.Size = new System.Drawing.Size(140, 21);
            this.chkAutomatic.TabIndex = 14;
            this.chkAutomatic.Text = "Automatic Sing-in";
            this.chkAutomatic.UseVisualStyleBackColor = true;
            this.chkAutomatic.KeyDown += new System.Windows.Forms.KeyEventHandler(this.txtUser_KeyDown);
            // 
            // splitContainer
            // 
            this.splitContainer.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.splitContainer.Location = new System.Drawing.Point(12, 61);
            this.splitContainer.Name = "splitContainer";
            this.splitContainer.Orientation = System.Windows.Forms.Orientation.Horizontal;
            // 
            // splitContainer.Panel1
            // 
            this.splitContainer.Panel1.Controls.Add(this.dgvListFavs);
            // 
            // splitContainer.Panel2
            // 
            this.splitContainer.Panel2.Controls.Add(this.pnlContent);
            this.splitContainer.Size = new System.Drawing.Size(646, 648);
            this.splitContainer.SplitterDistance = 323;
            this.splitContainer.TabIndex = 15;
            // 
            // dgvListFavs
            // 
            this.dgvListFavs.AllowUserToAddRows = false;
            this.dgvListFavs.AllowUserToDeleteRows = false;
            this.dgvListFavs.AllowUserToResizeRows = false;
            this.dgvListFavs.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dgvListFavs.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvListFavs.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.colId,
            this.colPrix,
            this.colVille,
            this.colType,
            this.colAdresse});
            this.dgvListFavs.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvListFavs.Location = new System.Drawing.Point(0, 0);
            this.dgvListFavs.MultiSelect = false;
            this.dgvListFavs.Name = "dgvListFavs";
            this.dgvListFavs.RowHeadersVisible = false;
            this.dgvListFavs.RowTemplate.Height = 24;
            this.dgvListFavs.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvListFavs.Size = new System.Drawing.Size(646, 323);
            this.dgvListFavs.TabIndex = 0;
            this.dgvListFavs.SelectionChanged += new System.EventHandler(this.dataGridView1_SelectionChanged);
            // 
            // colId
            // 
            this.colId.HeaderText = "#";
            this.colId.Name = "colId";
            this.colId.ReadOnly = true;
            this.colId.Width = 41;
            // 
            // colPrix
            // 
            this.colPrix.HeaderText = "Prix";
            this.colPrix.Name = "colPrix";
            this.colPrix.ReadOnly = true;
            this.colPrix.Width = 56;
            // 
            // colVille
            // 
            this.colVille.HeaderText = "Ville";
            this.colVille.Name = "colVille";
            this.colVille.ReadOnly = true;
            this.colVille.Width = 59;
            // 
            // colType
            // 
            this.colType.HeaderText = "Type";
            this.colType.Name = "colType";
            this.colType.ReadOnly = true;
            this.colType.Width = 65;
            // 
            // colAdresse
            // 
            this.colAdresse.HeaderText = "Adresse";
            this.colAdresse.Name = "colAdresse";
            this.colAdresse.ReadOnly = true;
            this.colAdresse.Width = 85;
            // 
            // btnDetails
            // 
            this.btnDetails.Location = new System.Drawing.Point(186, 125);
            this.btnDetails.Name = "btnDetails";
            this.btnDetails.Size = new System.Drawing.Size(104, 32);
            this.btnDetails.TabIndex = 17;
            this.btnDetails.Text = "Load Details";
            this.btnDetails.UseVisualStyleBackColor = true;
            this.btnDetails.Click += new System.EventHandler(this.btnDetails_Click);
            // 
            // wbDescription
            // 
            this.wbDescription.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.wbDescription.IsWebBrowserContextMenuEnabled = false;
            this.wbDescription.Location = new System.Drawing.Point(-2, 163);
            this.wbDescription.MinimumSize = new System.Drawing.Size(20, 20);
            this.wbDescription.Name = "wbDescription";
            this.wbDescription.Size = new System.Drawing.Size(620, 94);
            this.wbDescription.TabIndex = 18;
            this.wbDescription.Visible = false;
            this.wbDescription.Navigating += new System.Windows.Forms.WebBrowserNavigatingEventHandler(this.wbDescription_Navigating);
            // 
            // lblPhone
            // 
            this.lblPhone.AutoSize = true;
            this.lblPhone.Font = new System.Drawing.Font("Microsoft Sans Serif", 7.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPhone.Location = new System.Drawing.Point(185, 125);
            this.lblPhone.Name = "lblPhone";
            this.lblPhone.Size = new System.Drawing.Size(144, 17);
            this.lblPhone.TabIndex = 19;
            this.lblPhone.Text = "+1 (418) 856-1897";
            this.lblPhone.Visible = false;
            // 
            // splitContainer1
            // 
            this.splitContainer1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.splitContainer1.Location = new System.Drawing.Point(3, 263);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.dgvRooms);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.tvProperties);
            this.splitContainer1.Size = new System.Drawing.Size(615, 519);
            this.splitContainer1.SplitterDistance = 332;
            this.splitContainer1.TabIndex = 20;
            // 
            // tvProperties
            // 
            this.tvProperties.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tvProperties.Location = new System.Drawing.Point(0, 0);
            this.tvProperties.Name = "tvProperties";
            this.tvProperties.Size = new System.Drawing.Size(279, 519);
            this.tvProperties.TabIndex = 0;
            // 
            // dgvRooms
            // 
            this.dgvRooms.AllowUserToAddRows = false;
            this.dgvRooms.AllowUserToDeleteRows = false;
            this.dgvRooms.AllowUserToResizeRows = false;
            this.dgvRooms.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dgvRooms.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvRooms.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.colRoomName,
            this.colRoomEtage,
            this.colRoomDimM,
            this.colDimP,
            this.colRoomFloor});
            this.dgvRooms.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvRooms.Location = new System.Drawing.Point(0, 0);
            this.dgvRooms.MultiSelect = false;
            this.dgvRooms.Name = "dgvRooms";
            this.dgvRooms.RowHeadersVisible = false;
            this.dgvRooms.RowTemplate.Height = 24;
            this.dgvRooms.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dgvRooms.Size = new System.Drawing.Size(332, 519);
            this.dgvRooms.TabIndex = 1;
            // 
            // colRoomName
            // 
            this.colRoomName.HeaderText = "Pièce";
            this.colRoomName.Name = "colRoomName";
            this.colRoomName.ReadOnly = true;
            this.colRoomName.Width = 68;
            // 
            // colRoomEtage
            // 
            this.colRoomEtage.HeaderText = "Étage";
            this.colRoomEtage.Name = "colRoomEtage";
            this.colRoomEtage.ReadOnly = true;
            this.colRoomEtage.Width = 70;
            // 
            // colRoomDimM
            // 
            this.colRoomDimM.HeaderText = "Dimensions (m)";
            this.colRoomDimM.Name = "colRoomDimM";
            this.colRoomDimM.ReadOnly = true;
            this.colRoomDimM.Width = 120;
            // 
            // colDimP
            // 
            this.colDimP.HeaderText = "Dimensions (pi)";
            this.colDimP.Name = "colDimP";
            this.colDimP.ReadOnly = true;
            this.colDimP.Width = 120;
            // 
            // colRoomFloor
            // 
            this.colRoomFloor.HeaderText = "Plancher";
            this.colRoomFloor.Name = "colRoomFloor";
            this.colRoomFloor.ReadOnly = true;
            this.colRoomFloor.Width = 89;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(670, 721);
            this.Controls.Add(this.splitContainer);
            this.Controls.Add(this.chkAutomatic);
            this.Controls.Add(this.chkRemember);
            this.Controls.Add(this.btnConnect);
            this.Controls.Add(this.txtPass);
            this.Controls.Add(this.txtUser);
            this.Controls.Add(this.lblPass);
            this.Controls.Add(this.lblUser);
            this.Controls.Add(this.pbWait);
            this.MinimumSize = new System.Drawing.Size(688, 768);
            this.Name = "MainForm";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.pnlContent.ResumeLayout(false);
            this.pnlContent.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbWait)).EndInit();
            this.splitContainer.Panel1.ResumeLayout(false);
            this.splitContainer.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer)).EndInit();
            this.splitContainer.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvListFavs)).EndInit();
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvRooms)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pbWait;
        private System.Windows.Forms.TextBox txtPass;
        private System.Windows.Forms.TextBox txtUser;
        private System.Windows.Forms.Label lblPass;
        private System.Windows.Forms.Label lblUser;
        private System.Windows.Forms.Button btnConnect;
        private System.Windows.Forms.Panel pnlContent;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.Label lblNo;
        private System.Windows.Forms.Label lblSubTitle;
        private System.Windows.Forms.Button btnViewMap;
        private System.Windows.Forms.Button btnViewOnline;
        private System.Windows.Forms.WebBrowser wbVignette;
        private System.Windows.Forms.Label lblPrecision;
        private System.Windows.Forms.Label lblSummary;
        private System.Windows.Forms.CheckBox chkRemember;
        private System.Windows.Forms.CheckBox chkAutomatic;
        private System.Windows.Forms.SplitContainer splitContainer;
        private System.Windows.Forms.DataGridView dgvListFavs;
        private System.Windows.Forms.DataGridViewTextBoxColumn colId;
        private System.Windows.Forms.DataGridViewTextBoxColumn colPrix;
        private System.Windows.Forms.DataGridViewTextBoxColumn colVille;
        private System.Windows.Forms.DataGridViewTextBoxColumn colType;
        private System.Windows.Forms.DataGridViewTextBoxColumn colAdresse;
        private System.Windows.Forms.Button btnDetails;
        private System.Windows.Forms.WebBrowser wbDescription;
        private System.Windows.Forms.Label lblPhone;
        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.DataGridView dgvRooms;
        private System.Windows.Forms.DataGridViewTextBoxColumn colRoomName;
        private System.Windows.Forms.DataGridViewTextBoxColumn colRoomEtage;
        private System.Windows.Forms.DataGridViewTextBoxColumn colRoomDimM;
        private System.Windows.Forms.DataGridViewTextBoxColumn colDimP;
        private System.Windows.Forms.DataGridViewTextBoxColumn colRoomFloor;
        private System.Windows.Forms.TreeView tvProperties;

    }
}

