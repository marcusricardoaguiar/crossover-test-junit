package com.crossover.techtrial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crossover.techtrial.model.Article;

@RepositoryRestResource(exported = false)
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

	@Query(value = "select * from article a where a.title like %?1% or a.content like %?1% limit 10", nativeQuery = true)
	List<Article> findTop10ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title);

}
