package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class TelNumber
{
  private String prefix; // country code.
  private String telNumber;
  private boolean validated; // authorised.

  public TelNumber(String telNumber)
  {
    this.setTelNumber(telNumber);
  }

  public void setTelNumber(String telNumber)
  {
    // validate.
    this.telNumber = telNumber;
  }
}