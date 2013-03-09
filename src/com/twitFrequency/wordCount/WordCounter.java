package com.twitFrequency.wordCount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {

	private final List<String> words;
	private Map<String, Integer> wordFrequency;

	public WordCounter(List<String> words) {
		this.words = words;
	}

	public Map<String, Integer> getWordFrequency() {
		if (wordFrequency == null) {
			wordFrequency = countWords();
		}

		return wordFrequency;

	}

	private Map<String, Integer> countWords() {
		if (words != null && !words.isEmpty()) {
			wordFrequency = new HashMap<String, Integer>();
			for (String word : words) {
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
