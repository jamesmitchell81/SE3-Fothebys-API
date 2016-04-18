package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;
import jm.fotheby.persistence.*;
import jm.fotheby.entities.*;
import jm.fotheby.util.*;

import java.util.*;
import java.time.LocalDate;

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

  // @Ignore
  @Test
  public void testCreateLotItem()
  {
    JSONObject root = new JSONObject();
    root.put("itemName", "Test Item");
    root.put("textualDescription", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas id dolorum, ratione porro itaque? Consectetur ratione, saepe dolore sunt aspernatur nesciunt cum suscipit rerum a architecto quidem esse maiores provident!");
    root.put("estimatedPrice", 1.054);
    root.put("provenanceDetails", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas id dolorum, ratione porro itaque? Consectetur ratione, saepe dolore sunt aspernatur nesciunt cum suscipit rerum a architecto quidem esse maiores provident!");
    root.put("authenticated", true);

    LocalDate date = LocalDate.of(2015, 6, 22);
    JSONObject productionDate = new JSONObject();
    productionDate.put("dateDescription", "18th Century");
    productionDate.put("productionDate", date.toString());
    productionDate.put("year", 2015);

    JSONObject category = new JSONObject();
    category.put("name", "Drawings");

    JSONArray attributes = new JSONArray();
    JSONObject attribute = new JSONObject();
    attribute.put("name", "Medium");
    attribute.put("value", "Pencil");
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Artist");
    attribute.put("value", "Mattisse");
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Framed");
    attribute.put("value", "Yes");
    attributes.put(attribute);

    JSONObject dimensions = new JSONObject();
    dimensions.put("baseMeasure", "CM");
    dimensions.put("length", 10);
    dimensions.put("width",  20);
    dimensions.put("height", 30);

    JSONArray classification = new JSONArray();
    JSONObject c1 = new JSONObject();
    c1.put("name", "nude");
    JSONObject c2 = new JSONObject();
    c2.put("name", "abstract");
    classification.put(c1);
    classification.put(c2);

    JSONArray imageIds = new JSONArray();
    imageIds.put("1");
    imageIds.put("2");
    imageIds.put("3");
    root.put("images", imageIds);

    root.put("productionDate", productionDate);
    root.put("category", category);
    root.put("attributes", attributes);
    root.put("dimensions", dimensions);
    root.put("classifications", classification);

    System.out.println(root.toString());

    Response response = this.client.target("http://localhost:8080/services/lot-item")
                            .request()
                            .post(Entity.json(root.toString()));

    // System.out.println(response.getStatus());

    // assertTrue(HttpStatus.CREATED == response.getStatus());
    // response.close();
  }

  @Ignore
  @Test
  public void getAllLotItems()
  {
    String items = client.target("http://localhost:8080/services/lot-item")
                         .request()
                         .get(String.class);

    System.out.println(items);
  }

  @After
  public void cleanUp()
  {
    this.client.close();
  }
}