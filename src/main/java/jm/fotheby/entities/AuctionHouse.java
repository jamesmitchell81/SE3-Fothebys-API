package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class AuctionHouse
{
  private static final Long serialVersionUID = 1L;

  private Address location;
  private TelNumber telNumber;
  private int capacity;
}