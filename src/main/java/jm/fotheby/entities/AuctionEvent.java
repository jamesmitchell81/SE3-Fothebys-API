package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuctionEvent implements Serializable
{
  private static final Long serialVersionUID = 1L;

  @Id @GeneratedValue
  private int id;

  private AuctionSession session;
  private LotCollection collection;
  private Date date;
  private AuctionHouse location;
}