package com.twitFrequency.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class UtilTest {
	
	@Test
	public void testNull()
	{
		assertNotNull(Util.getWordList(null));
	}
	
	@Test
	public void testSingleWord()
	{
		assertList(Util.getWordList("word"), 1);
		assertList(Util.getWordList("word_Jkfl@fjdskl.comweroweruo$$//fds"), 1);
		assertList(Util.getWordList("abcd_123"), 1);
	}
	
	@Test
	public void testMultiWord()
	{
		assertList(Util.getWordList("word word"), 2);
		assertList(Util.getWordList("abcd 123"), 2);
		assertList(Util.getWordList("abcd" + System.getProperty("line.separator") + "123"), 2);
	}
	
	@Test
	public void testRemoveStopWords()
	{
		assertEquals("123", Util.removeStopWords("123"));
		assertEquals("", Util.removeStopWords("The"));
		assertEquals("quick brown fox jumped lazy dog", 
				Util.removeStopWords("The quick brown fox jumped over the lazy dog"));
		assertEquals("quick brown fox." + System.getProperty("line.separator") + "Jumped lazy dog", 
				Util.removeStopWords("The quick brown fox." + System.getProperty("line.separator") + "Jumped over the lazy dog"));
	}

	private void assertList(List<String> wordList, int size) 
	{
		assertNotNull(wordList);
		assertEquals(size,wordList.size());
	}

}
