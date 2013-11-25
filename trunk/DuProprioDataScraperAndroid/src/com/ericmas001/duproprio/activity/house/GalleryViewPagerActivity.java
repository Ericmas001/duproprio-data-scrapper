package com.ericmas001.duproprio.activity.house;
 
import java.util.List;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.adapter.GalleryPagerAdp;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;
import com.ericmas001.duproprio.entities.PictureInfo;
 
/**
 * The <code>ViewPagerFragmentActivity</code> class is the fragment activity hosting the ViewPager
 * @author mwho
 */
public class GalleryViewPagerActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    /** maintains the pager adapter*/
    private GalleryPagerAdp mPagerAdapter;
	HouseSummary mHouse = null;
    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_viewpager_layout);
		Bundle extras = getIntent().getExtras();
		int pos = 0;
		if (extras != null) {
			mHouse = HouseList.ITEM_MAP.get(extras.getInt("ANNONCE_NO"));
			pos = extras.getInt("POS");
		}
        //initialsie the pager
        this.initialisePaging(pos);
    }
 
    /**
     * Initialise the fragments to be paged
     */
    @SuppressLint("ShowToast")
	private void initialisePaging(int pos) {
 
        List<Fragment> fragments = new Vector<Fragment>();			
        for(int i = 0; i < mHouse.getDetails().getPictures().size(); ++i)
        {
	        Fragment fragment = new GalleryFragViewPager();
			Bundle args = new Bundle();
			args.putInt(GalleryFragViewPager.ARG_SECTION_NUMBER, i);
			args.putInt(GalleryFragViewPager.ARG_NO_ANNONCE, mHouse.getNoAnnonce());
			fragment.setArguments(args);
			fragments.add(fragment);
        }
		
        this.mPagerAdapter  = new GalleryPagerAdp(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.mPagerAdapter);
        pager.setOnPageChangeListener(this);
        pager.setCurrentItem(pos);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int i) {
		if( HouseList.GLOBAL_TOAST != null)
		{
			HouseList.GLOBAL_TOAST.cancel();
			HouseList.GLOBAL_TOAST = null;
		}
		PictureInfo pic = mHouse.getDetails().getPictures().get(i);
		setTitle(pic.getTitle());
		if(!pic.getDescription().isEmpty())
		{
			HouseList.GLOBAL_TOAST = Toast.makeText( this  , pic.getDescription() , Toast.LENGTH_LONG );
			HouseList.GLOBAL_TOAST.show();
		}
	}
}