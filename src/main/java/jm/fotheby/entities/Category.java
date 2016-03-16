package jm.fotheby.entities;

import java.util.Set;
import java.util.LinkedHashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.jdo.annotations.Unique;

// @Entity
public class Category
{


  // @Unique ?
  private String name;
  private Set<String> attributes;

  public Category()
  {
    this.attributes = new LinkedHashSet<String>();
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void addAttribute(String attrName)
  {
    this.attributes.add(attrName);
  }
}