using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using EricUtility;
using System.Globalization;
using System.Web;
using Newtonsoft.Json;

namespace DuProprioLib.Entities
{
    public class RoomInfo
    {
        private string m_Name;
        private string m_Etage;
        private SizeF m_DimPieds;
        private SizeF m_DimMetres;
        private string m_Plancher;
        private HouseDetailInfo m_House;

        [JsonIgnore]
        public HouseDetailInfo House
        {
            get { return m_House; }
        }

        public string Name
        {
            get { return m_Name; }
            set { m_Name = value; }
        }

        public string Etage
        {
            get { return m_Etage; }
            set { m_Etage = value; }
        }

        public SizeF DimPieds
        {
            get { return m_DimPieds; }
            set { m_DimPieds = value; }
        }

        public SizeF DimMetres
        {
            get { return m_DimMetres; }
            set { m_DimMetres = value; }
        }

        public string Plancher
        {
            get { return m_Plancher; }
            set { m_Plancher = value; }
        }
        public RoomInfo(HouseDetailInfo house, string html)
        {
            m_House = house;
            string[] cells = html.Split(new String[] { "</td>" }, StringSplitOptions.None).Select(x => x.RemoveHTMLTags().Trim()).Where(x => !string.IsNullOrWhiteSpace(x)).ToArray();
            m_Name = HttpUtility.HtmlDecode(cells[0]);
            m_Etage = cells[1];
            m_Plancher = cells[3];

            string dims = cells[2];
            string[] dimsP = dims.Remove(dims.IndexOf(' ')).Split('x');
            m_DimPieds = new SizeF((float)double.Parse(dimsP[0], CultureInfo.InvariantCulture), (float)double.Parse(dimsP[1], CultureInfo.InvariantCulture));
            string[] dimsM = dims.Extract("(", " ").Split('x');
            m_DimMetres = new SizeF((float)double.Parse(dimsM[0], CultureInfo.InvariantCulture), (float)double.Parse(dimsM[1], CultureInfo.InvariantCulture));
        }
    }
}
