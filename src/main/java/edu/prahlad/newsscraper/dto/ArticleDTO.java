package edu.prahlad.newsscraper.dto;

public class ArticleDTO {
	private String title;
	private String description;
	private String url;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "ArticleDTO [title=" + title + ", description=" + description + ", url=" + url + "]";
	}
	
	
	
	
}
