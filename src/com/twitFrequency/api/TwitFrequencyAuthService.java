package com.twitFrequency.api;

import twitter4j.auth.AccessToken;

public interface TwitFrequencyAuthService {

	public String getRequestURL() throws Exception;
	
	public AccessToken getAccessToken(String pin) throws Exception;
	
}
