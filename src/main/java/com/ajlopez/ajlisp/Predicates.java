package com.ajlopez.ajlisp;

public class Predicates {

	public static boolean isAtom(Object obj) {
		if (obj instanceof List)
			return false;
		return true;
	}

	public static boolean isList(Object obj) {
		if (obj instanceof List)
			return true;
		return false;
	}
	
	public static boolean isNil(Object obj) {
		return obj == null;
	}
	
	public static boolean isFalse(Object obj) {
		if (obj == null)
			return true;
		
		if (obj.equals(false))
			return true;
		
		return false;
	}
	
	public static boolean isTrue(Object obj) {
		return !isFalse(obj);
	}
}
