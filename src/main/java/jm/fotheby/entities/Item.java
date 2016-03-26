package jm.fotheby.entities;

import jm.fotheby.services.CategoryResource;

import java.util.Map;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Version;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name="ITEM")
public class Item
{
  @Id @GeneratedValue
  @Column(name="ITEM_ID")
  private Long id;

  @Version
  private Long version;

  @ManyToOne
  private Category category;

  @ManyToMany
  @JoinTable(
    name="ITEM_CLASSIFICATIONS",
    joinColumns={@JoinColumn(name="ITEM_ID")},
    inverseJoinColumns={@JoinColumn(name="CLASSIFICATION_ID")}
  )
  private List<Classification> classifications;

  @OneToMany(orphanRemoval=true)
  @JoinColumn(name="REFERENCE_ID", referencedColumnName="ITEM_ID")
  private List<ItemImage> images;

  private String itemName;
  @Embedded
  private DatePeriod productionDate;
  private String textualDescription;
  @Embedded
  private ItemDimensions dimensions;
  private String provenanceDetails;
  private boolean authenticated = false;
  private double estimatedPrice;

  private Map<String, ItemAttribute> attributes;

  public Long getId()
  {
    return this.id;
  }

  public Long getVersion()
  {
    return this.version;
  }

  public void setCategory(Category category)
  {
    this.category = CategoryResource.find(category.getName());
  }

  public void setClassifications(List<Classification> classifications)
  {
    this.classifications = classifications;
  }

  public void addClassification(Classification classifications)
  {
    this.classifications.add(classifications);
  }

  public void setImages(List<ItemImage> images)
  {
    this.images = images;
  }

  public void addImage(ItemImage image)
  {
    this.images.add(image);
  }

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public void setProductionDate(DatePeriod productionDate)
  {
    this.productionDate = productionDate;
  }

  public void setTextualDescription(String textualDescription)
  {
    this.textualDescription = textualDescription;
  }

  public void setDimensions(ItemDimensions dimensions)
  {
    this.dimensions = dimensions;
  }

  public void setEstimatedPrice(double estimatedPrice)
  {
    this.estimatedPrice = estimatedPrice;
  }

  public void setProvenanceDetails(String provenanceDetails)
  {
    this.provenanceDetails = provenanceDetails;
  }

  public void setAuthenticated(boolean authenticated)
  {
    this.authenticated = authenticated;
  }

  public void setAttributes(Map<String, ItemAttribute> attributes)
  {
    // check category allows attribute.
    this.attributes = attributes;
  }

  public void addAttribute(String key, ItemAttribute value)
  {
    this.attributes.put(key, value);
  }

  public Category getCategory()
  {
    return this.category;
  }

  public List<Classification> getClassifications()
  {
    return this.classifications;
  }

  public Classification getClassification(int id)
  {
    // check out of range
    return this.classifications.get(id);
  }

  public List<ItemImage> getImages()
  {
    return this.images;
  }

  public ItemImage getImage(int id)
  {
    return this.images.get(id);
  }

  public String getItemName()
  {
    return this.itemName;
  }

  public DatePeriod getProductionDate()
  {
    return this.productionDate;
  }

  public String getTextualDescription()
  {
    return this.textualDescription;
  }

  public ItemDimensions getDimensions()
  {
    return this.dimensions;
  }

  public double setEstimatedPrice()
  {
    return this.estimatedPrice;
  }

  public String setProvenanceDetails()
  {
    return this.provenanceDetails;
  }

  public boolean setAuthenticated()
  {
    return this.authenticated;
  }

  public Map<String, ItemAttribute> getAttributes()
  {
    // check category allows attribute.
    return this.attributes;
  }

  public ItemAttribute getAttribute(String key)
  {
    return this.attributes.get(key);
  }

}