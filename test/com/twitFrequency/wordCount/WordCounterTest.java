package com.twitFrequency.wordCount;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class WordCounterTest{
	
	@Test
	public void testWordCountEmpty()
	{
		assertNull(new WordCounter(null).getWordFrequency());
		assertNull(new WordCounter(new ArrayList<String>()).getWordFrequency());
	}
	
	@Test
	public void testBasicWordCount()
	{
		String[] words = new String[]{"a","b"};
		List<String> wordList = makeWordList(words);
		Map<String, Integer> wordCount = new WordCounter(wordList).getWordFrequency();
		assertTrue(wordCount.get("a").equals(1));
		
		wordList = makeWordList(new String[]{"a","a","b"});
		wordCount = new WordCounter(wordList).getWordFrequency();
		assertTrue(wordCount.get("a").equals(2));
		assertTrue(wordCount.get("b").equals(1));
	}

	private List<String> makeWordList(String[] words) {
		List<String> wordList = new ArrayList<String>();
		for(String word : words)
		{
			wordList.add(word);
		}
		return wordList;
	}

}
