package com.twitFrequency.api;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.google.inject.Inject;
import com.twitFrequency.data.TwitterUser;

public class TwitterUserServiceImpl implements TwitterUserService {

	private final Twitter twitter;
	
	@Inject
	public TwitterUserServiceImpl(Twitter twitter)
	{
		this.twitter = twitter;
		
	}
	
	@Override
	public List<Status> getHomeTimeLine(TwitterUser twitterUser) throws TwitterException {
		twitter.setOAuthAccessToken(twitterUser.getAccessToken());
		return twitter.getHomeTimeline();
	}

}
