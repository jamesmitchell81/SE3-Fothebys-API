package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistenance.Entity;

@Entity
public class AuctionHouse implements Serializable
{
  private static final Long serialVersionUID = 1L;

  private Address location;
  private TelNumber telNumber;
  private int capacity;
}