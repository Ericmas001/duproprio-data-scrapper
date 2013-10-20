using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using EricUtility;
using System.Globalization;
using System.Web;
using System.Drawing;
using EricUtility.Networking.Gathering;
using System.Net.Http;

namespace DuPropioDataScraper
{
    public class FavItemSummary
    {
        private int m_NoAnnonce;
        private double m_Latitude;
        private double m_Longitude;
        private string m_DetailsURL;
        private string m_Type;
        private string m_Ville;
        private string m_Region;
        private string m_Adresse;
        private string m_ImageURL;
        private int m_Prix;
        private string m_Precison;
        private string m_Summary;

        public int NoAnnonce
        {
            get { return m_NoAnnonce; }
            set { m_NoAnnonce = value; }
        }

        public double Latitude
        {
            get { return m_Latitude; }
            set { m_Latitude = value; }
        }

        public double Longitude
        {
            get { return m_Longitude; }
            set { m_Longitude = value; }
        }

        public string DetailsURL
        {
            get { return m_DetailsURL; }
            set { m_DetailsURL = value; }
        }

        public string Type
        {
            get { return m_Type; }
            set { m_Type = value; }
        }

        public string Ville
        {
            get { return m_Ville; }
            set { m_Ville = value; }
        }

        public string Region
        {
            get { return m_Region; }
            set { m_Region = value; }
        }

        public string Adresse
        {
            get { return m_Adresse; }
            set { m_Adresse = value; }
        }

        public string ImageURL
        {
            get { return m_ImageURL; }
            set { m_ImageURL = value; }
        }

        public int Prix
        {
            get { return m_Prix; }
            set { m_Prix = value; }
        }

        public string Precison
        {
            get { return m_Precison; }
            set { m_Precison = value; }
        }

        public string Summary
        {
            get { return m_Summary; }
            set { m_Summary = value; }
        }

        public string GMapURL
        {
            get { return String.Format(CultureInfo.InvariantCulture, "https://maps.google.com/maps?q={0},{1}", m_Latitude, m_Longitude); }
        }

        public override string ToString()
        {
            return "#" + m_NoAnnonce + " - " + m_Prix.ToString("C0") + " - " + m_Ville + ", " + m_Type + ", " + m_Adresse;
        }

        public FavItemSummary(string html)
        {
            m_NoAnnonce = int.Parse(html.Extract("data-code=\"", "\"").Trim());

            string[] latLong = html.Extract("rel=\"", "\"").Split('|');
            m_Latitude = double.Parse(latLong[0].Trim(), NumberStyles.Any, NumberFormatInfo.InvariantInfo);
            m_Longitude = double.Parse(latLong[1].Trim(), NumberStyles.Any, NumberFormatInfo.InvariantInfo);

            string firstLink = html.Extract("<a",">");
            m_DetailsURL = "http://duproprio.com" + firstLink.Extract("href=\"", "\"");

            string title = firstLink.Extract("title=\"", "\"");
            int iRegion = title.IndexOf(",") +1 ;
            int iVille = title.LastIndexOf('à', iRegion);
            int iAdresse = title.IndexOf(",", iRegion);

            m_Type = title.Remove(iVille).Trim();
            m_Ville = title.Substring(iVille + 1, (iRegion - iVille) -2).Trim();
            m_Region = title.Substring(iRegion, iAdresse - iRegion).Trim();
            m_Adresse = HttpUtility.HtmlDecode(title.Substring(iAdresse + 1).Replace("., ","").Trim());

            m_ImageURL = html.Extract("<img class=\"showimage-house\" src=\"", "\"");

            string info = html.Extract("itemprop=\"significantLink\">", "</h4>");
            m_Prix = int.Parse(info.Extract(" - ","$").Replace(" ",""));
            m_Precison = info.Extract("<span class=\"precision\">", "</span>");
            if (m_Precison != null)
                m_Precison = HttpUtility.HtmlDecode(m_Precison);

            m_Summary = html.Extract("<p class=\"resultMeta\">", "</p>").RemoveHTMLTags();
        }
    }
}
