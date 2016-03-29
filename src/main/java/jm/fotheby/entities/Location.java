package jm.fotheby.entities;

import javax.persistence.*;
import javax.jdo.annotations.Unique;

@Entity
public class Location
{
  @Id @GeneratedValue private int id;
  @Unique private String name;
  @Embedded private Address address;
  @ManyToOne private Country country;
  private String telNumber;
  private int capacity;

  public int getId()
  {
    return this.id;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setAddress(Address address)
  {
    this.address = address;
  }

  public void setCountry(Country country)
  {
    this.country = country;
  }

  public void setTelNumber(String telNumber)
  {
    this.telNumber = telNumber;
  }

  public void setCapacity(int capacity)
  {
    this.capacity = capacity;
  }

  public String getName()
  {
    return this.name;
  }

  public Address getAddress()
  {
    return this.address;
  }

  public Country getCountry()
  {
    return this.country;
  }

  public String getTelNumber()
  {
    return this.telNumber;
  }

  public int getCapacity()
  {
    return this.capacity;
  }

}