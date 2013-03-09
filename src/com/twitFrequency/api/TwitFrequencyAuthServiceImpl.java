package com.twitFrequency.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.google.inject.Inject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitFrequencyAuthServiceImpl implements TwitFrequencyAuthService {

	private final Twitter twitter;
	
	@Inject
	public TwitFrequencyAuthServiceImpl(Twitter twitter) {
		this.twitter = twitter;
	}
	
	@Override
	public String getRequestURL() throws TwitterException, FileNotFoundException, IOException {
		return getOAuthRequestToken().getAuthorizationURL();
	}

	private RequestToken getOAuthRequestToken() throws TwitterException, FileNotFoundException, IOException {
		Twitter twitter = TwitterFactory.getSingleton();
		Properties prop = new Properties();
		prop.load(new FileInputStream("twitter.properties"));
	    twitter.setOAuthConsumer(prop.getProperty("consumer_key"), prop.getProperty("consumer_key_secret"));
		return twitter.getOAuthRequestToken();
	}

	@Override
	public AccessToken getAccessToken(String pin) throws TwitterException {
		return twitter.getOAuthAccessToken(pin);
	}

}
