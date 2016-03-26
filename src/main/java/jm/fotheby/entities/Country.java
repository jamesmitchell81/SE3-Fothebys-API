package jm.fotheby.entities;

import javax.persistence.Entity;

@Entity
public class Country
{
  private String name;
  private String currency;
  private String timezone;
  private String callingCode;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getCurrency()
  {
    return currency;
  }

  public void setCurrency(String currency)
  {
    this.currency = currency;
  }

  public String getTimezone()
  {
    return timezone;
  }

  public void setTimezone(String timezone)
  {
    this.timezone = timezone;
  }

  public String getCallingCode()
  {
    return callingCode;
  }

  public void setCallingCode(String callingCode)
  {
    this.callingCode = callingCode;
  }
}