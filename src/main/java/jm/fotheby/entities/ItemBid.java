package jm.fotheby.entities;

import javax.persistence.*;

@Embeddable
public class ItemBid
{
  @ManyToOne private Client client;
  private double bidValue;

  public void setClient()
  {
    this.client = client;
  }

  public void setBidValue(double bidValue)
  {
    this.bidValue = bidValue;
  }

  public Client getClient()
  {
    return this.client;
  }

  public double getBidValue()
  {
    return this.bidValue;
  }
}