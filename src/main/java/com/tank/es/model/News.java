package com.tank.es.model;

public class News {

	private String title;
	private String tag;
	private String publishTime;

	public News() {
		super();
	}

	public News(String title, String tag, String publishTime) {
		super();
		this.title = title;
		this.tag = tag;
		this.publishTime = publishTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
}