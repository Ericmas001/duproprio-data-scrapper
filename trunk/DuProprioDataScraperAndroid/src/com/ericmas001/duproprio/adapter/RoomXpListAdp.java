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
import com.ericmas001.duproprio.entities.RoomInfo;
 
public class RoomXpListAdp extends BaseExpandableListAdapter {
 
    private Context _context;
 
    private LayoutInflater layoutInflater;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<RoomInfo>> _listDataChild;
 
    public RoomXpListAdp(Context context, List<RoomInfo> iTEMS) {
        this._context = context;
        ArrayList<String> lst = new ArrayList<String>();
        this._listDataHeader = lst;
        this._listDataChild = new HashMap<String, ArrayList<RoomInfo>>();
        for(RoomInfo ri : iTEMS)
        {
        	if( !lst.contains(ri.getEtage()))
        	{
        		lst.add(ri.getEtage());
        		this._listDataChild.put(ri.getEtage(), new ArrayList<RoomInfo>());
        	}
        	this._listDataChild.get(ri.getEtage()).add(ri);
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
		RoomInfo ri = (RoomInfo)getChild(groupPosition, childPosition);
        if (convertView == null) {
        	convertView = layoutInflater.inflate(R.layout.room_list_row_layout, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.lblTitle);
            holder.dimsView = (TextView) convertView.findViewById(R.id.lblDimensions);
            holder.floorView = (TextView) convertView.findViewById(R.id.lblFloor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.titleView.setText(ri.getName());
        holder.dimsView.setText(ri.getDimPieds().mWidth + "pi X " + ri.getDimPieds().mHeight+ "pi  /  "+ri.getDimMetres().mWidth + "m X " + ri.getDimMetres().mHeight + "m");
        holder.floorView.setText(ri.getPlancher());
        
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
        TextView titleView;
        TextView dimsView;
        TextView floorView;
    }
}