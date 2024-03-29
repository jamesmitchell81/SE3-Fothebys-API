package jm.fotheby.entities;

import java.sql.Date;
import javax.persistence.*;

@Entity
@DiscriminatorValue("EMPLOYEE")
@DiscriminatorColumn(name="EMPLOYEE_TYPE")
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="EMPLOYEE")
public class Employee extends Person
{
  @ManyToOne protected Location location;
  protected String role;
  @Embedded protected Credentials credentials;
  protected Date startDate;

  public Location getLocation()
  {
    return location;
  }

  public void setLocation(Location location)
  {
    this.location = location;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public Credentials getCredentials()
  {
    return credentials;
  }

  public void setCredentials(Credentials credentials)
  {
    this.credentials = credentials;
  }
}