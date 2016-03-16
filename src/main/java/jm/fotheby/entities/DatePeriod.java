package jm.fotheby.entities;

import java.sql.Date;

// @Embeddable
public class DatePeriod
{

  // id.

  private Date productionDate;
  private String dateDescription;
  private int year;

  // public DatePeriod(Date date) {}
  public DatePeriod(String dateDescription)
  {
    // this.parseDate
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