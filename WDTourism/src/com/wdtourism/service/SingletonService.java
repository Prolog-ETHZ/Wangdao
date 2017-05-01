package com.wdtourism.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SingletonService {
   private static InetAddress host;
   private static final int PORT = 1234;
   private Socket link;
   private ObjectInputStream input;
   private PrintWriter output;
   private SingletonService(){
	 try{
   		host = InetAddress.getLocalHost();
   		
		link = new Socket(host,PORT);
		input = null;
	    input = new ObjectInputStream(link.getInputStream());
	    output = new PrintWriter(link.getOutputStream(),true);
   	 }catch(UnknownHostException uhEx){
   		System.out.println("Host ID not found");
   		System.exit(1);
   	 } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
   }
   private static class Holder{
	   public static final SingletonService ONE = new SingletonService();
   }
   public static SingletonService getInstance(){
	   return Holder.ONE;
   }
   public void hello(){
	   String response="";
	   output.println("台北一日游，我想去看夜景");
	   try {
			try {
				response = (String) input.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String iso = null;
		try {
			iso = new String(response.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str;
		try {
			str = new String(iso.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);
   }
}
