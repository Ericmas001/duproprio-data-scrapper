package com.ericmas001.duproprio.activity.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class HouseFragDummy extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String ARG_NO_ANNONCE = "no_annonce";

	public HouseFragDummy() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_house_dummy,container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
		int sec = getArguments().getInt(ARG_SECTION_NUMBER);
		int no = getArguments().getInt(ARG_NO_ANNONCE);
		HouseSummary hs = HouseList.ITEM_MAP.get(no);
		dummyTextView.setText("Section #" + sec + " of house #" + hs.getNoAnnonce());
		return rootView;
	}

	
}
