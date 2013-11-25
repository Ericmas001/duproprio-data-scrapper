package com.ericmas001.duproprio.entities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HouseProperty {

//	{
//		"HasMultiples" : false,
//		"Name" : "Détails chambres",
//		"Value" : "Chambre des maîtres avec walk-in"
//	}, {
//		"HasMultiples" : true,
//		"Name" : "Parement extérieur",
//		"Value" : ["Clin de vinyle"]
//	}, {
//		"HasMultiples" : true,
//		"Name" : "Équipements / Services inclus",
//		"Value" : ["Aspirateur central", "Cabanon", "Chambre froide", "Échangeur d'air", "Luminaires", "Rideaux", "Salle d'eau au r/c", "Stores", "Système d'alarme", "Walk-in"]
//	}
	
	private String m_Name;
	private String m_Value;
	private ArrayList<String> m_Values;
	private boolean m_MultipleValues;
	
	public String getName() {
		return m_Name;
	}
	public void setName(String name) {
		this.m_Name = name;
	}
	public String getValue() {
		return m_Value;
	}
	public void setValue(String value) {
		this.m_Value = value;
	}
	public ArrayList<String> getValues() {
		return m_Values;
	}
	public void setValues(ArrayList<String> values) {
		this.m_Values = values;
	}
	public boolean hasMultipleValues() {
		return m_MultipleValues;
	}
	public void hasMultipleValues(boolean multipleValues) {
		this.m_MultipleValues = multipleValues;
	}
	
	public HouseProperty(String name, ArrayList<String> values) {
		super();
		this.m_Name = name;
		this.m_Value = null;
		this.m_Values = values;
		this.m_MultipleValues = true;
	}
	
	public HouseProperty(String name, String value) {
		super();
		this.m_Name = name;
		this.m_Name = name;
		this.m_Value = value;
		this.m_Values = null;
		this.m_MultipleValues = false;
	}

	public HouseProperty() {
		super();
	}
	
	public HouseProperty(JSONObject item) throws JSONException {
		super();

		this.m_Name = item.getString("Name");
		this.m_MultipleValues = item.getBoolean("HasMultiples");
		if(m_MultipleValues)
		{
			JSONArray jarr = item.getJSONArray("Value");
			m_Values = new ArrayList<String>();
			for(int i = 0; i < jarr.length(); ++i)
				m_Values.add(jarr.getString(i));
			m_Value = null;
		}
		else
		{
			m_Values = null;
			m_Value = item.getString("Value");
		}
	}
	
	@Override
	public String toString()
	{
		return m_Name;
	} 
	
}
