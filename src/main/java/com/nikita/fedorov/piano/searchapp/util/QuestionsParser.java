package com.nikita.fedorov.piano.searchapp.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.nikita.fedorov.piano.searchapp.bean.QuestionBean;

public class QuestionsParser {
		
	/**
	 * Parses the questions json.
	 * @param source - the questions json.
	 * @return list of the questions business objects. 
	 */
	public static ArrayList<QuestionBean> ParseQuestions(String source){
		Gson gson = new Gson();
		Map<String, Object> parsed = gson.fromJson(source, Map.class);
		ArrayList<Object> items = (ArrayList<Object>) parsed.get("items");
		ArrayList<QuestionBean> questions = new ArrayList<QuestionBean>();
		
		for(Object itemObj : items) {
			Map<String, Object> item = (Map<String, Object>) itemObj;
			String author = (String) ((Map<String, Object>)item.get("owner")).get("display_name");
			boolean isAnswered = (Boolean) item.get("is_answered");
			Date creationDate = new Date(((Double) item.get("creation_date")).longValue() * 1000);
			String title = (String) item.get("title");
			String link = (String) item.get("link");
			questions.add(new QuestionBean(title, author, creationDate, isAnswered, link));
		}
		
		return questions;
	}
}
