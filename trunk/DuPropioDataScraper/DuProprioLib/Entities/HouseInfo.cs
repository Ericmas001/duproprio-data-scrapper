using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using EricUtility;

namespace DuProprioLib.Entities
{
    public class HouseInfo : HouseDetailInfo
    {
        private HouseSummary m_Summary;

        public HouseSummary Summary
        {
            get { return m_Summary; }
        }

        public HouseInfo(HouseSummary summary, string html): base(html)
        {
            m_Summary = summary;
        }
    }
}
