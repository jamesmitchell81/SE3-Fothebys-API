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

  @Test
  public void getClassificiations()
  {
    String response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .get(String.class);

    System.out.println(response);
  }

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
    // landscape, seascape, portrait, figure, still life, nude, animal, abstract, or other

    JSONObject json = new JSONObject();
    Response response;

    json.put("name", "landscape");
    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "seascape");
    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "portrait");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "figure");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "still life");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "nude");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));
    response.close();

    json = new JSONObject();
    json.put("name", "animal");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));
    response.close();

    json = new JSONObject();
    json.put("name", "abstract");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();
  }

  @After
  public void cleanUp()
  {

  }
}