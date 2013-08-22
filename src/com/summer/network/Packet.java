package com.summer.network;

import java.util.ArrayList;
import java.util.List;

public class Packet {
	public String type;
	List<Object> data = new ArrayList<Object>();
	
	//Packet constructor
	public Packet(String packet){
		String[] elements = packet.split("\\|");
		type = elements[0];
		for(int i = 1; i < elements.length; i++){
			data.add(elements[i]);
		}
	}
	
	//Type & parameter constructor
	public Packet(String type, Object... params){
		this.type = type;
		
		for(Object o: params){
			data.add(o);
		}
	}
	
	//Add parameters to the packet
	public void add(Object... params){
		for(Object o: params){
			data.add(o);
		}
	}
	
	//Turns the packet into string form
	public String toString(){
		String packet = type;
		
		for(int i = 0; i < getLength(); i++){
			packet += "|" + getString(i);
		}
		
		return packet;
	}
	
	
	//Get length
	public int getLength(){
		return data.size();
	}
	
	//Get string
	public String getString(int index){
		String s = data.get(index).toString();
		return s;
	}
	
	//Get integer
	public int getInt(int index){
		int i = Integer.parseInt(getString(index));
		return i;
	}
	
	//Get boolean
	public boolean getBoolean(int index){
		return getString(index).equals("true") ? true : false;
	}
}
