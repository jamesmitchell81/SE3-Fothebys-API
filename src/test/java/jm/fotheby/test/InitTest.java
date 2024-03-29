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

  // @Ignore
  @Test
  public void init()
  {
    this.initCategory();
    this.initClassification();
    this.initCountries();
    this.initLocation();
    this.initExpert();
    this.initClient();
  }

  public void initCategory()
  {
    String location;
    Response response;

    JSONObject root = new JSONObject();
    root.put("name", "Drawings");

    JSONArray attributes = new JSONArray();
    JSONObject attribute = new JSONObject();
    attribute.put("name", "Artist");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Medium");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Material");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);
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
    attribute = new JSONObject();
    attribute.put("name", "Artist");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Medium");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Framed");
    attribute.put("type", "true/false");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

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
    attribute = new JSONObject();
    attribute.put("name", "Artist");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Type");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Framed");
    attribute.put("type", "true/false");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

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
    attribute = new JSONObject();
    attribute.put("name", "Artist");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Material");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

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

    attribute = new JSONObject();
    attribute.put("name", "Artist");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

    attribute = new JSONObject();
    attribute.put("name", "Material");
    attribute.put("type", "text");
    attribute.put("required", true);
    attribute.put("active", true);
    attributes.put(attribute);

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
      // REFERENCE: data from: https://restcountries.eu
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

  public void initExpert()
  {
    JSONObject expert = new JSONObject();
    expert.put("title", "Mr.");
    expert.put("firstName", "James");
    expert.put("surname", "Mitchell");
    expert.put("emailAddress", "james.mitchell@logo.co.uk");
    expert.put("telNumber", "07729 388 158");
    expert.put("role", "expert");

    JSONObject address = new JSONObject();
    address.put("firstLine", "2 The Avenue");
    address.put("secondLine", "Abington");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN1 5HL");

    expert.put("contactAddress", address);

    JSONObject location = new JSONObject();
    location.put("id", 1);

    JSONObject category = new JSONObject();
    category.put("id", 1);

    JSONArray classification = new JSONArray();
    JSONObject c1 = new JSONObject();
    c1.put("id", 1);
    JSONObject c2 = new JSONObject();
    c2.put("id", 2);
    classification.put(c1);
    classification.put(c2);

    expert.put("location", location);
    expert.put("category", category);
    expert.put("specialties", classification);

    Response response = this.client.target("http://localhost:8080/services/expert")
                                   .request()
                                   .post(Entity.json(expert.toString()));
    response.close();

    expert = new JSONObject();
    expert.put("title", "Mr.");
    expert.put("firstName", "Chris");
    expert.put("surname", "Powell");
    expert.put("emailAddress", "chris@powell.co.uk");
    expert.put("telNumber", "07759 642 215");
    expert.put("role", "expert");

    address = new JSONObject();
    address.put("firstLine", "92 The Valley");
    address.put("secondLine", "Charlton");
    address.put("townCity", "Charlton");
    address.put("postalCode", "SE7 6GH");

    expert.put("contactAddress", address);

    location = new JSONObject();
    location.put("id", 2);

    category = new JSONObject();
    category.put("id", 2);

    classification = new JSONArray();
    c1 = new JSONObject();
    c1.put("id", 3);
    c2 = new JSONObject();
    c2.put("id", 4);
    classification.put(c1);
    classification.put(c2);

    expert.put("location", location);
    expert.put("category", category);
    expert.put("specialties", classification);

    response = this.client.target("http://localhost:8080/services/expert")
                                   .request()
                                   .post(Entity.json(expert.toString()));
    response.close();
  }

  public void initClient()
  {
    JSONObject root = new JSONObject();
    root.put("title", "Mr.");
    root.put("firstName", "Owen");
    root.put("surname", "Router");
    root.put("emailAddress", "owen@router.co.uk");
    root.put("telNumber", "07754 112 357");
    root.put("registered", false);

    JSONObject address = new JSONObject();
    address.put("firstLine", "23 The Lane");
    address.put("secondLine", "Hunsbury");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN4 AAA");

    root.put("contactAddress", address);
    root.put("country", "GBR");

    Response response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(root.toString()));
    response.close();

    root = new JSONObject();
    root.put("title", "Miss");
    root.put("firstName", "Isla");
    root.put("surname", "Router");
    root.put("emailAddress", "isla@router.co.uk");
    root.put("telNumber", "07754 112 357");
    root.put("registered", false);

    address = new JSONObject();
    address.put("firstLine", "23 The Grange");
    address.put("secondLine", "Hunsbury");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN4 A7P");

    root.put("contactAddress", address);
    root.put("country", "GBR");

    response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(root.toString()));
    response.close();

    root = new JSONObject();
    root.put("title", "Mrs");
    root.put("firstName", "Sally");
    root.put("surname", "Router");
    root.put("emailAddress", "sally@router.co.uk");
    root.put("telNumber", "07754 112 357");
    root.put("registered", false);

    address = new JSONObject();
    address.put("firstLine", "29 The Lane");
    address.put("secondLine", "Duston");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN5 5FP");

    root.put("contactAddress", address);
    root.put("country", "GBR");

    response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(root.toString()));
    response.close();

    root = new JSONObject();
    root.put("title", "Mr");
    root.put("firstName", "Adam");
    root.put("surname", "Router");
    root.put("emailAddress", "adam@router.co.uk");
    root.put("telNumber", "07754 112 357");
    root.put("registered", false);

    address = new JSONObject();
    address.put("firstLine", "56 The Park");
    address.put("secondLine", "Abington");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN2 5HG");

    root.put("contactAddress", address);
    root.put("country", "GBR");

    response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(root.toString()));
    response.close();

    root = new JSONObject();
    root.put("title", "Mr");
    root.put("firstName", "Simon");
    root.put("surname", "Mitchell");
    root.put("emailAddress", "simon@mitchell.co.uk");
    root.put("telNumber", "07785 665 954");
    root.put("registered", false);

    address = new JSONObject();
    address.put("firstLine", "48 The Gardens");
    address.put("secondLine", "Husbury");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN4 9GH");

    root.put("contactAddress", address);
    root.put("country", "GBR");

    response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(root.toString()));
    response.close();

    root = new JSONObject();
    root.put("title", "Mrs");
    root.put("firstName", "Sue");
    root.put("surname", "Mitchell");
    root.put("emailAddress", "sue@mitchell.co.uk");
    root.put("telNumber", "07786 452 682");
    root.put("registered", false);

    address = new JSONObject();
    address.put("firstLine", "99 The Hill");
    address.put("secondLine", "Camp Hill");
    address.put("townCity", "Northampton");
    address.put("postalCode", "NN4 9GH");

    root.put("contactAddress", address);
    root.put("country", "GBR");

    response = this.client.target("http://localhost:8080/services/clients")
                            .request()
                            .post(Entity.json(root.toString()));
    response.close();
  }
}