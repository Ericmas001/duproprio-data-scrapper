package com.ericmas001.duproprio.adapter;
 
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.entities.HouseSummary;
import com.ericmas001.duproprio.util.DownloadImageTask;
 
public class HouseXpListAdp extends BaseExpandableListAdapter {
 
    private Context _context;
 
    private LayoutInflater layoutInflater;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<HouseSummary>> _listDataChild;
 
    public HouseXpListAdp(Context context, List<HouseSummary> iTEMS) {
        this._context = context;
        ArrayList<String> lst = new ArrayList<String>();
        this._listDataHeader = lst;
        this._listDataChild = new HashMap<String, ArrayList<HouseSummary>>();
        for(HouseSummary hs : iTEMS)
        {
        	if( !lst.contains(hs.getVille()))
        	{
        		lst.add(hs.getVille());
        		this._listDataChild.put(hs.getVille(), new ArrayList<HouseSummary>());
        	}
        	this._listDataChild.get(hs.getVille()).add(hs);
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
		HouseSummary hs = (HouseSummary)getChild(groupPosition, childPosition);
        if (convertView == null) {
        	convertView = layoutInflater.inflate(R.layout.house_list_row_layout, null);
            holder = new ViewHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.summaryView = (TextView) convertView.findViewById(R.id.summary);
            holder.noAnnonceView = (TextView) convertView.findViewById(R.id.noAnnonce);
            holder.precisionView = (TextView) convertView.findViewById(R.id.precision);
            holder.vignetteView = (ImageView) convertView.findViewById(R.id.vignette);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		symbols.setGroupingSeparator(' ');
        holder.titleView.setText(formatter.format(hs.getPrix()) + "$ - " + hs.getType() + " - " + hs.getAdresse());
        holder.summaryView.setText(hs.getVille() + "; " + hs.getSummary());
        holder.noAnnonceView.setText("#"+hs.getNoAnnonce());
        holder.precisionView.setText(hs.getPrecison());
		new DownloadImageTask(holder.vignetteView).execute(hs.getImageURL());
        
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
        TextView summaryView;
        TextView noAnnonceView;
        TextView precisionView;
        ImageView vignetteView;
    }
}