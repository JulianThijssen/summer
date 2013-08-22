package com.summer.util;

public class CyclicByteBuffer {
	private byte[] buffer = null;
	private int start = 0, end = 0;
	
	public CyclicByteBuffer(int capacity) {
		buffer = new byte[capacity];
	}
	
	public static CyclicByteBuffer allocate(int capacity) {
		if(capacity < 1) {
			throw new IllegalArgumentException("Please provide a position number as capacity");
		}
		CyclicByteBuffer buffer = new CyclicByteBuffer(capacity);
		return buffer;
	}
	
	public boolean isEmpty() {
		if(end > start) {
			return false;
		}
		return true;
	}
	
	public byte get() {
		byte b = buffer[start];
		start++;
		if(start > buffer.length) {
			start = 0;
		}
		return b;
	}
	
	public void put(byte b) {
		buffer[end] = b;
		end++;
		if(end > buffer.length) {
			end = 0;
		}
	}
}
