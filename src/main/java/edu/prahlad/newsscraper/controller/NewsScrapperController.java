package edu.prahlad.newsscraper.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.prahlad.newsscraper.dao.NewsScrapperDao;
import edu.prahlad.newsscraper.dto.ArticleDTO;
import edu.prahlad.newsscraper.dto.AuthorDTO;

@RestController
@RequestMapping("/api")
public class NewsScrapperController {

public static final Logger LOGGER = LoggerFactory.getLogger(NewsScrapperController.class);
	

	@GetMapping("/metaData")
	public ResponseEntity<HttpStatus> loadMetaData(){
		
		return new ResponseEntity<HttpStatus>(NewsScrapperDao.getMetaData(), HttpStatus.OK);
	}

	@GetMapping("/authors")
	public ResponseEntity<List<AuthorDTO>> listAllUsers(){
		
		return new ResponseEntity<List<AuthorDTO>>(NewsScrapperDao.getAuthors(), HttpStatus.OK);
	}
	
	@GetMapping("/articles")
	public ResponseEntity<List<ArticleDTO>> listAllArticles(){
		
		return new ResponseEntity<List<ArticleDTO>>(NewsScrapperDao.getArticles(), HttpStatus.OK);
	}
	
	@GetMapping("/authors/{userName}/articles")
	public ResponseEntity<List<ArticleDTO>> authorBasedArticle(@PathVariable("userName") final String userName){
		
		return new ResponseEntity<List<ArticleDTO>>(NewsScrapperDao.getArticleBasedOnAuthor(userName), HttpStatus.OK);
	}
	
	
	@GetMapping("/articles/{article}")
	public ResponseEntity<List<ArticleDTO>> articleBasedSearch(@PathVariable("article") final String article){
		
		return new ResponseEntity<List<ArticleDTO>>(NewsScrapperDao.articlesBasedSearch(article), HttpStatus.OK);
	}
	
}
