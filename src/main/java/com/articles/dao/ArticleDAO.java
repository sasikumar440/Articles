package com.articles.dao;
/**
 * @author sgudapati
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.hibernate.service.spi.ServiceException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.articles.entity.Article;
import com.articles.enums.VoteType;

@Component
@EnableAsync
public class ArticleDAO {
	private static final int NUMBER_OF_POPULOR_articleS_TO_BE_MAINTAINED = 20;

	Map<String, Article> allarticles = new HashMap<>();
	
	
	int articleIdCounter = 0;
	int minVotesToBePopulor = 1;

public Map<VoteType, Integer> voteArticle(Long id, VoteType voteType) {

		if (voteType == null) {
			throw new ServiceException("VOTE_TYPE_IS_REQUIRED");
		}

		if (StringUtils.isEmpty(id)) {
			throw new ServiceException("article_ID_REQUIRED");
		}
		Article article = null;


		synchronized (allarticles) {
			article = allarticles.get(id);
		}
		if (article == null) {
			throw new ServiceException("article_NOT_FOUND");
		}
		article.vote(voteType);
		updatePopulorarticlesWithNewVote(article, voteType);
		return article.getVoteCount();
	}


public List<Article> getPopulorArticles(VoteType voteType) {
	if (voteType == null) {
		voteType = VoteType.UPVOTE;
	}
	List<Article> populorTopicsList = new ArrayList<>();
	populorTopicsList.addAll(voteType.getPopulorArticles());
	Collections.sort(populorTopicsList, voteType.getComparator());

	return populorTopicsList;
}

@Async
public void updatePopulorarticlesWithNewVote(Article article, VoteType voteType) throws ServiceException {
	if (article == null) {
		throw new ServiceException("article_REQUIRED_TO_UPDATE_POPULOR_articleS");
	}

	if (voteType == null) {
		throw new ServiceException("VOTE_TYPE_REQUIRED_TO_UPDATE_POPULOR_articleS");
	}

	PriorityQueue<Article> populorarticles = voteType.getPopulorArticles();
	
	synchronized (populorarticles) {
		populorarticles.remove(article);
		Integer count = article.getVoteCount().get(voteType);
		if ((count != null)) {
			Article peekarticle = populorarticles.peek();
			if (peekarticle != null) {
				Integer peekVoteCount = peekarticle.getVoteCount().get(voteType);
				if (peekVoteCount == null || count > peekVoteCount
						|| populorarticles.size() < NUMBER_OF_POPULOR_articleS_TO_BE_MAINTAINED) {
					populorarticles.offer(article);
				}
			} else {
				populorarticles.offer(article);
			}
		}
		
		while (populorarticles.size() > NUMBER_OF_POPULOR_articleS_TO_BE_MAINTAINED) {
			populorarticles.remove();
		}

	}
}


}