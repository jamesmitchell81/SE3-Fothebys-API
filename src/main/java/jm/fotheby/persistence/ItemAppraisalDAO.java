package jm.fotheby.persistence;

import jm.fotheby.entities.*;
import jm.fotheby.util.*;

import javax.persistence.*;
import java.util.*;

import org.json.*;

public class ItemAppraisalDAO
{
  public void insert(ItemAppraisal ia) throws PersistenceException
  {
    Database db = new Database();
    db.connect();
    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(ia);
    db.getEntityManager().getTransaction().commit();
    db.close();
  }

  public void update(int id, ItemAppraisal update) throws PersistenceException
  {
    Database db = new Database();
    ItemAppraisal ia = this.get(id);
    db.connect();

    db.getEntityManager().getTransaction().begin();
    ia.setClient(update.getClient());
    ia.setExpert(update.getExpert());
    ia.setAdditionalNotes(update.getAdditionalNotes());
    ia.setAgreement(update.getAgreement());
    ia.setEstimatedPrice(update.getEstimatedPrice());
    ia.setAgreedPrice(update.getAgreedPrice());
    db.getEntityManager().getTransaction().commit();
    db.close();
  }

  public List<ItemAppraisal> get()
  {
    Database db = new Database();
    db.connect();
    List<ItemAppraisal> list = db.getEntityManager()
                                 .createQuery("SELECT ia FROM ItemAppraisal ia", ItemAppraisal.class)
                                 .getResultList();
    return list;
  }

  public ItemAppraisal get(int id)
  {
    Database db = new Database();
    db.connect();

    TypedQuery<ItemAppraisal> query = db.getEntityManager()
                                        .createQuery("SELECT DISTINCT ia FROM ItemAppraisal ia WHERE ia.item.id = :id", ItemAppraisal.class);
    query.setParameter("id", id);
    ItemAppraisal ia = query.getSingleResult();
    return ia;
  }

}