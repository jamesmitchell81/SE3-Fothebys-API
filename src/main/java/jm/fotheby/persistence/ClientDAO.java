package jm.fotheby.persistence;

import jm.fotheby.entities.Client;
import jm.fotheby.util.*;

import javax.persistence.*;
import java.util.*;

import org.json.*;

public class ClientDAO
{
  public void insert(Client client) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(client);
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public void update(int id, Client update) throws PersistenceException
  {
    Database db = new Database();
    db.connect();
    Client current = db.getEntityManager().find(Client.class, id);

    db.getEntityManager().getTransaction().begin();
    // current.setName(update.getName());
    // current.setAttributes(update.getAttributes());
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public List<Client> get()
  {
    Database db = new Database();
    db.connect();
    List<Client> list = db.getEntityManager().createQuery("SELECT c FROM Client c", Client.class)
                                               .getResultList();

    return list;
  }

  public Client get(String name)
  {
    Database db = new Database();
    db.connect();
    TypedQuery<Client> query = db.getEntityManager().createQuery("SELECT DISTINCT c FROM Client c WHERE name = :name", Client.class);
    query.setParameter("name", name);
    List<Client> list = query.getResultList();
    Client client = list.get(0);
    return client;
  }

  public Client get(int id)
  {
    Database db = new Database();
    db.connect();
    Client client = db.getEntityManager().find(Client.class, id);
    return client;
  }

  public Client get(JSONObject json)
  {
    Client client = new Client();

    if ( json.has("name") )
    {
      client = this.get(json.getString("name"));
    }

    if ( json.has("id") )
    {
      client = this.get(json.getInt("id"));
    }

    return client;
  }

}