package com.twitFrequency.wordCount;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class WordCounterTest{
	
	@Test
	public void testWordCountEmpty()
	{
		assertNull(new WordCounter("").getWordFrequency());
	}
	
	
	@Test
	public void testBasicWordCountString()
	{
		String blockOfText = "The the the one one. two?";
		Map<String, Integer> wordCount = new WordCounter(blockOfText).getWordFrequency();
		assertTrue(wordCount.get("the").equals(3));
		assertTrue(wordCount.get("one").equals(2));
		assertTrue(wordCount.get("two").equals(1));
		
		
	}
	
	
	@Test
	public void testTopWords()
	{
		String text = "one four four four four two two three three three";
		List<String> topWords = new WordCounter(text).getTopWords(10);
		assertEquals("four",topWords.get(0));
		assertEquals("three",topWords.get(1));
		assertEquals("two",topWords.get(2));
		assertEquals("one",topWords.get(3));
	}
	
	@Test
	public void testTopWordsLimit()
	{
		String text = "one four four four four two two three three three";
		List<String> topWords = new WordCounter(text).getTopWords(2);
		assertEquals(2, topWords.size());
		assertEquals("four",topWords.get(0));
		assertEquals("three",topWords.get(1));
	}


}
