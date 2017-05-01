package com.wdtourism.test;

import com.wdtourism.service.SingletonService;

public class Test {
   public static void main(String[] agrs){
	   SingletonService ss = SingletonService.getInstance();
	   int count=0;
	   while(true){
		 if(count==0||count==9){
	       ss.hello();
	       
		 }
		 count++;
	   }
   }
}
