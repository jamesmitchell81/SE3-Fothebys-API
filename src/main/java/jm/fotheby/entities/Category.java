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
  @ElementCollection(fetch=FetchType.EAGER)
  private Set<AttributeDefinition> attributes;

  public int getId()
  {
    return this.id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void addAttribute(AttributeDefinition attribute)
  {
    this.attributes.add(attribute);
  }

  public void setAttributes(Set<AttributeDefinition> attributes)
  {
    this.attributes = attributes;
  }

  public String getName()
  {
    return this.name;
  }

  public Set<AttributeDefinition> getAttributes()
  {
    return this.attributes;
  }
}