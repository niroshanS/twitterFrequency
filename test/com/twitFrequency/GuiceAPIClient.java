package com.twitFrequency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.twitFrequency.api.TwitFrequencyAuthService;
import com.twitFrequency.api.TwitFrequencyModule;
import com.twitFrequency.api.TwitterUserService;
import com.twitFrequency.data.TwitterDataFactory;
import com.twitFrequency.data.TwitterUser;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class GuiceAPIClient {
	
	public static void main(String[] args) throws Exception
	{
		Injector injector = Guice.createInjector(new TwitFrequencyModule());
		TwitFrequencyAuthService authService = injector.getInstance(TwitFrequencyAuthService.class);
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(authService.getRequestURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = authService.getAccessToken(pin);
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    
	    TwitterUser twitterUser = injector.getInstance(TwitterDataFactory.class).createTwitterUser(accessToken);
	    TwitterUserService twitterUserService = injector.getInstance(TwitterUserService.class);
	    
	    
		
		for(Status status : twitterUserService.getHomeTimeLine(twitterUser))
		{
			System.out.println(status.getUser().getName() + ": " + status.getText());
		}
	}

}
