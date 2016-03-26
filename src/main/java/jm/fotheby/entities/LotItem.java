package jm.fotheby.entities;

import java.sql.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotItem
{

  private double reservePrice;
  private double salePrice;
  // private bids
  @ManyToOne private Client buyer;
}
