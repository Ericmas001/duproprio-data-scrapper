package com.ericmas001.duproprio.activity.house;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.activity.HouseActivity;
import com.ericmas001.duproprio.adapter.GalleryGridViewAdp;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class HouseFragGallery extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	

    private GridView gridView;
    private GalleryGridViewAdp customGridAdapter;
	HouseSummary mHouse;

	public HouseFragGallery() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_house_gallery,container, false);
		int no = getArguments().getInt(HouseActivity.ARG_NO_ANNONCE);
		mHouse = HouseList.ITEM_MAP.get(no);
		
        gridView = (GridView) v.findViewById(R.id.gridView);
        customGridAdapter = new GalleryGridViewAdp(getActivity(), R.layout.gallery_cell_layout, mHouse.getDetails().getPictures());
        gridView.setAdapter(customGridAdapter);
        
        gridView.setOnItemClickListener(new OnItemClickListener() {
        	
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	
            	Intent intent = new Intent(getActivity(), GalleryViewPagerActivity.class);
            	intent.putExtra("ANNONCE_NO", mHouse.getNoAnnonce());
            	intent.putExtra("POS", position);
            	startActivityForResult(intent,0);      

            }
        });

		return v;
	}
	
}
