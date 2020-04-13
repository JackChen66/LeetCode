package application.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import application.property.bean.QuestionPropertyBean;

public class JsonUtil {
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (json == null || json.length() == 0)
			return null;
		Gson gson = new Gson();
        // Convert JSON File to Java Object
		T t = gson.fromJson(json, clazz);
		
        return t;
	}
	
	public static String toJson(Object o) {
		if (o == null)
			return null;
		Gson gson = new Gson();
		return gson.toJson(o);
	}
}
