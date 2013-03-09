package com.twitFrequency.api;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class TwitFrequencyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TwitFrequencyAuthService.class).to(TwitFrequencyAuthServiceImpl.class);
		
	}
	
	@Provides
	public Twitter getTwitter()
	{
		return new TwitterFactory().getInstance();
	}

}
