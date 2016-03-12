package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.ArrayList;

@Entity
public class LotCollection implements Serializable
{
  @Id @GeneratedValue
  private int id;

  // auction event id
  // ||
  // auction event reference ( if the objectdb objects are references)

  private String collectionTitle;
  private ArrayList<LotItem> lotItems;

  public void addLotItem(LotItem item)
  {
    this.lotItems.add(item);
  }

  public void addLotItem(int lotNumber, LotItem item)
  {
    this.lotItems.add(lotNumber, item);
  }
}