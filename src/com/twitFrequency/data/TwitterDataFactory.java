package com.twitFrequency.data;

import twitter4j.auth.AccessToken;

public interface TwitterDataFactory {
	
	public TwitterUser createTwitterUser(AccessToken accessToken);

}
