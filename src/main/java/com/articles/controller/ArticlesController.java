package com.articles.controller;
/**
 * @author sgudapati
 *
 */
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.articles.dao.ArticleDAO;
import com.articles.entity.Article;
import com.articles.enums.VoteType;
import com.articles.response.GenericServiceResponse;
import com.articles.response.GetPopulorArticlesResponse;
import com.articles.response.ServiceResponse;
import com.articles.response.VoteArticleResponse;
import com.articles.service.Service;
import com.articles.serviceimpl.ServiceImpl;

@RestController
public class ArticlesController {

	@Autowired
	ServiceImpl serviceImpl;
	
	@Autowired
	Service service;
@Autowired
ArticleDAO articleDAO;

	
@PostMapping(value = "/articles")
public Article createArticle(@Valid @RequestBody Article article ) {
	
	return serviceImpl.save(article);

}

@GetMapping(value ="/articles")
public List<Article> getAllArticles(){
	return serviceImpl.findAll();
}

@GetMapping(value = "/articles/{id}")
public ResponseEntity<Optional<Article>> getArticleById(@PathVariable(value="id") Long id){

	Optional<Article> article=serviceImpl.findById(id);
	
	if(article==null) {
		return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(article);		
	}

@PostMapping(value ="/vote")
public ServiceResponse<VoteArticleResponse> votearticle(
			@RequestParam(name = "id") Long id,
			@RequestParam(name = "voteType", required = false) VoteType voteType) throws ServiceException {
	ServiceResponse<VoteArticleResponse> genericServiceResponse = new ServiceResponse<VoteArticleResponse>();
VoteArticleResponse voteArticlecResponse = service.voteArticle(id, voteType);
genericServiceResponse.setPayload(voteArticlecResponse);
genericServiceResponse.setStatus("SUCCESS");
return genericServiceResponse;
}

@RequestMapping(value = "/popularArticless", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public GenericServiceResponse<GetPopulorArticlesResponse> getPopulorArticles(
		@RequestParam(name = "voteType", required = false) VoteType voteType) throws ServiceException {
	GenericServiceResponse<GetPopulorArticlesResponse> genericServiceResponse = new GenericServiceResponse<GetPopulorArticlesResponse>();
	GetPopulorArticlesResponse getPopulorArticlesResponse = serviceImpl.getPopulorArticles(voteType);
	genericServiceResponse.setPayload(getPopulorArticlesResponse);
	genericServiceResponse.setStatus("SUCCESS");
	return genericServiceResponse;
}

}

	


