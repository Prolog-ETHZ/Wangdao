package com.wdtourism.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpRequest {
	public static void setParams(URIBuilder builder, String[][] params) {
		int num = params.length;
		for(int i = 0; i < num; i++) {
			builder.setParameter(params[i][0], params[i][1]);
		}
	}
	
	public static void setHeaders(HttpUriRequest hur, String[][] headers) {
		int num = headers.length;
		for(int i = 0; i < num; i++) {
			hur.setHeader(headers[i][0], headers[i][1]);
		}
	}
	
	public static void setEntity(HttpPost httpPost, String[][] entity) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		int num = entity.length;
		for(int i = 0; i < num; i++) {
			formparams.add(new BasicNameValuePair(entity[i][0], entity[i][1]));
		}
		UrlEncodedFormEntity newEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		httpPost.setEntity(newEntity);
	}
	
	public static String inputStreamToString(InputStream is, String charset) throws IOException {  
        int i = -1;  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        while ((i = is.read()) != -1) {  
            baos.write(i);  
        }  
        if (null == charset) {  
            return baos.toString();  
        } else {  
            return baos.toString(charset);  
        }  
    } 
}
