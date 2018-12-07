package com.articles.response;

import java.io.Serializable;
import java.util.List;

import com.articles.entity.Article;

public class GetPopulorArticlesResponse implements Serializable{
	
	private static final long serialVersionUID = 2582668594950742430L;
	List<Article> populorArticles = null;
	
	public GetPopulorArticlesResponse() {
	}

	public List<Article> getPopulorArticles() {
		return populorArticles;
	}

	public void setPopulorArticles(List<Article> populorArticles) {
		this.populorArticles = populorArticles;
	}

}
