package Util;
import java.lang.reflect.Field;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONUtils {
	public JSONObject jsonifyList(List list) {
		JSONObject listJSON = new JSONObject();
		JSONArray jsonList = new JSONArray();
		for (Object item : list) {
			JSONObject jsonObject = jsonifyObject(item);
			jsonList.add(jsonObject);
		}
		listJSON.put("List", jsonList);
		return listJSON;
	}

	public JSONObject jsonifyObject(Object item) {
		JSONObject jsonObject = new JSONObject();
		if (item instanceof List) {
			jsonObject = jsonifyList((List) item);
		} else {
			Class<?> clz = item.getClass();
			Field[] fields = clz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					Object value = field.get(item);
					if (value != null) {
						jsonObject.put(field.getName(), value.toString());
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return jsonObject;
	}
}
