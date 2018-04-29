package com.crossover.techtrial.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleTest {

  @Before
  public void setup() throws Exception {

  }
  
  @Test
  public void testArticleObjectHashCodeMethod1Result() throws Exception {
	  Article article1 = new Article();
	  int hash = article1.hashCode();
	  Assert.assertNotNull(hash);
  }

  @Test
  public void testArticleObjectEqualsMethodFalse() throws Exception {
	  Article article1 = new Article();
	  article1.setId((long)1);
	  Article article2 = new Article();
	  article2.setId((long)2);
	  Assert.assertEquals(article1.equals(article2), false);
  }
  
  @Test
  public void testArticleObjectEqualsMethodTrue() throws Exception {
	  Article article1 = new Article();
	  article1.setId((long)1);
	  Article article2 = new Article();
	  article2.setId((long)1);
	  Assert.assertEquals(article1.equals(article2), true);
  }
  
}
