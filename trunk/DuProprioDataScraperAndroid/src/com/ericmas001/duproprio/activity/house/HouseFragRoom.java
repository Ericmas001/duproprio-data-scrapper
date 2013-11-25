package com.ericmas001.duproprio.activity.house;

import com.ericmas001.duproprio.adapter.RoomXpListAdp;

public class HouseFragRoom extends HouseFragXpList<RoomXpListAdp> 
{
	@Override
	protected RoomXpListAdp getAdapter() 
	{
		return new RoomXpListAdp(getActivity(),m_House.getDetails().getRooms());
	}
}
