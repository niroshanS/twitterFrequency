package com.twitFrequency;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * A simple way to test out the twitter api
 * 
 *
 */
public class SimpleAPIClient {

	public static void main(String[] args) throws TwitterException, IOException
	{
		Twitter twitter = TwitterFactory.getSingleton();
		Properties prop = new Properties();
		prop.load(new FileInputStream("twitter.properties"));
	    twitter.setOAuthConsumer(prop.getProperty("consumer_key"), prop.getProperty("consumer_key_secret"));
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(pin);
	         }else{
	           accessToken = twitter.getOAuthAccessToken();
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    
	    
		
		for(Status status : twitter.getHomeTimeline())
		{
			System.out.println(status.getUser().getName() + ": " + status.getText());
		}
	}
	
}
