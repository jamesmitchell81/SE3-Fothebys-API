package jm.fotheby.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="EXPERT")
public class Expert extends Employee
{
  @ManyToOne
  private Category category;

  @ManyToMany
  @JoinTable(
    name="EXPERT_CLASSIFICATIONS",
    joinColumns={@JoinColumn(name="PERSON_ID")},
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