package com.articles.response;

import java.io.Serializable;
import java.util.Map;

import com.articles.enums.VoteType;

public class VoteArticleResponse implements Serializable  {
	
	 	private static final long serialVersionUID = -7811147658383368425L;
	 	Map<VoteType, Integer> voteCount = null;
	 
	 	public VoteArticleResponse() {
	 	}
	 	
	 	public Map<VoteType, Integer> getVoteCount() {
	 		return voteCount;
	 	}
	 	
	 	public void setVoteCount(Map<VoteType, Integer> voteCount) {
	 		this.voteCount = voteCount;
	 	}
	 }