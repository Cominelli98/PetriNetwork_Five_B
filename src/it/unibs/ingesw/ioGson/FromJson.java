package it.unibs.ingesw.ioGson;

import java.util.ArrayList;

import com.google.gson.Gson;

public final class FromJson {
	
	public static Object convert(String s, Class c ) {
		Gson gson = new Gson();
		return gson.fromJson(s,c);
	}
}
