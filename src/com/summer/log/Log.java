package com.summer.log;

public class Log {
	public static void error(String error) {
		System.out.println("ERROR: " + error);
	}
	
	public static void info(String info) {
		System.out.println("Info: " + info);
	}
	
	public static void debug(String debug) {
		System.out.println("Debug: " + debug);
	}
}
