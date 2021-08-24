package com.lusca44.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String arg) {
		try {
			return URLDecoder.decode(arg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
