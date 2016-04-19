package jm.fotheby.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class LotCollection
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="COLLECTION_ID")
  private Long id;
  private String collectionTitle;

  @OneToMany(cascade=CascadeType.ALL, mappedBy="lotCollection")
  private List<LotItem> lotItems;

  public void setCollectionTitle(String collectionTitle)
  {
    this.collectionTitle = collectionTitle;
  }

  public String getCollectionTitle()
  {
    return collectionTitle;
  }

  public void addLotItem(LotItem item)
  {
    this.lotItems.add(item);
  }

  public void addLotItem(int lotNumber, LotItem item)
  {
    this.lotItems.add(lotNumber, item);
  }

  public LotItem getLotItem(int index)
  {
    return lotItems.get(index);
  }

  public List<LotItem> getLotItems()
  {
    return this.lotItems;
  }
}