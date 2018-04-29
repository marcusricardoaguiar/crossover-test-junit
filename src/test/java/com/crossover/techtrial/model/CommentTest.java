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
public class CommentTest {

  @Before
  public void setup() throws Exception {

  }
  
  @Test
  public void testCommentObjectHashCodeMethod1Result() throws Exception {
	  Comment comment1 = new Comment();
	  int hash = comment1.hashCode();
	  Assert.assertNotNull(hash);
  }

  @Test
  public void testCommentObjectEqualsMethodFalse() throws Exception {
	  Comment comment1 = new Comment();
	  comment1.setId((long)1);
	  Comment comment2 = new Comment();
	  comment2.setId((long)2);
	  Assert.assertEquals(comment1.equals(comment2), false);
  }
  
  @Test
  public void testCommentObjectEqualsMethodTrue() throws Exception {
	  Comment comment1 = new Comment();
	  comment1.setId((long)1);
	  Comment comment2 = new Comment();
	  comment2.setId((long)1);
	  Assert.assertEquals(comment1.equals(comment2), true);
  }
  
}
