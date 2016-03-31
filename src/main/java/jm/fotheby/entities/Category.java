package jm.fotheby.entities;

import java.util.Set;
import javax.persistence.*;

import javax.jdo.annotations.Unique;

@Entity
public class Category
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  @Unique private String name = "";
  private Set<String> attributes;

  public int getId()
  {
    return this.id;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void addAttribute(String attrName)
  {
    this.attributes.add(attrName);
  }

  public void setAttributes(Set<String> attributes)
  {
    this.attributes = attributes;
  }

  public String getName()
  {
    return this.name;
  }

  public Set<String> getAttributes()
  {
    return this.attributes;
  }
}