package com.twitFrequency.data;

import twitter4j.auth.AccessToken;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class TwitterUserImpl implements TwitterUser {

	private final AccessToken accessToken;
	
	@Inject
	public TwitterUserImpl(@Assisted AccessToken accessToken)
	{
		this.accessToken = accessToken;
		
	}
	
	@Override
	public AccessToken getAccessToken() {
		return accessToken;
	}

}
