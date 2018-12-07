package com.articles.repository;

/**
 * @author sgudapati
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.articles.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}