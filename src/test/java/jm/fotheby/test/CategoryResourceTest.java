package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.json.*;

public class CategoryResourceTest
{
  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @Ignore
  @Test
  public void testCreateCategory()
  {
    JSONObject root = new JSONObject();
    root.put("name", "CategoryName");

    JSONArray attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    Response response = this.client.target("http://localhost:8080/services/category")
                            .request()
                            .post(Entity.json(root.toString()));

    assertTrue(HttpStatus.CREATED == response.getStatus());
    response.close();
  }

  @Ignore
  @Test
  public void testGetAllCategories()
  {
    String categories = client.target("http://localhost:8080/services/category")
                              .request()
                              .get(String.class);

    System.out.println(categories);
  }

  @Ignore
  @Test
  public void testUpdateCategory()
  {
    JSONObject root = new JSONObject();
    root.put("name", "Drawings");

    JSONArray attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Framed");

    root.put("attributes", attributes);

    Response response = this.client.target("http://localhost:8080/services/category/1")
                                   .request()
                                   .put(Entity.json(root.toString()));

    assertTrue(HttpStatus.NO_CONTENT == response.getStatus());
    response.close();

    this.testGetAllCategories();
  }

  @Ignore
  @Test
  public void testDuplicateEntry()
  {
    String location;

    JSONObject root = new JSONObject();
    root.put("name", "Drawings");

    JSONArray attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    Response response = this.client.target("http://localhost:8080/services/category")
                            .request()
                            .post(Entity.json(root.toString()));


    assertTrue(HttpStatus.UNPROCESSABLE_ENTITY == response.getStatus());
    response.close();
  }

  @After
  public void cleanUp()
  {
    this.client.close();
  }
}