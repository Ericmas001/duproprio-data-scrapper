package com.ericmas001.duproprio.activity.house;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericmas001.duproprio.R;
import com.ericmas001.duproprio.activity.HouseActivity;
import com.ericmas001.duproprio.entities.HouseDetail;
import com.ericmas001.duproprio.entities.HouseList;
import com.ericmas001.duproprio.entities.HouseSummary;
import com.ericmas001.duproprio.util.DownloadImageTask;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class HouseFragInfo extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */

	public HouseFragInfo() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_house_info,container, false);

		ImageView imgVignette = (ImageView) v.findViewById(R.id.imgVignette);
		TextView lblNoAnnonce = (TextView) v.findViewById(R.id.lblNoAnnonce);
		TextView lblPrecision = (TextView) v.findViewById(R.id.lblPrecision);
		TextView lblTitle = (TextView) v.findViewById(R.id.lblTitle);
		TextView lblAdresse = (TextView) v.findViewById(R.id.lblAdresse);
		TextView lblVille = (TextView) v.findViewById(R.id.lblVille);
		TextView lblRegion = (TextView) v.findViewById(R.id.lblRegion);
		TextView lblShortDescription = (TextView) v.findViewById(R.id.lblShortDescription);
		WebView webLongDescription = (WebView) v.findViewById(R.id.webLongDescription);
		
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

		symbols.setGroupingSeparator(' ');
		
		int no = getArguments().getInt(HouseActivity.ARG_NO_ANNONCE);
		HouseSummary hs = HouseList.ITEM_MAP.get(no);
		lblNoAnnonce.setText("#" + hs.getNoAnnonce());
		lblPrecision.setText(hs.getPrecison());
		lblTitle.setText(formatter.format(hs.getPrix()) + "$ - " + hs.getType());
		lblAdresse.setText(hs.getAdresse());
		lblVille.setText(hs.getVille());
		lblRegion.setText(hs.getRegion());
		lblShortDescription.setText(hs.getSummary());
		HouseDetail hd = hs.getDetails();
		if(hd != null)
		{
			webLongDescription.loadDataWithBaseURL(null, hd.getLongHTMLDescription(), "text/html", "UTF-8", null);
			webLongDescription.setBackgroundColor(Color.argb(1, 0, 0, 0));
			webLongDescription.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
			webLongDescription.setVisibility(View.VISIBLE);
		}
		else
		{
			webLongDescription.setVisibility(View.INVISIBLE);
		}
		new DownloadImageTask(imgVignette).execute(hs.getImageURL());
		return v;
	}

	
}
