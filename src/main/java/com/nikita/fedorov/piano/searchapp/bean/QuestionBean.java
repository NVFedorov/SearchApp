package com.nikita.fedorov.piano.searchapp.bean;

import java.util.Date;

/**
 * @author Nikita Fedorov
 * The following class represents the business object of the question entity.
 */
public class QuestionBean {
	private String title;
	private String author;
	private Date creationDate;
	private boolean isAnswered;
	private String link;
	
	public QuestionBean(String title, String author, Date creationDate, boolean isAnswered, String link) {
		this.title = title;
		this.author = author;
		this.creationDate = creationDate;
		this.isAnswered = isAnswered;
		this.link = link;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String value) {
		this.author = value;
	}
	
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	public void setCreationDate(Date value) {
		this.creationDate = value;
	}
	
	public boolean getIsAnswered() {
		return this.isAnswered;
	}
	
	public void setIsAnswered(boolean value) {
		this.isAnswered = value;
	}
	
	public String getLink() {
		return this.link;
	}
	
	public void setLink(String value) {
		this.link = value;
	}
}
