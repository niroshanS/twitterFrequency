package com.twitFrequency.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class Util {
	
	private static final Pattern spaceRegex = Pattern.compile("\\s|" + System.getProperty("line.separator"));
	private static final Pattern newLineRegex = Pattern.compile(System.getProperty("line.separator"));
	private static final Map<String,String> stopWords = getStopWords();
	
	public static List<String> getWordList(String target)
	{
		List<String> wordList = new ArrayList<String>();
		if(target != null)
		{
			for (String word : spaceRegex.split(target)) 
			{
				if(StringUtils.isNotEmpty(word))
				{
					wordList.add(word);
				}
			}
		}
		
		return wordList;
	}
	
	private static Map<String, String> getStopWords() {
		Map<String,String> stopWords = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("stopwords.txt"));
			String word = null;
			while((word = reader.readLine()) != null)
			{
				stopWords.put(word.toLowerCase(), word);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stopWords;
	}

	public static String removeStopWords(String target)
	{
		StringBuilder builder = new StringBuilder();
		if(target != null)
		{
			for(String line : newLineRegex.split(target))
			{
				StringBuilder lineBuilder = new StringBuilder();
				for(String word : spaceRegex.split(line))
				{
					if(stopWords.get(word.toLowerCase()) == null)
					{
						if(lineBuilder.length() > 0) lineBuilder.append(" ");
						lineBuilder.append(word);
						
					}
				}
				if(builder.length() > 0) builder.append(System.getProperty("line.separator"));
				builder.append(lineBuilder.toString().trim());
				
			}
		}
		return builder.toString();
	}

}
