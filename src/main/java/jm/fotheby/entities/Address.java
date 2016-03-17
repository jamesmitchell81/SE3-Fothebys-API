package jm.fotheby.entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Address
{
  private String name;
  private String firstLine;
  private String secondLine;
  private String townCity;
  private String postalCode;

  @ManyToOne
  private Country country;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstLine() {
    return firstLine;
  }

  public void setFirstLine(String firstLine) {
    this.firstLine = firstLine;
  }

  public String getSecondLine() {
    return secondLine;
  }

  public void setSecondLine(String secondLine) {
    this.secondLine = secondLine;
  }

  public String getTownCity() {
    return townCity;
  }

  public void setTownCity(String townCity) {
    this.townCity = townCity;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}