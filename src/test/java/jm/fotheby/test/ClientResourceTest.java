package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.json.*;

public class ClientResourceTest
{
  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @Ignore
  @Test
  public void createUnregisteredClientTest()
  {
    // jm.fotheby.entities.Client testClient = new jm.fotheby.entities.Client("Mr", "James", "Mitchell");

    JSONObject client = new JSONObject();
    client.put("title", "Mr.");
    client.put("firstName", "James");
    client.put("surname", "Mitchell");
    client.put("emailAddress", "james.mitchell81@live.co.uk");
    client.put("telNumber", "07789 558 138");
    client.put("registered", false);

    JSONObject address = new JSONObject();
    address.put("firstLine", "Apt 4, The Gallery, 2/3 Market Square");
    address.put("secondLine", "Town Centre");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN1 2DL");

    client.put("contactAddress", address);

    Response response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(client.toString()));

    if (response.getStatus() != HttpStatus.CREATED) throw new RuntimeException("Failed to create resource");
    System.out.println(response.getStatus());
  }

  @Test
  public void getAllClients()
  {
    String clients = this.client.target("http://localhost:8080/services/clients").request().get(String.class);

    System.out.println(clients);
  }

  @Test
  public void testClientSearch()
  {

    try {
      String email = java.net.URLEncoder.encode("james.mitchell81@live.co.uk", "UTF-8");
      String surname = java.net.URLEncoder.encode("Mitchell", "UTF-8");
      String telNumber = java.net.URLEncoder.encode("07789 558 138", "UTF-8");
      String data = "emailAddress=" + email + "&surname=" + surname + "&telNumber=" + telNumber;
      System.out.println(data);
      String search = this.client.target("http://localhost:8080/services/clients/search-client?" + data)
                                 .request()
                                 .get(String.class);
      System.out.println(search);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  @After
  public void clearDown()
  {

  }
}

