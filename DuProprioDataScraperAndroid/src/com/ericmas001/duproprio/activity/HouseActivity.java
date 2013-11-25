package com.ericmas001.duproprio.activity;

import java.util.Locale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.activity.house.HouseFragDummy;
import com.ericmas001.duproprio.activity.house.HouseFragGallery;
import com.ericmas001.duproprio.activity.house.HouseFragInfo;
import com.ericmas001.duproprio.activity.house.HouseFragProperty;
import com.ericmas001.duproprio.activity.house.HouseFragRoom;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;

public class HouseActivity extends FragmentActivity {

	public static final String ARG_NO_ANNONCE = "no_annonce";
	
	private class HouseTab
	{
		public Fragment Fragment;
		public int TitleId;
		
		public HouseTab(Fragment fragment, int titleId) {
			super();
			Fragment = fragment;
			TitleId = titleId;
		}
		
	}
	private HouseTab[] m_Tabs = new HouseTab[]
	{
		new HouseTab(new HouseFragInfo(), R.string.sec_info),
		new HouseTab(new HouseFragRoom(), R.string.sec_pieces),
		new HouseTab(new HouseFragProperty(), R.string.sec_caracts),
		new HouseTab(new HouseFragGallery(), R.string.sec_photos),
	};
	
	private SectionsPagerAdapter m_SectionsPagerAdapter;
	private ViewPager m_ViewPager;
	private HouseSummary m_House = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null)
			m_House = HouseList.ITEM_MAP.get(extras.getInt("ANNONCE_NO"));

		setTitle("#"+ m_House.getNoAnnonce());
		getActionBar().setDisplayHomeAsUpEnabled(true);

		m_SectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		m_ViewPager = (ViewPager) findViewById(R.id.pager);
		m_ViewPager.setAdapter(m_SectionsPagerAdapter);
	}
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.house, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case android.R.id.home:
			{
				finish();
				return true;
			}
			case R.id.menu_open_gmap:
			{
				if( m_House != null )
				{
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(m_House.getGMapURL()));
					startActivityForResult(intent,-1);
				}
				return true;
			}
			case R.id.menu_call_owner:
			{
				if( m_House != null && m_House.getDetails() != null )
				{
					Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+m_House.getDetails().getPhoneNumber()));
					startActivityForResult(intent,-1);
				}
				return true;
			}
			case R.id.menu_open_website:
			{
				if( m_House != null )
				{
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(m_House.getDetailsURL()));
					startActivityForResult(intent,-1);
				}
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) 
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position) 
		{
			Fragment fragment = new HouseFragDummy();
			if( position >= 0 && position < m_Tabs.length)
				fragment = m_Tabs[position].Fragment;
			Bundle args = new Bundle();
			args.putInt(ARG_NO_ANNONCE, m_House.getNoAnnonce());
			args.putInt(HouseFragDummy.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() 
		{
			return m_Tabs.length;
		}

		@Override
		public CharSequence getPageTitle(int position) 
		{
			Locale l = Locale.getDefault();
			if( position >= 0 && position < m_Tabs.length)
				return getString( m_Tabs[position].TitleId).toUpperCase(l);
			return null;
		}
	}

}
