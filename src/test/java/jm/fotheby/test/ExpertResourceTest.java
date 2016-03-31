package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import org.junit.After;
import org.junit.Ignore;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.json.*;

public class ExpertResourceTest
{
  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  // @Ignore
  @Test
  public void testAddExpert()
  {
    JSONObject expert = new JSONObject();
    expert.put("title", "Mr.");
    expert.put("firstName", "James");
    expert.put("surname", "Mitchell");
    expert.put("emailAddress", "james.mitchell81@live.co.uk");
    expert.put("telNumber", "07729 588 138");
    expert.put("role", "expert");

    JSONObject address = new JSONObject();
    address.put("firstLine", "The Gallery, 2/3 Market Square");
    address.put("secondLine", "Town Centre");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN1 2DL");

    expert.put("contactAddress", address);

    JSONObject location = new JSONObject();
    location.put("id", 1);

    JSONObject category = new JSONObject();
    category.put("id", 1);

    JSONArray classification = new JSONArray();
    JSONObject c1 = new JSONObject();
    c1.put("id", 1);
    JSONObject c2 = new JSONObject();
    c2.put("id", 2);
    classification.put(c1);
    classification.put(c2);

    expert.put("location", location);
    expert.put("category", category);
    expert.put("specialties", classification);

    Response response = this.client.target("http://localhost:8080/services/expert")
                                   .request()
                                   .post(Entity.json(expert.toString()));

    assertTrue(response.getStatus() == HttpStatus.CREATED);

  }

  @Ignore
  @Test
  public void getExpertByIdTest()
  {
    String response = this.client.target("http://localhost:8080/services/expert/1")
                                 .request()
                                 .get(String.class);

    JSONObject obj = new JSONObject(response);
    int id = obj.has("id") ? obj.getInt("id") : 0;

    // System.out.println(obj.toString());
    System.out.println(response);


    assertTrue(id == 1);
  }

  @Ignore
  @Test
  public void updateExpertTest()
  {
    JSONObject expert = new JSONObject();
    expert.put("title", "Mr.");
    expert.put("firstName", "James");
    expert.put("surname", "Mitchell");
    expert.put("emailAddress", "james.mitchell81@live.co.uk");
    expert.put("telNumber", "07729 255 138");
    expert.put("role", "expert");

    JSONObject address = new JSONObject();
    address.put("firstLine", "The Gallery, 2/3 Market Square");
    address.put("secondLine", "Town Centre");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN1 2DL");

    expert.put("contactAddress", address);

    JSONObject location = new JSONObject();
    location.put("id", 1);

    JSONObject category = new JSONObject();
    category.put("id", 1);

    JSONArray classification = new JSONArray();
    JSONObject c1 = new JSONObject();
    c1.put("id", 3);
    JSONObject c2 = new JSONObject();
    c2.put("id", 4);
    classification.put(c1);
    classification.put(c2);

    expert.put("location", location);
    expert.put("category", category);
    expert.put("specialties", classification);

    Response response = this.client.target("http://localhost:8080/services/expert/1")
                                   .request()
                                   .put(Entity.json(expert.toString()));

    System.out.println(response.getStatus());
  }

  @Ignore
  @Test
  public void getExpertsTest()
  {
    String response = this.client.target("http://localhost:8080/services/expert")
                                 .request()
                                 .get(String.class);

    // JSONArray arr = new JSONArray(response);

    // for ( int i = 0; i < arr.length(); i++ )
    // {
    //   System.out.println(arr.getJSONObject(i).toString());
    // }
  }

  @After
  public void cleanUp()
  {

  }
}