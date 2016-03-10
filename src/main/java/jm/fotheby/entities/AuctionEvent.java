package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistenance.GeneratedValue;
import javax.persistenance.Id;

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