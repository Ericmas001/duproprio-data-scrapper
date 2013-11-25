package com.ericmas001.duproprio.entities;

import org.json.JSONException;
import org.json.JSONObject;

public class HouseSummary {

//	{
//		"Details" : null,
//		"NoAnnonce" : 345384,
//		"Latitude" : 46.77758093328076,
//		"Longitude" : -71.173945069313049,
//		"DetailsURL" : "http://duproprio.com/jumele-a-vendre-levis-quebec-345384",
//		"Type" : "Jumelé",
//		"Ville" : "Lévis",
//		"Region" : "Québec Rive-Sud",
//		"Adresse" : "4772, rue Jacques-Ferron",
//		"ImageURL" : "http://photos.duproprio.com/facade-jumele-a-vendre-levis-quebec-province-medium-3010486.jpg",
//		"Prix" : 259000,
//		"Precison" : null,
//		"Summary" : "3 chambre(s), 1 salle(s) de bain, Aire habitable 1 428 pi², Terrain 4 000 pi², Stationnement privé",
//		"GMapURL" : "https://maps.google.com/maps?q=46.7775809332808,-71.173945069313"
//	}
	
	private int m_NoAnnonce;
	private double m_Latitude;
	private double m_Longitude;
	private String m_DetailsURL;
	private String m_Type;
	private String m_Ville;
	private String m_Region;
	private String m_Adresse;
	private String m_ImageURL;
	private int m_Prix;
	private String m_Precison;
	private String m_Summary;
	private String m_GMapURL;
	private HouseDetail m_Details = null;

	public int getNoAnnonce() {
		return m_NoAnnonce;
	}
	public void setNoAnnonce(int noAnnonce) {
		this.m_NoAnnonce = noAnnonce;
	}
	public HouseDetail getDetails() {
		return m_Details;
	}
	public void setDetails(HouseDetail details) {
		this.m_Details = details;
	}
	public double getLatitude() {
		return m_Latitude;
	}
	public void setLatitude(double latitude) {
		this.m_Latitude = latitude;
	}
	public double getLongitude() {
		return m_Longitude;
	}
	public void setLongitude(double longitude) {
		this.m_Longitude = longitude;
	}
	public String getDetailsURL() {
		return m_DetailsURL;
	}
	public void setDetailsURL(String detailsURL) {
		this.m_DetailsURL = detailsURL;
	}
	public String getType() {
		return m_Type;
	}
	public void setType(String type) {
		this.m_Type = type;
	}
	public String getVille() {
		return m_Ville;
	}
	public void setVille(String ville) {
		this.m_Ville = ville;
	}
	public String getRegion() {
		return m_Region;
	}
	public void setRegion(String region) {
		this.m_Region = region;
	}
	public String getAdresse() {
		return m_Adresse;
	}
	public void setAdresse(String adresse) {
		this.m_Adresse = adresse;
	}
	public String getImageURL() {
		return m_ImageURL;
	}
	public void setImageURL(String imageURL) {
		this.m_ImageURL = imageURL;
	}
	public int getPrix() {
		return m_Prix;
	}
	public void setPrix(int prix) {
		this.m_Prix = prix;
	}
	public String getPrecison() {
		return m_Precison;
	}
	public void setPrecison(String precison) {
		this.m_Precison = precison;
	}
	public String getSummary() {
		return m_Summary;
	}
	public void setSummary(String summary) {
		this.m_Summary = summary;
	}
	public String getGMapURL() {
		return m_GMapURL;
	}
	public void setGMapURL(String gMapURL) {
		this.m_GMapURL = gMapURL;
	}
	
	public HouseSummary(int noAnnonce, double latitude, double longitude,
			String detailsURL, String type, String ville,
			String region, String adresse, String imageURL, int prix,
			String precison, String summary, String gMapURL, HouseDetail details) {
		super();
		this.m_NoAnnonce = noAnnonce;
		this.m_Latitude = latitude;
		this.m_Longitude = longitude;
		this.m_DetailsURL = detailsURL;
		this.m_Type = type;
		this.m_Ville = ville;
		this.m_Region = region;
		this.m_Adresse = adresse;
		this.m_ImageURL = imageURL;
		this.m_Prix = prix;
		this.m_Precison = precison;
		this.m_Summary = summary;
		this.m_GMapURL = gMapURL;
		this.m_Details = details;
	}

	public HouseSummary() {
		super();
	}
	
	public HouseSummary(JSONObject item) throws JSONException {
		super();

		this.m_NoAnnonce = item.getInt("NoAnnonce");
		this.m_Latitude = item.getDouble("Latitude");
		this.m_Longitude = item.getDouble("Longitude");
		this.m_DetailsURL = item.getString("DetailsURL");
		this.m_Type = item.getString("Type");
		this.m_Ville = item.getString("Ville");
		this.m_Region = item.getString("Region");
		this.m_Adresse = item.getString("Adresse");
		this.m_ImageURL = item.getString("ImageURL");
		this.m_Prix = item.getInt("Prix");
		this.m_Precison = item.getString("Precison");
		if( m_Precison == "null")
			m_Precison = "";
		this.m_Summary = item.getString("Summary");
		this.m_GMapURL = item.getString("GMapURL");
	}
	
	@Override
	public String toString()
	{
		return ""+getNoAnnonce();
	}
	
	
}
