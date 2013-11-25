package com.ericmas001.duproprio.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class RoomInfo {

//	{
//		"Name" : "Salle d'eau",
//		"Etage" : "Rez-de-chaussée",
//		"DimPieds" : "8.3, 6.1",
//		"DimMetres" : "2.53, 1.86",
//		"Plancher" : "Céramique"
//	}
	
	private String m_Name;
	private String m_Etage;
	private Size m_DimPieds;
	private Size m_DimMetres;
	private String m_Plancher;
	
	public String getName() {
		return m_Name;
	}
	public void setName(String name) {
		this.m_Name = name;
	}
	public String getEtage() {
		return m_Etage;
	}
	public void setEtage(String etage) {
		this.m_Etage = etage;
	}
	public Size getDimPieds() {
		return m_DimPieds;
	}
	public void setDimPieds(Size dimPieds) {
		this.m_DimPieds = dimPieds;
	}
	public Size getDimMetres() {
		return m_DimMetres;
	}
	public void setDimMetres(Size dimMetres) {
		this.m_DimMetres = dimMetres;
	}
	public String getPlancher() {
		return m_Plancher;
	}
	public void setPlancher(String plancher) {
		this.m_Plancher = plancher;
	}
	
	public RoomInfo(String name, String etage, Size dimPieds,
			Size dimMetres, String plancher) {
		super();
		this.m_Name = name;
		this.m_Etage = etage;
		this.m_DimPieds = dimPieds;
		this.m_DimMetres = dimMetres;
		this.m_Plancher = plancher;
	}

	public RoomInfo() {
		super();
	}
	
	public RoomInfo(JSONObject item) throws JSONException {
		super();

		this.m_Name = item.getString("Name");
		this.m_Etage = item.getString("Etage");
		this.m_DimPieds = new Size(item.getString("DimPieds"));
		this.m_DimMetres = new Size(item.getString("DimMetres"));
		this.m_Plancher = item.getString("Plancher");
	}
	
	@Override
	public String toString()
	{
		return m_Name;
	}
	
	public class Size { 
		  public final double mWidth; 
		  public final double mHeight; 
		  public Size(double width, double height) { 
			    this.mWidth = width; 
			    this.mHeight = height; 
			  } 
		  public Size(String fromString) { 
			    String[] items = fromString.split(", ");
			  
			  	this.mWidth = Double.parseDouble(items[0]); 
			    this.mHeight = Double.parseDouble(items[1]); 
			  } 
		} 
	
}
