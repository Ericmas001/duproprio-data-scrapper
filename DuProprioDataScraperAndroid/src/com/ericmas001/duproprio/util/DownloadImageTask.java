package com.ericmas001.duproprio.util;

import java.io.InputStream;
import java.util.HashMap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    
    public static HashMap<String,Bitmap> cache = new HashMap<String,Bitmap>();

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        if(cache.containsKey(urldisplay))
        	return cache.get(urldisplay);
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        int bytes = GetBytes(mIcon11);
        if (android.os.Build.VERSION.SDK_INT >= 19)
        	bytes = GetBytes19(mIcon11);
        if(bytes < 100000)
            cache.put(urldisplay, mIcon11);
        return mIcon11;
    }

    public int GetBytes(Bitmap b)
    {
    	return b.getByteCount();
    }
    
    @TargetApi(19)
    public int GetBytes19(Bitmap b)
    {
    	return b.getAllocationByteCount();
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
