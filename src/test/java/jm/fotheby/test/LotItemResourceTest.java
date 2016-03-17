package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import java.util.HashMap;

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

public class LotItemResourceTest
{
  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @Ignore
  @Test
  public void testCreateLotItem()
  {
    JSONObject root = new JSONObject();
    root.put("itemName", "Test Item");
    root.put("textualDescription", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas id dolorum, ratione porro itaque? Consectetur ratione, saepe dolore sunt aspernatur nesciunt cum suscipit rerum a architecto quidem esse maiores provident!");
    root.put("estimatedPrice", 1.054);

    JSONObject productionDate = new JSONObject();
    productionDate.put("dateDescription", "18th Century");

    JSONObject category = new JSONObject();
    category.put("name", "Drawing");

    JSONObject attributes = new JSONObject();
    HashMap<String, String> attribute = new HashMap<String, String>();
    attribute.put("Medium", "Pencil");
    attributes.put("Medium", attribute);

    JSONObject dimensions = new JSONObject();
    dimensions.put("baseMeasure", "CM");
    dimensions.put("length", 10);
    dimensions.put("width",  20);
    dimensions.put("height", 30);

    root.put("productionDate", productionDate);
    root.put("category", category);
    root.put("attributes", attributes);
    root.put("dimensions", dimensions);

    Response response = this.client.target("http://localhost:8080/services/lot-item")
                            .request()
                            .post(Entity.json(root.toString()));

    // assertTrue(HttpStatus.CREATED == response.getStatus());
    response.close();
  }

  @Test
  public void getAllLotItems()
  {
    String items = client.target("http://localhost:8080/services/lot-item").request().get(String.class);
    System.out.println(items);
  }

  @After
  public void cleanUp()
  {
    this.client.close();
  }
}