package com.twitFrequency.wordCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

import com.twitFrequency.util.Util;

public class WordCounter {

	private final List<String> words;
	private Map<String, Integer> wordFrequency;

	public WordCounter(String blockOfText) {
		
		blockOfText = blockOfText.replaceAll("([a-zA-Z]+)[?:!.,;]*", "$1");
		blockOfText = Util.removeStopWords(blockOfText);
		words = StringUtils.isNotEmpty(blockOfText) ? Arrays.asList(blockOfText.split(" ")) : 
			new ArrayList<String>();
		
	}
	

	public Map<String, Integer> getWordFrequency() {
		initWordCount();

		return wordFrequency;

	}
	
	public List<String> getTopWords(int limit)
	{
		initWordCount();
		SortedSet<Map.Entry<String, Integer>> sortedEntries = sortMap();
		List<String> topWords = new ArrayList<String>();
		int index = 0;
		for(Map.Entry<String, Integer> entry : sortedEntries)
		{
			if(index >= limit) break;
			topWords.add(entry.getKey());
			index++;
		}
		
		return topWords;
	}


	private SortedSet<Entry<String, Integer>> sortMap() {
		
		SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<Map.Entry<String,Integer>>(new Comparator<Map.Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> e1,
					Entry<String, Integer> e2) {
				int result = e2.getValue().compareTo(e1.getValue());
				return result != 0 ? result : 1;
			}
		});
		sortedEntries.addAll(wordFrequency.entrySet());
		return sortedEntries;
	}


	private void initWordCount() {
		if(wordFrequency == null)
		{
			wordFrequency = countWords();
		}
	}

	private Map<String, Integer> countWords() {
		if (words != null && !words.isEmpty()) {
			wordFrequency = new HashMap<String, Integer>();
			Map<String,String> stopWords = Util.getStopWords();
			for (String word : words) {
				word = word.toLowerCase().trim();
				if(stopWords.get(word) != null || StringUtils.isEmpty(word)) continue;
				if (wordFrequency.get(word) == null) {
					wordFrequency.put(word, 1);
				} else {
					wordFrequency.put(word, wordFrequency.get(word) + 1);
				}

			}
		}

		return wordFrequency;
	}

}
