package jm.fotheby.entities;

import java.util.List;
import javax.persistence.*;

import javax.jdo.annotations.Unique;

@Entity
public class Country
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Unique private String name;
  private List<String> currency;
  private List<String> timezones;
  private String shortCode;
  private String region;

  public Long getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public List<String> getCurrency()
  {
    return currency;
  }

  public void setCurrency(List<String> currency)
  {
    this.currency = currency;
  }

  public List<String> getTimezones()
  {
    return timezones;
  }

  public void setTimezones(List<String> timezones)
  {
    this.timezones = timezones;
  }

  public void setShortCode(String shortCode)
  {
    this.shortCode = shortCode;
  }

  public String getShortCode()
  {
    return this.shortCode;
  }

  public void setRegion(String region)
  {
    this.region = region;
  }

  public String getRegion()
  {
    return this.region;
  }
}