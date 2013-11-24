using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using EricUtility;
using System.Web;

namespace DuProprioLib.Entities
{
    public class HouseDetailInfo
    {
        private string m_PhoneNumber;
        private string m_LongHTMLDescription;
        private IEnumerable<IHouseProperty> m_HouseProperty;
        private IEnumerable<RoomInfo> m_Rooms;
        private IEnumerable<PictureInfo> m_Pictures;

        public IEnumerable<PictureInfo> Pictures
        {
            get { return m_Pictures; }
            set { m_Pictures = value; }
        }

        public IEnumerable<RoomInfo> Rooms
        {
            get { return m_Rooms; }
            set { m_Rooms = value; }
        }

        public IEnumerable<IHouseProperty> HouseProperty
        {
            get { return m_HouseProperty; }
            set { m_HouseProperty = value; }
        }

        public string LongHTMLDescription
        {
            get { return m_LongHTMLDescription; }
            set { m_LongHTMLDescription = value; }
        }

        public string PhoneNumber
        {
            get { return m_PhoneNumber; }
            set { m_PhoneNumber = value; }
        }

        public HouseDetailInfo(string html)
        {

           
            m_LongHTMLDescription = html.Extract("<div class=\"box boxSmall\" id=\"remarques\">", "<div class=\"box boxSmall\" id=\"details\">").Extract("<div class=\"content\">", "</div>").Replace(" target=\"_blank\"","").Trim();
            m_PhoneNumber = html.Extract("<span itemprop=\"telephone\">", "</span>");
            
            // Get all pics
            IEnumerable<string> listePics = html.Extract("<ol class=\"photosList\">", "</ol>").Split(new String[] { "</li>" }, StringSplitOptions.None);
            listePics = listePics.Select(x => x.Trim()).Where(x => !string.IsNullOrWhiteSpace(x));
            m_Pictures = listePics.Select(x => new PictureInfo(this, x));

            //Get all Rooms
            string rooms = html.Extract("<div class=\"box boxSmall\" id=\"dimensions\">", "</table>");
            if (rooms == null)
                m_Rooms = new RoomInfo[0];
            else
            {
                IEnumerable<string> listeRooms = rooms.Split(new String[] { "</tr>" }, StringSplitOptions.None);
                listeRooms = listeRooms.Select(x => x.Trim()).Where(x => !string.IsNullOrWhiteSpace(x) && x.Contains("</td>"));
                m_Rooms = listeRooms.Select(x => new RoomInfo(this, x));
            }

            //Get all Properties
            string divSingles = html.Extract("<div class=\"box boxSmall\" id=\"details\">", "<div id=\"otherDetails\">");
            IEnumerable<string> singleLeft = divSingles.Extract("<ul class=\"left\">", "</ul>").Split(new String[] { "/li>" }, StringSplitOptions.None);
            IEnumerable<string> singleRight = divSingles.Extract("<ul class=\"right\">", "</ul>").Split(new String[] { "/li>" }, StringSplitOptions.None);
            IEnumerable<string> listeSingles = singleLeft.Union(singleRight).Select(x => x.Trim()).Where(x => !string.IsNullOrWhiteSpace(x));
            IEnumerable<IHouseProperty> listeSinglesP = listeSingles.Select(x => new SingleHouseProperty(this, x.Extract("<strong>", " :</strong>"), x.Extract("</strong> ", "<")));

            string divList = html.Extract("<div id=\"otherDetails\">", "</div>") + "</div>";
            IEnumerable<string> listLeft = divList.Extract("<ul class=\"left\">", "<ul class=\"right\">").Split(new String[] { " </li>" }, StringSplitOptions.None);
            IEnumerable<string> listRight = divList.Extract("<ul class=\"right\">", "</div>").Split(new String[] { " </li>" }, StringSplitOptions.None);
            IEnumerable<string> listeLists = listLeft.Union(listRight).Select(x => x.Replace("</ul>", "").Trim() + " ").Where(x => !string.IsNullOrWhiteSpace(x));
            IEnumerable<IHouseProperty> listeListsP = listeLists.Select(x => new ListHouseProperty(this, x.Extract("<strong>", " :</strong>"), x.Extract(" <li>", "</li> ").Split(new String[] { "</li><li>" }, StringSplitOptions.None)));

            m_HouseProperty = listeSinglesP.Union(listeListsP);
        }
    }
}
