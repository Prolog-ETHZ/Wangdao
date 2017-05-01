package com.wdtourism.test;



import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import java.util.*;
public class ClientTest {
    private static InetAddress host;
    private static final int PORT = 1234;
    public static void main(String[] agrs){
    	try{
    		host = InetAddress.getLocalHost();
    	}catch(UnknownHostException uhEx){
    		System.out.println("Host ID not found");
    		System.exit(1);
    	}
    	accessServer();
    }
	private static void accessServer() {
		// TODO Auto-generated method stub
		Socket link = null;
		try{
			link = new Socket(host,PORT);
			ObjectInputStream input = null;
			
			input = new ObjectInputStream(link.getInputStream());
			 
			PrintWriter output = new PrintWriter(link.getOutputStream(),true);
			Scanner userEntry = new Scanner(System.in,"UTF-8");
			String message,response = null;
			int count = 1;
			do{
				System.out.println("Enter message: ");
				//message = userEntry.nextLine();
			//	System.out.println("台北一日游，我想去看夜景");
				if(count==1){
				  output.println("台北一日游，我想去看夜景");
				  count++;
				
				try {
					response = (String) input.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String iso = new String(response.getBytes("UTF-8"),"ISO-8859-1");
				String str = new String(iso.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println(str);
				output.flush();
				}
				
			}while(true);
		}catch(IOException ioEx){
		   ioEx.printStackTrace();	
		}
		finally{
			try{
				System.out.println("\n*CLosing connection……");
				link.close();
			}catch(Exception ioEx){
				System.out.println("Unable to disconnect");
			}
		}
	}

}
