package jm.fotheby.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Address
{
  private String name; // number
  private String firstLine;
  private String secondLine;
  private String postalCode;

  @ManyToOne
  private Country country;
}