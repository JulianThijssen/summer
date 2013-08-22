package com.summer.util;

import java.util.Random;

public class NameGenerator {
	private static final char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'r', 's', 't', 'v', 'w'};
	private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	
	public static String getName() {
		Random random = new Random();
		char one = consonants[random.nextInt(17)];
		char two = vowels[random.nextInt(5)];
		char three = consonants[random.nextInt(17)];
		char four = vowels[random.nextInt(5)];
		char five = consonants[random.nextInt(17)];
		char six = vowels[random.nextInt(5)];
		char seven = consonants[random.nextInt(17)];
		char eight = vowels[random.nextInt(5)];
		return "" + one + two + three + four + five + six + seven + eight;
	}
	
	public static int generateID(String name) {
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			System.out.println(Character.getNumericValue(c));
		}
		return 0;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			System.out.println(getName());
		}
	}
}
