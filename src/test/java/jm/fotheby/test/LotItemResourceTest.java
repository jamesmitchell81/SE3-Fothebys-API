package jm.fotheby.test;

import jm.fotheby.util.HttpStatus;

import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.json.*;

public class LotItemResourceTest
{

  @Before
  public void setUp()
  {

  }

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

    JSONObject dimensions = new JSONObject();
    // dimensions.put("length", new Measure(10, "CM"));
    // dimensions.put("width",  new Measure(20, "CM"));
    // dimensions.put("height", new Measure(30, "CM"));


  }

  @After
  public void cleanUp()
  {

  }
}