package com.ericmas001.duproprio.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.entities.PictureInfo;
import com.ericmas001.duproprio.util.DownloadImageTask;
/**
 * 
 * @author javatechig {@link http://javatechig.com}
 * 
 */
public class GalleryGridViewAdp extends ArrayAdapter<PictureInfo> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<PictureInfo> data = new ArrayList<PictureInfo>();
 
    public GalleryGridViewAdp(Context context, int layoutResourceId,
            ArrayList<PictureInfo> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
 
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
 
        PictureInfo item = (PictureInfo)data.get(position);
        holder.imageTitle.setText(item.getTitle());
		new DownloadImageTask(holder.image).execute(item.getImageUrlSmall());
        return row;
    }
 
    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}
