package jm.fotheby.entities;

import java.util.Set;
import javax.persistence.*;

@Entity
@DiscriminatorValue("EXPERT")
@Table(name="EXPERT")
public class Expert extends Employee
{
  @ManyToOne
  private Category category;

  // @ManyToMany(fetch=FetchType.EAGER)
  // @JoinTable(
  //   name="EXPERT_CLASSIFICATIONS",
  //   joinColumns={@JoinColumn(name="EXPERT_ID")},
  //   inverseJoinColumns={@JoinColumn(name="CLASSIFICATION_ID")}
  // )
  // private List<Classification> specialties;

  private Set<Integer> specialties;

  public void setCategory(Category category)
  {
    this.category = category;
    System.out.println("!" + category.getId());
  }

  // public void setSpecialties(List<Classification> specialties)
  public void setSpecialties(Set<Integer> specialties)
  {
    this.specialties = specialties;
  }

  public Category getCategory()
  {
    return this.category;
  }

  // public List<Classification> getSpecialties()
  public Set<Integer> getSpecialties()
  {
    return this.specialties;
  }
}