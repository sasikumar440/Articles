package com.articles.service;
/**
 * @author sgudapati
 *
 */
import org.hibernate.service.spi.ServiceException;

import com.articles.enums.VoteType;
import com.articles.response.GetPopulorArticlesResponse;
import com.articles.response.VoteArticleResponse;


public interface Service {
	
	VoteArticleResponse voteArticle(Long id, VoteType voteType) throws ServiceException;

	
	GetPopulorArticlesResponse getPopulorArticles(VoteType voteType) throws ServiceException;

}
