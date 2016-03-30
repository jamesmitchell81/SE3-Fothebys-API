import org.junit.After;
import static org.junit.Assert.*;
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

public class CountryResourceTest
{
  Client client;

  @Before
  public void setUp()
  {
    this.client = ClientBuilder.newClient();
  }

  @Ignore
  @Test
  public void getAllCountriesTest()
  {
    String countries = this.client.target("http://localhost:8080/services/countries")
                                  .request().get(String.class);

    JSONArray arr = new JSONArray(countries);
    assertTrue(arr.length() == 247);
  }

  @Ignore
  @Test
  public void getCountryById()
  {
    String country = this.client.target("http://localhost:8080/services/countries/1")
                                  .request().get(String.class);

    JSONObject c = new JSONObject(country);
    String name = c.has("name") ? c.getString("name") : "";

    assertTrue(name.equals("Afghanistan"));
  }

  @After
  public void cleanUp()
  {

  }
}