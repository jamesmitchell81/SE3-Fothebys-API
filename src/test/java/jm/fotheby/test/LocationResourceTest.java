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

public class LocationResourceTest
{

  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @Ignore
  @Test
  public void createLocationTest()
  {
    JSONObject root = new JSONObject();
    root.put("name", "London");
    root.put("capacity", 500);
    root.put("telNumber", "020 7946 0929");

    JSONObject country = new JSONObject();
    country.put("shortCode", "GBR");

    root.put("country", country);

    JSONObject address = new JSONObject();
    address.put("firstLine", "Fotheby's London");
    address.put("secondLine", "24 Carnaby Street");
    address.put("townCity", "London");
    address.put("postalCode", "W1F 7DB");

    root.put("address", address);

    Response response = this.client.target("http://localhost:8080/services/location")
                            .request()
                            .post(Entity.json(root.toString()));

    System.out.println(response.getStatus());

  }

  @Ignore
  @Test
  public void updateLocationTest()
  {
    int updateId = 1;

    JSONObject root = new JSONObject();
    root.put("name", "London");
    root.put("capacity", 500);
    root.put("telNumber", "020 7946 0929");

    JSONObject country = new JSONObject();
    country.put("shortCode", "GBR");

    root.put("country", country);

    JSONObject address = new JSONObject();
    address.put("firstLine", "Fotheby's London");
    address.put("secondLine", "28 Carnaby Street");
    address.put("townCity", "London");
    address.put("postalCode", "W1F 7DB");

    root.put("address", address);

    Response response = this.client.target("http://localhost:8080/services/location/1")
                            .request()
                            .put(Entity.json(root.toString()));

    assertTrue(HttpStatus.NO_CONTENT == response.getStatus());
    response.close();
  }

  @Ignore
  @Test
  public void getLocationByIdTest()
  {
    String response = this.client.target("http://localhost:8080/services/location/1")
                                 .request()
                                 .get(String.class);

    System.out.println(response);
  }

  @After
  public void cleanUp()
  {

  }
}