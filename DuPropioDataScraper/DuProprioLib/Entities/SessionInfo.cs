using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using EricUtility;
using System.Drawing;
using System.IO;
using EricUtility.Networking.Gathering;

namespace DuProprioLib.Entities
{
    public class SessionInfo
    {
        private string m_Username;
        private string m_Password;

        private CookieContainer m_Cookies;
        private HttpClient m_Client;

        private bool m_Connected = false;

        public bool Connected
        {
            get { return m_Connected; }
        }

        public SessionInfo(string user, string pass)
        {
            m_Username = user;
            m_Password = pass;
        }

        private async Task<bool> Connect()
        {
            m_Cookies = new CookieContainer();
            m_Client = new HttpClient(new HttpClientHandler() { CookieContainer = m_Cookies });

            string URI = "https://duproprio.com/connecter";
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Post, URI);

            request.Headers.Host = "duproprio.com";

            //Accept: text/plain, */*; q=0.01
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("text/plain"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*", 0.01));

            //Accept-Language: fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3
            request.Headers.AcceptLanguage.Add(new StringWithQualityHeaderValue("fr"));
            request.Headers.AcceptLanguage.Add(new StringWithQualityHeaderValue("fr-fr", 0.8));
            request.Headers.AcceptLanguage.Add(new StringWithQualityHeaderValue("en-us", 0.5));
            request.Headers.AcceptLanguage.Add(new StringWithQualityHeaderValue("en", 0.5));

            //Accept-Encoding: gzip, deflate
            request.Headers.AcceptEncoding.Add(new StringWithQualityHeaderValue("gzip"));
            request.Headers.AcceptEncoding.Add(new StringWithQualityHeaderValue("deflate"));

            //Content-Type: application/x-www-form-urlencoded; charset=UTF-8
            request.Content = new FormUrlEncodedContent(new[]
            {
                new KeyValuePair<string, string>("username", m_Username),
                new KeyValuePair<string, string>("password", m_Password),
                new KeyValuePair<string, string>("rememberme", "on"),
            });
            //request.Content = new StreamContent(new MemoryStream(System.Text.Encoding.UTF8.GetBytes(args)));
            request.Content.Headers.ContentType = new MediaTypeWithQualityHeaderValue("application/x-www-form-urlencoded");

            //User-Agent: Mozilla/5.0 (Windows NT 6.2; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0
            request.Headers.Add("user-agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0");

            //X-Requested-With: XMLHttpRequest
            request.Headers.Add("X-Requested-With", "XMLHttpRequest");

            request.Headers.Referrer = new Uri(URI);

            HttpResponseMessage result = await m_Client.SendAsync(request);


            if (result.IsSuccessStatusCode) 
                m_Connected = (await m_Client.GetStringAsync("http://duproprio.com")).Contains("<li class=\"logout\">");

            return m_Connected;
        }

        public async Task<IEnumerable<HouseSummary>> GetFavs()
        {
            if (!m_Connected)
                await Connect();
            if (!m_Connected)
                return null;
            // Get all favs
            IEnumerable<string> liste = (await m_Client.GetStringAsync("http://duproprio.com/my-account/dashboard-buyer/bookmarks")).Extract("<ul id=\"favouritesResults\">", "</ul>").Split(new String[] { "</li>" }, StringSplitOptions.None);

            // Trim and delete empty entries
            liste = liste.Select(x => x.Trim()).Where(x => !string.IsNullOrWhiteSpace(x));

            // Get Rid of the Sold ones
            liste = liste.Where(x => !x.Contains("<div class=\"infoSold\">"));

            return liste.Select(x => new HouseSummary(this, x));
        }

        public async Task<HouseInfo> GetHouseInfo(HouseSummary summary)
        {
            if (!m_Connected)
                await Connect();
            if (!m_Connected)
                return null;

            // Get Page
            return new HouseInfo(summary, await m_Client.GetStringAsync(summary.DetailsURL));
        }
    }
}
