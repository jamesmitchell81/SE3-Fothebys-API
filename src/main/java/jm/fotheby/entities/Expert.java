package jm.fotheby.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@DiscriminatorValue("EXPERT")
@Table(name="EXPERT")
public class Expert extends Employee
{
  @ManyToOne
  private Category category;
  private List<Integer> specialties;

  public void setCategory(Category category)
  {
    this.category = category;
  }

  // public void setSpecialties(List<Classification> specialties)
  public void setSpecialties(List<Integer> specialties)
  {
    this.specialties = specialties;
  }

  public Category getCategory()
  {
    return this.category;
  }

  // public List<Classification> getSpecialties()
  public List<Integer> getSpecialties()
  {
    return this.specialties;
  }
}