package jm.fotheby.persistence;

import jm.fotheby.entities.Country;
import jm.fotheby.util.Database;

import javax.persistence.*;
import java.util.*;

import org.json.*;

public class CountryDAO
{

  public void insert(Country country) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(country);
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public List<Country> get()
  {
    Database db = new Database();
    db.connect();
    List<Country> list = db.getEntityManager().createQuery("SELECT c FROM Country c", Country.class)
                  .getResultList();
    db.close();
    return list;
  }

  public Country get(int id)
  {
    Database db = new Database();
    db.connect();
    Country country = db.getEntityManager().find(Country.class, id);
    db.close();
    return country;
  }

  public Country get(String name)
  {
    Database db = new Database();
    db.connect();
    TypedQuery<Country> query = db.getEntityManager().createQuery("SELECT DISTINCT c FROM Country c WHERE name = '" + name + "'", Country.class);
    query.setParameter("name", name);
    List<Country> list = query.getResultList();
    Country country = list.get(0);
    db.close();
    return country;
  }

  public Country getByShortCode(String shortCode)
  {
    Country country = new Country();
    Database db = new Database();
    db.connect();

    TypedQuery<Country> query = db.getEntityManager().createQuery("SELECT DISTINCT c FROM Country c WHERE shortCode = :shortCode", Country.class);
    query.setParameter("shortCode", shortCode);
    List<Country> list = query.getResultList();
    country = list.get(0);

    db.close();

    return country;
  }

  public List<Country> getCountriesByRegion(String region)
  {
    Database db = new Database();
    db.connect();
    TypedQuery<Country> query = db.getEntityManager().createQuery("SELECT DISTINCT c FROM Country c WHERE region = :region", Country.class);
    query.setParameter("region", region);
    List<Country> list = query.getResultList();
    db.close();
    return list;
  }

  public Country get(JSONObject json)
  {
    Country country = new Country();

    if ( json.has("name") )
    {
      return this.get(json.getString("name"));
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