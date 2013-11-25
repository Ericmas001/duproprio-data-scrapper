package com.ericmas001.duproprio.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.adapter.HouseXpListAdp;
import com.ericmas001.duproprio.entities.HouseDetail;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;
import com.ericmas001.duproprio.util.ContactWebservice;
 
public class HouseSummaryActivity extends Activity {
 
    HouseXpListAdp listAdapter;
    ExpandableListView expListView;
	ProgressDialog mPDialog;
	HouseSummary mHouse = null;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_summary);
 
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new HouseXpListAdp(this,HouseList.ITEMS);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);// Listview on child click listener

        
        for (int i = 0; i < listAdapter.getGroupCount(); i++)
        	expListView.expandGroup(i);
        
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
            	
            	mHouse = (HouseSummary)listAdapter.getChild(groupPosition, childPosition);

        		

        		if( mHouse.getDetails() == null)
        		{
        			// Show a progress spinner, and kick off a background task to
        			// perform getting the details.
        			mPDialog = ProgressDialog.show(HouseSummaryActivity.this, "", "Loading. Please wait...", true);
        			
        			ContactWebservice.CallWS(HouseSummaryActivity.this, "onPostExecute", "http://ws.ericmas001.com/duproprio/user/house/"+mHouse.getNoAnnonce());
        		}
        		else
        		{
                	Intent intent = new Intent(HouseSummaryActivity.this, HouseActivity.class);
                	intent.putExtra("ANNONCE_NO", mHouse.getNoAnnonce());
                	startActivityForResult(intent,0); 
        		}
                return false;
            }
        });
    }
    public void onPostExecute(String result, Exception exception)
    {			
        if (result != null && !result.isEmpty())
        {
            try
            {
            	mHouse.setDetails(new HouseDetail(new JSONObject(result)));
        		
            	Intent intent = new Intent(HouseSummaryActivity.this, HouseActivity.class);
            	intent.putExtra("ANNONCE_NO", mHouse.getNoAnnonce());
            	startActivityForResult(intent,0);  
            }
            catch (JSONException e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        mPDialog.dismiss();
    }
}