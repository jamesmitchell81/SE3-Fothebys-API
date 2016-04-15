package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuctionEvent
{
  @Id @GeneratedValue
  private int id;

  private AuctionSession session;
  private LotCollection collection;
  private Date date;
  private Location location;
}