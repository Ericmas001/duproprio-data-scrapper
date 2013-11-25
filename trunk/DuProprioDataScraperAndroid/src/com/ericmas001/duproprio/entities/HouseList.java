package com.ericmas001.duproprio.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.widget.Toast;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
@SuppressLint("UseSparseArrays")
public class HouseList {

	public static List<HouseSummary> ITEMS = new ArrayList<HouseSummary>();
	public static Map<Integer, HouseSummary> ITEM_MAP = new HashMap<Integer, HouseSummary>();
	public static Toast GLOBAL_TOAST;
	

	public static void addItem(HouseSummary house) {
		ITEMS.add(house);
		ITEM_MAP.put(house.getNoAnnonce(), house);
	}
}
