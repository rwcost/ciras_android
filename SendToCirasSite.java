package com.example.sensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//class designed to send named pairs to a php page for storage on a MySQL database
	public class SendToCirasSite { 
		 
		private String myResponse;
		
		//constructor used to pass named pairs by the calling code
		public SendToCirasSite(List<NameValuePair> nameValuePairs){
			//define a client
		    HttpClient client = new DefaultHttpClient();
		    
		    //this creates a post object with URL to sent the data to.
		    HttpPost post = new HttpPost("http://www.cirasheartcare.com/mysqlconnector.php");
		    try {
	    	  //Here is the actual http send to the php page
		      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		      
		      //read a response for connection error reporting
		      HttpResponse response = client.execute(post);
		      
		      //response is in a stream
		      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		      String line = "";
		      while ((line = rd.readLine()) != null) {
		        System.out.println(line);
		      }

		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		 		  	    
		}
		  public String getResponse(){
		    	return "Hello";
		    }
		
	}


