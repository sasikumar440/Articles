package com.articles.serviceimpl;
/**
 * @author sgudapati
 *
 */
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.articles.dao.ArticleDAO;
import com.articles.entity.Article;
import com.articles.enums.VoteType;
import com.articles.repository.ArticleRepository;
import com.articles.response.GetPopulorArticlesResponse;
import com.articles.response.VoteArticleResponse;
import com.articles.service.Service;

@Component
public class ServiceImpl implements Service {
	@Autowired
	ArticleDAO articlesDao;
	
	@Autowired
	ArticleRepository repository;
	
public Article save(Article article) {
	return repository.save(article);
}

public List<Article> findAll(){
	return repository.findAll();
}

public Optional<Article> findById(Long id) {
	return repository.findById(id);
}
	
	@Override
	public VoteArticleResponse voteArticle(Long id, VoteType voteType) throws ServiceException {
		if (StringUtils.isEmpty(id)) {
			throw new ServiceException("Article_ID_REQUIRED");
		}
		if (voteType == null) {
			throw new ServiceException("VOTE_TYPE_IS_REQUIRED");
		}
		Map<VoteType, Integer> voteCount = articlesDao.voteArticle(id, voteType);
		VoteArticleResponse voteTopicResponse = new VoteArticleResponse();
		voteTopicResponse.setVoteCount(voteCount);

		return voteTopicResponse;
	}
	@Override
	public GetPopulorArticlesResponse getPopulorArticles(VoteType voteType) throws ServiceException {
		if (voteType == null) {
			voteType = VoteType.UPVOTE;
		}
		List<Article> populorArticles = articlesDao.getPopulorArticles(voteType);
		GetPopulorArticlesResponse getPopulorArticlesResponse = new GetPopulorArticlesResponse();
		getPopulorArticlesResponse.setPopulorArticles(populorArticles);
		return getPopulorArticlesResponse;
	}

}
