package com.twitFrequency.api;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.twitFrequency.data.TwitterDataFactory;
import com.twitFrequency.data.TwitterUser;
import com.twitFrequency.data.TwitterUserImpl;

public class TwitFrequencyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TwitFrequencyAuthService.class).to(TwitFrequencyAuthServiceImpl.class);
		bind(TwitterUserService.class).to(TwitterUserServiceImpl.class);
		install(new FactoryModuleBuilder().implement(TwitterUser.class, TwitterUserImpl.class).build(TwitterDataFactory.class));
		
	}
	
	@Provides
	@Singleton
	public Twitter getTwitter()
	{
		return TwitterFactory.getSingleton();
	}

}
