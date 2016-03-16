package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address // implements Serializable?
{
  private String name; // number
  private String firstLine;
  private String secondLine;
  private String postalCode;

  // @ManyToOne(...)
  private Country country;
}