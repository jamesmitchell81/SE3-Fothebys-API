package jm.fotheby.persistence;

import jm.fotheby.entities.Country;
import javax.persistence.*;
import java.util.*;

import org.json.*;

public class CountryDAO
{

  private EntityManager em;

  public CountryDAO()
  {
    this.em = Persistence.createEntityManagerFactory("$objectdb/db/country.odb")
                         .createEntityManager();
  }

  public CountryDAO(EntityManager em)
  {
    this.em = em;
  }

  public List<Country> get()
  {
    return this.em.createQuery("SELECT c FROM Country c", Country.class)
                  .getResultList();
  }

  public Country get(int id)
  {
    return this.em.find(Country.class, id);
  }

  public Country get(String name)
  {
    TypedQuery<Country> query = em.createQuery("SELECT DISTINCT c FROM Country c WHERE name = '" + name + "'", Country.class);
    query.setParameter("name", name);
    List<Country> list = query.getResultList();
    return list.get(0);
  }

  public Country getByShortCode(String shortCode)
  {
    TypedQuery<Country> query = em.createQuery("SELECT DISTINCT c FROM Country c WHERE shortCode = :shortCode", Country.class);
    query.setParameter("shortCode", shortCode);
    List<Country> list = query.getResultList();
    return list.get(0);
  }

  public List<Country> getCountriesByRegion(String region)
  {
    TypedQuery<Country> query = em.createQuery("SELECT DISTINCT c FROM Country c WHERE region = :region", Country.class);
    query.setParameter("region", region);
    return query.getResultList();
  }

  public Country get(JSONObject json)
  {
    Country country = new Country();

    if ( json.has("name") )
    {
      country = this.get(json.getString("name"));
    }

    if ( json.has("id") )
    {
      return this.get(json.getInt("id"));
    }

    if ( json.has("shortCode") )
    {
      return this.getByShortCode(json.getString("shortCode"));
    }

    return country;
  }

}