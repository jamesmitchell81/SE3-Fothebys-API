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

  @Ignore
  @Test
  public void intCountriesTest()
  {
    EntityManager em = Persistence.createEntityManagerFactory("$objectdb/db/country.odb").createEntityManager();

    // String countries = this.client.target("https://restcountries.eu/rest/v1/all")
    //                        .request().get(String.class);

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

      try {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
      } catch (PersistenceException e) {
        System.out.println(e.getMessage());
      }
    }

    List<Country> allcountries = em.createQuery("SELECT c FROM Country c", Country.class).getResultList();

    System.out.println(allcountries.size());


    for ( Country count : allcountries )
    {
      JSONObject j = new JSONObject(count);
      System.out.println(j.toString());
    }
  }

  @After
  public void cleanUp()
  {

  }
}