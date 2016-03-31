package jm.fotheby.util;

import javax.persistence.*;

public class Database
{
  private EntityManagerFactory emf;
  private EntityManager em;

  public void connect()
  {
    this.emf = Persistence.createEntityManagerFactory("$objectdb/db/fotheby.odb");
    this.em = this.emf.createEntityManager();
  }

  public EntityManager getEntityManager()
  {
    return this.em;
  }

  public void close()
  {
    this.em.close();
    this.emf.close();
  }
}