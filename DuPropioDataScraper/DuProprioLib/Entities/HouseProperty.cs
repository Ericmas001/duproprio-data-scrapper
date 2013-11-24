using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DuProprioLib.Entities
{
    public interface IHouseProperty : IComparable<IHouseProperty>
    {
        string Name { get; }
        object Value { get; }

        [JsonIgnore]
        HouseDetailInfo House { get; }
    }
    public class SingleHouseProperty : Tuple<string, string>, IHouseProperty
    {
        private HouseDetailInfo m_House;

        [JsonIgnore]
        public HouseDetailInfo House
        {
            get { return m_House; }
        }
        public string Name { get { return this.Item1; } }
        public object Value { get { return this.Item2; } }

        public SingleHouseProperty(HouseDetailInfo house, string name, string value)
            : base(name, value)
        {
            m_House = house;
        }

        public int CompareTo(IHouseProperty other)
        {
            return Name.CompareTo(other.Name);
        }
    }
    public class ListHouseProperty : Tuple<string, IEnumerable<string>>, IHouseProperty
    {
        private HouseDetailInfo m_House;

        [JsonIgnore]
        public HouseDetailInfo House
        {
            get { return m_House; }
        }
        public string Name { get { return this.Item1; } }
        public object Value { get { return this.Item2; } }

        public ListHouseProperty(HouseDetailInfo house, string name, IEnumerable<string> values)
            : base(name, values)
        {
            m_House = house;
        }

        public int CompareTo(IHouseProperty other)
        {
            return Name.CompareTo(other.Name);
        }
    }
}
