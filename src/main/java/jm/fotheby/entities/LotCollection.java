package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistenance.Entity;
import javax.persistenance.GeneratedValue;
import javax.persistenance.Id;

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