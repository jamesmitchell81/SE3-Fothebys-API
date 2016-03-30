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
  public void getClassificiations()
  {
    String response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .get(String.class);

    System.out.println(response);
  }

  @Ignore
  @Test
  public void getClassificiationById()
  {
    String response = this.client.target("http://localhost:8080/services/classification/1")
                                 .request()
                                 .get(String.class);

    System.out.println(response);

  }

  @Ignore
  @Test
  public void initClassifications()
  {

  }

  @After
  public void cleanUp()
  {

  }
}