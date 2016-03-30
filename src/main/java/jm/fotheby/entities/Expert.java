package jm.fotheby.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="EXPERT")
public class Expert implements Person, Employee
{
  @Id @GeneratedValue private int id;
  @Embedded private PersonalDetails personalDetails;
  // Personal Details. // implements person.
    // Title, FirstName, surname, TelNumber, EmailAddress
  @Embedded private EmployeeDetails employeeDetails;
  // Employee. .. implements employee.
    // Role, Location, Credetials, StartDate

  @ManyToOne
  private Category category;

  @ManyToMany
  @JoinTable(
    name="EXPERT_CLASSIFICATIONS",
    joinColumns={@JoinColumn(name="EXPERT_ID")},
    inverseJoinColumns={@JoinColumn(name="CLASSIFICATION_ID")}
  )
  private List<Classification> specialties;

  public void setCategory(Category category)
  {
    this.category = category;
  }

  public void setSpecialties(List<Classification> specialties)
  {
    this.specialties = specialties;
  }

  public Category getCategory()
  {
    return this.category;
  }

  public List<Classification> getSpecialties()
  {
    return this.specialties;

  }
}