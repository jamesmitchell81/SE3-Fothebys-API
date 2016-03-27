package jm.fotheby.persistence;

import jm.fotheby.entities;
import javax.persistance.*;

public class ClassificationDAO
{
  private EntityManager em;

  public ClassificationDAO(EntityManager em)
  {
    this.em = em;
  }

  public void save(Classification classification) throws PersistanceException
  {
    // this.em.getTransaction().begin();
    // this.em.persist(classification);
    // this.em.getTransaction().commit();
  }

  public void insert(Classification classification) throws PersistanceException
  {
    this.em.getTransaction().begin();
    this.em.persist(classification);
    this.em.getTransaction().commit();
  }
}

