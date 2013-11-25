package com.ericmas001.duproprio.entities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HouseDetail {

//	{
//		"Pictures" : [{
//				"Index" : 0,
//				"Title" : "Photo",
//				"Description" : "",
//				"ImageUrlSmall" : "http://photos.duproprio.com/photo-jumele-a-vendre-levis-quebec-province-small-3036446.jpg",
//				"ImageUrlLarge" : "http://photos.duproprio.com/photo-jumele-a-vendre-levis-quebec-province-large-3036446.jpg",
//				"ImageUrlBig" : "http://photos.duproprio.com/photo-jumele-a-vendre-levis-quebec-province-big-3036446.jpg"
//			}, {
//				"Index" : 1,
//				"Title" : "Salon",
//				"Description" : "",
//				"ImageUrlSmall" : "http://photos.duproprio.com/salon-jumele-a-vendre-levis-quebec-province-small-2986930.jpg",
//				"ImageUrlLarge" : "http://photos.duproprio.com/salon-jumele-a-vendre-levis-quebec-province-large-2986930.jpg",
//				"ImageUrlBig" : "http://photos.duproprio.com/salon-jumele-a-vendre-levis-quebec-province-big-2986930.jpg"
//			}
//		],
//		"Rooms" : [{
//				"Name" : "Cuisine",
//				"Etage" : "Rez-de-chaussée",
//				"DimPieds" : "13, 9",
//				"DimMetres" : "3.96, 2.74",
//				"Plancher" : "Céramique"
//			}, {
//				"Name" : "Salle de bain",
//				"Etage" : "1er étage",
//				"DimPieds" : "8, 8",
//				"DimMetres" : "2.44, 2.44",
//				"Plancher" : "Céramique"
//			}
//		],
//		"HouseProperty" : [{
//				"HasMultiples" : false,
//				"Name" : "Détails chambres",
//				"Value" : "Chambre des maîtres avec walk-in"
//			}, {
//				"HasMultiples" : true,
//				"Name" : "Parement extérieur",
//				"Value" : ["Clin de vinyle"]
//			}, {
//				"HasMultiples" : true,
//				"Name" : "Équipements / Services inclus",
//				"Value" : ["Aspirateur central", "Cabanon", "Chambre froide", "Échangeur d'air", "Luminaires", "Rideaux", "Salle d'eau au r/c", "Stores", "Système d'alarme", "Walk-in"]
//			}
//		],
//		"LongHTMLDescription" : "<p><em><strong>Tr&egrave;s beau jumel&eacute; ...</strong></p>",
//		"PhoneNumber" : "418-555-2548"
//	}
	
	private String m_PhoneNumber;
	private String m_LongHTMLDescription;
	private ArrayList<PictureInfo> m_Pictures;
	private ArrayList<RoomInfo> m_Rooms;
	private ArrayList<HouseProperty> m_Properties;
	
	public String getPhoneNumber() {
		return m_PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.m_PhoneNumber = phoneNumber;
	}
	public String getLongHTMLDescription() {
		return m_LongHTMLDescription;
	}
	public void setLongHTMLDescription(String longHTMLDescription) {
		this.m_LongHTMLDescription = longHTMLDescription;
	}
	public ArrayList<PictureInfo> getPictures() {
		return m_Pictures;
	}
	public void setPictures(ArrayList<PictureInfo> pictures) {
		this.m_Pictures = pictures;
	}
	public ArrayList<RoomInfo> getRooms() {
		return m_Rooms;
	}
	public void setRooms(ArrayList<RoomInfo> rooms) {
		this.m_Rooms = rooms;
	}
	public ArrayList<HouseProperty> getProperties() {
		return m_Properties;
	}
	public void setProperties(ArrayList<HouseProperty> properties) {
		this.m_Properties = properties;
	}
	
	public HouseDetail(String phoneNumber, String longHTMLDescription, ArrayList<PictureInfo> pictures,
			ArrayList<RoomInfo> rooms, ArrayList<HouseProperty> properties) {
		super();
		this.m_PhoneNumber = phoneNumber;
		this.m_LongHTMLDescription = longHTMLDescription;
		this.m_Pictures = pictures;
		this.m_Rooms = rooms;
		this.m_Properties = properties;
	}

	public HouseDetail() {
		super();
	}
	
	public HouseDetail(JSONObject item) throws JSONException {
		super();

		this.m_PhoneNumber = item.getString("PhoneNumber");
		this.m_LongHTMLDescription = item.getString("LongHTMLDescription");
		
		this.m_Pictures = new ArrayList<PictureInfo>();
		JSONArray pics = item.getJSONArray("Pictures");
        for (int i = 0; i < pics.length(); ++i)
        	this.m_Pictures.add(new PictureInfo(pics.getJSONObject(i)));
        
		this.m_Rooms = new ArrayList<RoomInfo>();
		JSONArray rooms = item.getJSONArray("Rooms");
        for (int i = 0; i < rooms.length(); ++i)
        	this.m_Rooms.add(new RoomInfo(rooms.getJSONObject(i)));
        
		this.m_Properties = new ArrayList<HouseProperty>();
		JSONArray props = item.getJSONArray("HouseProperty");
        for (int i = 0; i < props.length(); ++i)
        	this.m_Properties.add(new HouseProperty(props.getJSONObject(i)));
	}
	
	
}
