package com.ericmas001.duproprio.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class PictureInfo {

//	{
//		"Index" : 0,
//		"Title" : "Photo",
//		"Description" : "",
//		"ImageUrlSmall" : "http://photos.duproprio.com/photo-jumele-a-vendre-levis-quebec-province-small-3036446.jpg",
//		"ImageUrlLarge" : "http://photos.duproprio.com/photo-jumele-a-vendre-levis-quebec-province-large-3036446.jpg",
//		"ImageUrlBig" : "http://photos.duproprio.com/photo-jumele-a-vendre-levis-quebec-province-big-3036446.jpg"
//	}
	
	private int m_Index;
	private String m_Title;
	private String m_Description;
	private String m_ImageUrlSmall;
	private String m_ImageUrlLarge;
	private String m_ImageUrlBig;
	
	public int getIndex() {
		return m_Index;
	}
	public void setIndex(int index) {
		this.m_Index = index;
	}
	public String getTitle() {
		return m_Title;
	}
	public void setTitle(String title) {
		this.m_Title = title;
	}
	public String getDescription() {
		return m_Description;
	}
	public void setDescription(String description) {
		this.m_Description = description;
	}
	public String getImageUrlSmall() {
		return m_ImageUrlSmall;
	}
	public void setImageUrlSmall(String imageUrlSmall) {
		this.m_ImageUrlSmall = imageUrlSmall;
	}
	public String getImageUrlLarge() {
		return m_ImageUrlLarge;
	}
	public void setImageUrlLarge(String imageUrlLarge) {
		this.m_ImageUrlLarge = imageUrlLarge;
	}
	public String getImageUrlBig() {
		return m_ImageUrlBig;
	}
	public void setImageUrlBig(String imageUrlBig) {
		this.m_ImageUrlBig = imageUrlBig;
	}
	
	public PictureInfo(int index, String title, String description,
			String imageUrlSmall, String imageUrlLarge, String imageUrlBig) {
		super();
		this.m_Index = index;
		this.m_Title = title;
		this.m_Description = description;
		this.m_ImageUrlSmall = imageUrlSmall;
		this.m_ImageUrlLarge = imageUrlLarge;
		this.m_ImageUrlBig = imageUrlBig;
	}

	public PictureInfo() {
		super();
	}
	
	public PictureInfo(JSONObject item) throws JSONException {
		super();

		this.m_Index = item.getInt("Index");
		this.m_Title = item.getString("Title");
		this.m_Description = item.getString("Description");
		this.m_ImageUrlSmall = item.getString("ImageUrlSmall");
		this.m_ImageUrlLarge = item.getString("ImageUrlLarge");
		this.m_ImageUrlBig = item.getString("ImageUrlBig");
	}
	
	@Override
	public String toString()
	{
		return m_Index+":"+m_Title;
	}
	
	
}
