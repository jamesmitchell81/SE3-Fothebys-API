package jm.fotheby.entities;

import java.sql.Date;

import javax.persistence.Embeddable;

@Embeddable
public class DatePeriod
{
  // private Date productionDate;
  private String dateDescription;
  // private int year;

  public DatePeriod() {}

  // public DatePeriod(Date date) {}
  public DatePeriod(String dateDescription)
  {
    this.dateDescription = dateDescription;
  }

  public void setDateDescription(String dateDescription)
  {
    this.dateDescription = dateDescription;
  }

  public String getDateDescription()
  {
    return this.dateDescription;
  }

  // public DatePeriod(int year) {}

  // public Date getDate() {}
  // public String getDescription() {}
  // public int getYear() {}

  private void parseDate()
  {
    // Regex year.

    // attempt to get other information
    // from data provided.
  }
}