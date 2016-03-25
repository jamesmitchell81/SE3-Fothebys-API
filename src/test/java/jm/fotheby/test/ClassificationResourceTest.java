import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.json.*;

public class ClassificationResourceTest
{

  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @Ignore
  @Test
  public void addClassificationText()
  {
    JSONObject classification = new JSONObject();

    classification.put("classification", "landscape");

    Response response = this.client.target("http:/localhost:8080/services/classifications")
                                   .request()
                                   .post(Entity.json(classification.toString()));


    // test

  }

  @After
  public void cleanUp()
  {

  }
}