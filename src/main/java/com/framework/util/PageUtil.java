package com.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class PageUtil {

	public static String GenerateSessionId() {
		return UUID.randomUUID().toString();
	}

	public static JSONObject toX5JSONObject(JSONObject json) {
		if (!json.getString("code").equals("200")) {
			return json;
		}

		JSONObject newPageObject = new JSONObject();
		newPageObject.put("@type", "table");

		JSONArray orginRows = json.getJSONArray("page");
		JSONArray rows = new JSONArray();

		for (int i = 0; i < orginRows.size(); i++) {
			JSONObject jo = (JSONObject) orginRows.get(i);
			JSONObject toJo = new JSONObject();

			Set<String> keys = jo.keySet();
			for (String key : keys) {
				JSONObject filed = new JSONObject();
				filed.put("value", jo.getString(key));
				toJo.put(key, filed);
			}

			rows.add(toJo);
		}

		newPageObject.put("rows", rows);

		JSONObject userdata = new JSONObject();
		userdata.put("sys.count", json.getInteger("totalCount"));// orginRows.size());

		newPageObject.put("userdata", userdata);

		json.put("page", newPageObject);

		return json;
	}

	public static JSONObject toX5JSONObjectNew(JSONObject json, JSONObject params) {
		if (json.getString("isSuccess") == "0") {
			return json;
		}

		JSONObject jsonData = json.getJSONObject("data");

		// JSONObject result = new JSONObject();
		// result.put("isSuccess", "1");

		// JSONObject resultData = new JSONObject();
		JSONObject newRowObject = new JSONObject();
		for (String rowKey : jsonData.keySet()) {
			if (rowKey.endsWith("Rows")) {

				newRowObject.put("@type", "table");

				JSONArray orginRows = jsonData.getJSONArray(rowKey);
				JSONArray rows = new JSONArray();

				for (int i = 0; i < orginRows.size(); i++) {
					JSONObject jo = (JSONObject) orginRows.get(i);
					JSONObject toJo = new JSONObject();

					Set<String> keys = jo.keySet();
					for (String key : keys) {
						JSONObject filed = new JSONObject();
						filed.put("value", jo.getString(key));
						toJo.put(key, filed);
					}

					rows.add(toJo);
				}

				newRowObject.put("rows", rows);

				JSONObject userdata = new JSONObject();
				userdata.put("sys.count", orginRows.size());

				newRowObject.put("userdata", userdata);

				// resultData.put(rowKey, newRowObject);
			}
		}

		// result.put("data", resultData);
		//
		// return result;
		return newRowObject;
	}

	// jsonObject转map
	public static Map<String, String> json2Map(JSONObject json) {
		// Map -> JSON
		Map<String, String> map = new HashMap<String, String>();

		for (String rowKey : json.keySet()) {
			map.put(rowKey, json.getString(rowKey));
		}

		return map;
	}
}
