package com.ericmas001.duproprio.adapter;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.entities.HouseProperty;
 
public class PropertyXpListAdp extends BaseExpandableListAdapter {
 
    private Context _context;
    private final String GENERAL = "General";
 
    private LayoutInflater layoutInflater;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<String>> _listDataChild;
 
    public PropertyXpListAdp(Context context, List<HouseProperty> iTEMS) {
        this._context = context;
        ArrayList<String> lst = new ArrayList<String>();
        this._listDataHeader = lst;
        this._listDataChild = new HashMap<String, ArrayList<String>>();
		lst.add(GENERAL);
		this._listDataChild.put(GENERAL, new ArrayList<String>());
        
        for(HouseProperty hp : iTEMS)
        {
        	if( hp.hasMultipleValues() )
        	{
        		lst.add(hp.getName());
        		this._listDataChild.put(hp.getName(), hp.getValues());
        	}
        	else
        		this._listDataChild.get(GENERAL).add(hp.getName() +": "+hp.getValue());
        }
        layoutInflater = LayoutInflater.from(context);
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        ViewHolder holder;
        String s = (String)getChild(groupPosition, childPosition);
        if (convertView == null) {
        	convertView = layoutInflater.inflate(R.layout.house_property_list_row_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.lblName);
            holder.valueView = (TextView) convertView.findViewById(R.id.lblValue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        if(s.contains(":"))
        {
        	holder.nameView.setText(s.substring(0, s.indexOf(":")+1));
        	holder.valueView.setText(s.substring(s.indexOf(":")+1, s.length()));
        }
        else
        {
        	holder.nameView.setText("");
        	holder.valueView.setText(s);
        }
        
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.xplist_header_layout, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    
    static class ViewHolder {
        TextView nameView;
        TextView valueView;
    }
}