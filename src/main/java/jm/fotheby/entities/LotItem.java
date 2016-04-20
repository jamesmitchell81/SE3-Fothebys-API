package jm.fotheby.entities;

import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class LotItem
{
  private boolean sold;
  private double salePrice;
  @Embedded private List<ItemBid> bids;
  @ManyToOne private Client buyer;

  @ManyToOne private ItemAppraisal itemAppraisal;

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="COLLECTION_ID")
  private LotCollection lotCollection;

  public void setItem(Item item)
  {
    this.item = item;
  }

  public void setSold(boolean sold)
  {
    this.sold = sold;
  }

  public void setSalePrice(double salePrice)
  {
    this.salePrice = salePrice;
  }

  public void addBid(ItemBid itemBid)
  {
    this.bids.add(itemBid);
  }

  public void setBids(List<ItemBid> itemBids)
  {
    this.bids = itemBids;
  }

  public void setBuyer(Client buyer)
  {
    this.buyer = buyer;
  }

  public boolean getSold()
  {
    return this.sold;
  }

  public double getReservePrice()
  {
    return this.itemAppraisal.getAgreedPrice();
  }

  public double getSalePrice()
  {
    return this.salePrice;
  }

  public ItemBid getBid(int index)
  {
    return this.bids.get(index);
  }

  public List<ItemBid> getBids()
  {
    return this.bids;
  }

  public Client getBuyer()
  {
    return this.buyer;
  }


}
