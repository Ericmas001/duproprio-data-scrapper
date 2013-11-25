package com.ericmas001.duproprio.activity.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.activity.HouseActivity;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;

public abstract class HouseFragXpList<TAdapter extends BaseExpandableListAdapter> extends Fragment {

	protected abstract TAdapter getAdapter();
	
	private TAdapter m_ListAdapter;
    private ExpandableListView m_ExpListView;
    protected HouseSummary m_House = null;

	public HouseFragXpList() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_house_xplist,container, false);
		int no = getArguments().getInt(HouseActivity.ARG_NO_ANNONCE);
		m_House = HouseList.ITEM_MAP.get(no);


        m_ExpListView = (ExpandableListView) v.findViewById(R.id.lvExp);
        m_ListAdapter = getAdapter();
        m_ExpListView.setAdapter(m_ListAdapter);
        
        for (int i = 0; i < m_ListAdapter.getGroupCount(); i++)
        	m_ExpListView.expandGroup(i);
		
		return v;
	}

	
}
