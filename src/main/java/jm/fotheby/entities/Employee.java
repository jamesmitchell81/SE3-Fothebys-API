package jm.fotheby.entities;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Embedded;

@Entity
public class Employee extends Person
{
  private String location;
  private String role;
  @Embedded private Credentials credentials;
  private Date startDate;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Credentials getCredentials() {
    return credentials;
  }

  public void setCredentials(Credentials credentials) {
    this.credentials = credentials;
  }
}