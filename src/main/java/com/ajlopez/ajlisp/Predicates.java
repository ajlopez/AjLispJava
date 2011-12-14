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
}
