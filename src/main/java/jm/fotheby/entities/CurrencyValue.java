package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class CurrencyValue
{

  // private Country country;
  private double value;

  public void setValue(double value)
  {
    this.value = value;
  }
}