using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using EricUtility;
using System.Web;
using Newtonsoft.Json;

namespace DuProprioLib.Entities
{
    public class PictureInfo
    {
        private int m_Index;
        private string m_Title;
        private string m_Description;
        private string m_ImageUrlSmall;
        private string m_ImageUrlLarge;
        private string m_ImageUrlBig;
        private HouseDetailInfo m_House;

        [JsonIgnore]
        public HouseDetailInfo House
        {
            get { return m_House; }
        }

        public int Index
        {
            get { return m_Index; }
            set { m_Index = value; }
        }

        public string Title
        {
            get { return m_Title; }
            set { m_Title = value; }
        }

        public string Description
        {
            get { return m_Description; }
            set { m_Description = value; }
        }

        public string ImageUrlSmall
        {
            get { return m_ImageUrlSmall; }
            set { m_ImageUrlSmall = value; }
        }

        public string ImageUrlLarge
        {
            get { return m_ImageUrlLarge; }
            set { m_ImageUrlLarge = value; }
        }

        public string ImageUrlBig
        {
            get { return m_ImageUrlBig; }
            set { m_ImageUrlBig = value; }
        }
        public PictureInfo(HouseDetailInfo house, string html)
        {
            m_House = house;
            m_Index = int.Parse(html.Extract("data-index=\"", "\""));
            m_Title = HttpUtility.HtmlDecode(html.Extract("data-title=\"", "\""));
            m_Description = HttpUtility.HtmlDecode(html.Extract("data-desc=\"", "\""));
            m_ImageUrlSmall = html.Extract("data-small=\"", "\"");
            m_ImageUrlLarge = html.Extract("data-large=\"", "\"");
            m_ImageUrlBig = html.Extract("data-big=\"", "\"");
        }
    }
}
