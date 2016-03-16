package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import org.junit.Test;
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

  @Test
  public void createClientTest()
  {
    // jm.fotheby.entities.Client testClient = new jm.fotheby.entities.Client("Mr", "James", "Mitchell");
    String json = "{ 'title' : 'Mr', 'firstName' : 'James', 'surname' : 'Mitchell' }";
    Response response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(json));

    if (response.getStatus() != HttpStatus.CREATED) throw new RuntimeException("Failed to create resource");


  }

  @After
  public void clearDown()
  {

  }
}

