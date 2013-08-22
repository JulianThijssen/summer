package com.summer.network;

public class OldPacket {
	//Server List Packets
	public static final int JOIN_REQUEST = 0x04;
	public static final int JOIN_REQUEST_ACCEPTED = 0x05;
	public static final int JOIN_REQUEST_DENIED = 0x06;
	
	//Lobby Packets
	public static final int ROOM_STARTED = 0x10;
	public static final int ROOM_CLOSED = 0x11;
	
	//General Packets
	public static final int USER_JOINED = 0x20;
	public static final int USER_LEFT = 0x21;
	
	public static byte[] createJoinRequest() {
		byte[] b = {JOIN_REQUEST};
		return b;
	}
	
	public static byte[] createJoinRequestAccepted() {
		byte[] b = {JOIN_REQUEST_ACCEPTED};
		return b;		
	}
	
	public static byte[] createJoinRequestDenied() {
		byte[] b = {JOIN_REQUEST_DENIED};
		return b;
	}
	
	public static byte[] createRoomStarted() {
		byte[] b = {ROOM_STARTED};
		return b;
	}
	
	public static byte[] createRoomClosed() {
		byte[] b = {ROOM_CLOSED};
		return b;
	}
	
	public static byte[] createUserJoined(String name) {
		byte[] b = new byte[9];
		b[0] = USER_JOINED;
		b[1] = (byte) name.charAt(0);
		b[2] = (byte) name.charAt(1);
		b[3] = (byte) name.charAt(2);
		b[4] = (byte) name.charAt(3);
		b[5] = (byte) name.charAt(4);
		b[6] = (byte) name.charAt(5);
		b[7] = (byte) name.charAt(6);
		b[8] = (byte) name.charAt(7);
		return b;
	}
	
	public static byte[] createUserLeft(String name) {
		byte[] b = new byte[9];
		b[0] = USER_LEFT;
		b[1] = (byte) name.charAt(0);
		b[2] = (byte) name.charAt(1);
		b[3] = (byte) name.charAt(2);
		b[4] = (byte) name.charAt(3);
		b[5] = (byte) name.charAt(4);
		b[6] = (byte) name.charAt(5);
		b[7] = (byte) name.charAt(6);
		b[8] = (byte) name.charAt(7);
		return b;
	}
}
