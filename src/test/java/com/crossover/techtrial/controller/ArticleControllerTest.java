package com.crossover.techtrial.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.techtrial.model.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setup() throws Exception {

  }

  @Test
  public void testArticleShouldBeCreated() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    Assert.assertNotNull(resultAsset.getBody().getId());
  }
  
  @Test
  public void testArticleShouldBeDeleted() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    template.delete("/articles/" + resultAsset.getBody().getId());
    Assert.assertNotNull(resultAsset.getBody().getId());
  }
  
  @Test
  public void testArticleShouldBeUpdated() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    Article articleCreated = resultAsset.getBody();
    articleCreated.setTitle("Title Changed");
    template.put("/articles/" + articleCreated.getId(), articleCreated);
    Assert.assertNotNull(resultAsset.getBody().getId());
  }
  
  @Test
  public void testArticleSearch() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"user1@gmail.com\", \"title\": \"xpto\" }");
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    long start = System.nanoTime();
    ResponseEntity<List<Article>> response = template.exchange("/articles/search?text=" + resultAsset.getBody().getTitle(),
    	    HttpMethod.GET, null, new ParameterizedTypeReference<List<Article>>(){});
    long elapsedTime = System.nanoTime() - start;
    System.out.println("Search Total Time: " + elapsedTime);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
  }
  
  @Test
  public void testArticleGetById() throws Exception {
    HttpEntity<Object> article = getHttpEntity(
        "{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
    ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article,
        Article.class);
    Article result = template.getForEntity("/articles/" + resultAsset.getBody().getId(), Article.class).getBody();
    Assert.assertEquals(resultAsset.getBody().getId(), result.getId());
  }
  
  @Test
  public void testArticleGetByIdShouldReturnNull() throws Exception {
	ResponseEntity<Article> resultAsset = template.getForEntity("/articles/0", Article.class);
    Assert.assertEquals(resultAsset.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }
}
