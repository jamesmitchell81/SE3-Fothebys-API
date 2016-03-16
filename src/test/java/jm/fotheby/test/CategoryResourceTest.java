package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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

  @After
  public void cleanUp()
  {
    this.client.close();
  }
}