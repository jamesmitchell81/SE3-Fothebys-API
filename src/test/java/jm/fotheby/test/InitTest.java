package jm.fotheby.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import jm.fotheby.entities.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import javax.persistence.*;

import org.json.*;
import java.util.*;

public class InitTest
{
  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @After
  public void cleanUp()
  {

  }

  @Ignore
  @Test
  public void testName()
  {
    this.initCategory();
    this.initClassification();
    this.initCountries();
    this.initLocation();
  }

  public void initCategory()
  {
    String location;
    Response response;

    JSONObject root = new JSONObject();
    root.put("name", "Drawings");

    JSONArray attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                            .request()
                            .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Paintings");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Photographic Images");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Sculptures");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Carvings");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();
  }

  public void initClassification()
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

  public void initCountries()
  {
    String countries = "";

    try {
      String file = "/Users/jm/Development/SE3/SE3-Fothebys-API/countries.json";
      BufferedReader br = new BufferedReader(new FileReader(file));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while ( line != null )
      {
        sb.append(line);
        line = br.readLine();
      }
      countries = sb.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }

    JSONArray cArr = new JSONArray(countries);

    for ( int i = 0; i < cArr.length(); i++ )
    {
      JSONObject obj = cArr.getJSONObject(i);
      Country c = new Country();

      if ( obj.has("name") )
        c.setName(obj.getString("name"));

      if ( obj.has("region") )
        c.setRegion(obj.getString("region"));

      if ( obj.has("alpha3Code") )
        c.setShortCode(obj.getString("alpha3Code"));

      if ( obj.has("currencies") )
      {
        Object k = obj.get("currencies");

        if ( k instanceof JSONArray ) {
          JSONArray currencies = obj.getJSONArray("currencies");
          List<String> crns = new ArrayList();

          for ( int j = 0; j < currencies.length(); j++ )
          {
            crns.add(currencies.getString(j));
          }
          c.setCurrency(crns);
        }
      }

      if ( obj.has("timezones") )
      {
        Object t = obj.get("timezones");

        if ( t instanceof JSONArray ) {
          JSONArray tzs = obj.getJSONArray("timezones");
          List<String> tzsarr = new ArrayList();

          for ( int j = 0; j < tzs.length(); j++ )
          {
            tzsarr.add(tzs.getString(j));
          }
          c.setTimezones(tzsarr);
        }
      }

      JSONObject json = new JSONObject(c);

      Response response = this.client.target("http://localhost:8080/services/countries")
                          .request()
                          .post(Entity.json(json.toString()));
      response.close();
    } // end for

  }

  public void initLocation()
  {
    Response response;
    JSONObject root;
    JSONObject country;
    JSONObject address;

    root = new JSONObject();
    root.put("name", "London");
    root.put("capacity", 500);
    root.put("telNumber", "020 7946 0929");

    country = new JSONObject();
    country.put("shortCode", "GBR");

    root.put("country", country);

    address = new JSONObject();
    address.put("firstLine", "Fotheby's London");
    address.put("secondLine", "24 Carnaby Street");
    address.put("townCity", "London");
    address.put("postalCode", "W1F 7DB");

    root.put("address", address);

    response = this.client.target("http://localhost:8080/services/location")
                            .request()
                            .post(Entity.json(root.toString()));

    System.out.println(response.getStatus());

    response.close();

    root = new JSONObject();
    root.put("name", "Paris");
    root.put("capacity", 400);
    root.put("telNumber", "020 7236 0129");

    country = new JSONObject();
    country.put("shortCode", "FRA");

    root.put("country", country);

    address = new JSONObject();
    address.put("firstLine", "Fotheby's Paris");
    address.put("secondLine", "24 La Carnaby Street");
    address.put("townCity", "Paris");
    address.put("postalCode", "PF 7DB");

    root.put("address", address);

    response = this.client.target("http://localhost:8080/services/location")
                            .request()
                            .post(Entity.json(root.toString()));

    response.close();

    root = new JSONObject();
    root.put("name", "New York");
    root.put("capacity", 350);
    root.put("telNumber", "020 7946 0929");

    country = new JSONObject();
    country.put("shortCode", "USA");

    root.put("country", country);

    address = new JSONObject();
    address.put("firstLine", "Fotheby's New York");
    address.put("secondLine", "24 Carnaby Street");
    address.put("townCity", "Manhattern");
    address.put("postalCode", "W1F 7DB");

    root.put("address", address);

   response = this.client.target("http://localhost:8080/services/location")
                            .request()
                            .post(Entity.json(root.toString()));

    response.close();

  }



}