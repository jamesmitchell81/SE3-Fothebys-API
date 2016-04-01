package jm.fotheby.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Address
{
  private String firstLine = "";
  private String secondLine = "";
  private String townCity = "";
  private String postalCode = "";

  public String getFirstLine()
  {
    return firstLine;
  }

  public void setFirstLine(String firstLine)
  {
    this.firstLine = firstLine;
  }

  public String getSecondLine()
  {
    return secondLine;
  }

  public void setSecondLine(String secondLine)
  {
    this.secondLine = secondLine;
  }

  public String getTownCity()
  {
    return townCity;
  }

  public void setTownCity(String townCity)
  {
    this.townCity = townCity;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public void setPostalCode(String postalCode)
  {
    this.postalCode = postalCode;
  }
}