package com.ericmas001.duproprio.activity.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;
import com.ericmas001.duproprio.entities.PictureInfo;
import com.ericmas001.duproprio.util.DownloadImageTask;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class GalleryFragViewPager extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static final String ARG_NO_ANNONCE = "no_annonce";

	HouseSummary mHouse;
	PictureInfo mPic;
	
	public GalleryFragViewPager() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_gallery,container, false);
		ImageView dummyImageView = (ImageView) rootView.findViewById(R.id.imageView1);
		int sec = getArguments().getInt(ARG_SECTION_NUMBER);
		int no = getArguments().getInt(ARG_NO_ANNONCE);
		mHouse = HouseList.ITEM_MAP.get(no);
		mPic = mHouse.getDetails().getPictures().get(sec);
		
		new DownloadImageTask(dummyImageView).execute(mPic.getImageUrlLarge());
		
		dummyImageView.setOnClickListener(new View.OnClickListener() {

	        @Override
	        public void onClick(View v) {
	    		if( HouseList.GLOBAL_TOAST != null)
	    		{
	    			HouseList.GLOBAL_TOAST.cancel();
	    			HouseList.GLOBAL_TOAST = null;
	    		}
	    		if(!mPic.getDescription().isEmpty())
	    		{
	    			HouseList.GLOBAL_TOAST = Toast.makeText(GalleryFragViewPager.this.getActivity(), mPic.getDescription(), Toast.LENGTH_LONG);
	    			HouseList.GLOBAL_TOAST.show();
	    		}

	        }
	    });
		return rootView;
	}

	
}
