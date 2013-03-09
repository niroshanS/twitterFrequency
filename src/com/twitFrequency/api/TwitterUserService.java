package com.twitFrequency.api;

import java.util.List;

import com.twitFrequency.data.TwitterUser;

import twitter4j.Status;
import twitter4j.TwitterException;

public interface TwitterUserService {
	
	public List<Status> getHomeTimeLine(TwitterUser twitterUser) throws TwitterException;

}
