package jm.fotheby.entities;

import java.util.Set;
import java.util.Map;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="ITEM")
public class Item
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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

  private Map<String, String> attributes;

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
    this.category = category;
  }

  public void setClassifications(List<Classification> classifications)
  {
    this.classifications = classifications;
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

  public void setAttributes(Map<String, String> attributes)
  {
    // check category allows attribute.
    this.attributes = attributes;
  }

  public Category getCategory()
  {
    return this.category;
  }

  public List<Classification> getClassifications()
  {
    return this.classifications;
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

  public double getEstimatedPrice()
  {
    return this.estimatedPrice;
  }

  public String getProvenanceDetails()
  {
    return this.provenanceDetails;
  }

  public boolean getAuthenticated()
  {
    return this.authenticated;
  }

  public Map<String, String> getAttributes()
  {
    return this.attributes;
  }
}