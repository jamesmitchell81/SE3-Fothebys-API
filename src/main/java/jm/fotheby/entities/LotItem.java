package jm.fotheby.entities;

import java.sql.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotItem
{
  @Id @GeneratedValue private Long id;
  @ManyToOne private Client client;
  @ManyToOne private Category category;
  @Embedded  private DatePeriod productionDate;
  @Embedded private ItemDimensions dimensions;

  private String itemName;
  private Date signingDate; // x
  private String textualDescription;
  // @Embedded private CurrencyValue estimatedPrice;
  private double estimatedPrice; // x
  private double reservePrice; // x
  private double salePrice; // x
  private Map<String, Map> attributes;

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public void setSigningDate(Date signingDate)
  {
    this.signingDate = signingDate;
  }

  public void setClient(Client client)
  {
    this.client = client;
  }

  public void setProductionDate(DatePeriod productionDate)
  {
    this.productionDate = productionDate;
  }

  public void setTextualDescription(String textualDescription)
  {
    this.textualDescription = textualDescription;
  }

  public void setCategory(Category category)
  {
    this.category = category;
  }

  // public void setEstimatedPrice(CurrencyValue estimatedPrice)
  public void setEstimatedPrice(double estimatedPrice)
  {
    this.estimatedPrice = estimatedPrice;
  }

  public void setDimensions(ItemDimensions dimensions)
  {
    this.dimensions = dimensions;
  }

  public void setAttributes(Map<String, Map> attributes)
  {
    this.attributes = attributes;
  }

  public void addAttribute(String key, Map value)
  {
  // category get attrbute of key
  // reject if not exists.
  }

  public Long getId()
  {
    return id;
  }

  public String getItemName()
  {
    return itemName;
  }

  public Date getSigningDate()
  {
    return signingDate;
  }

  public DatePeriod getProductionDate()
  {
    return productionDate;
  }

  public String getTextualDescription()
  {
    return textualDescription;
  }

  public Category getCategory()
  {
    return category;
  }

  public double getEstimatedPrice()
  {
    return estimatedPrice;
  }

  public ItemDimensions getDimensions()
  {
    return dimensions;
  }

  public Map<String, Map> getAttributes()
  {
    return attributes;
  }
}
