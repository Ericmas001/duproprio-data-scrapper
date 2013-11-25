package com.ericmas001.duproprio.activity.house;

import com.ericmas001.duproprio.adapter.PropertyXpListAdp;

public class HouseFragProperty extends HouseFragXpList<PropertyXpListAdp> 
{
	@Override
	protected PropertyXpListAdp getAdapter() 
	{
		return new PropertyXpListAdp(getActivity(),m_House.getDetails().getProperties());
	}
}
