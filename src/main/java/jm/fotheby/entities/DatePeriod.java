package jm.fotheby.entities;

import java.sql.Date;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.json.*;

@Embeddable
public class DatePeriod
{

  private Date storedDate;
  // So JSON will see the datestring to be parsed to LocalDate, set to sql.date and be stored!
  // @Transient
  private String productionDate = "";
  private String dateDescription = "";
  private int year;
  private int yearBetweenStart;
  private int yearBetweenEnd;

  public DatePeriod() {}

  public DatePeriod(JSONObject json)
  {
    this.productionDate = json.optString("productionDate");
    this.dateDescription = json.optString("dateDescription");
    this.year = json.optInt("year", -1);
    this.yearBetweenStart = json.optInt("yearBetweenStart", -1);
    this.yearBetweenEnd = json.optInt("yearBetweenEnd", -1);
  }

  public void setProductionDate(String productionDate)
  {
    this.productionDate = productionDate;
  }

  public String getProductionDate()
  {
    // set localdate
    // format date to string
    return this.productionDate; //this.productionDate;
  }

  public void setDateDescription(String dateDescription)
  {
    this.dateDescription = dateDescription;
  }

  public String getDateDescription()
  {
    return this.dateDescription;
  }

  public void setYear(int year)
  {
    this.year = year;
  }

  public int getYear()
  {
    return this.year;
  }

  public void setYearBetweenStart(int yearBetweenStart)
  {
    this.yearBetweenStart = yearBetweenStart;
  }

  public int getYearBetweenStart()
  {
    return this.yearBetweenStart;
  }

  public void setYearBetweenEnd(int yearBetweenEnd)
  {
    this.yearBetweenEnd = yearBetweenEnd;
  }

  public int getYearBetweenEnd()
  {
    return this.yearBetweenEnd;
  }

  private void parseDate()
  {
    // Regex year.

    // attempt to get other information
    // from data provided.
  }
}