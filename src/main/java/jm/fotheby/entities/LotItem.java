package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotItem
{
    @Id @GeneratedValue
    private Long id;
    private String itemName;
    private Date signingDate;
    @Embedded
    private DatePeriod productionDate;
    private String textualDescription;
    @ManyToOne
    private Category category;
    // @Embedded
    // private CurrencyValue estimatedPrice;
    private double estimatedPrice;
    @Embedded
    private ItemDimensions dimensions;
    private Map<String, ItemAttribute> attributes;

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setSigningDate(Date signingDate)
    {
        this.signingDate = signingDate;
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

    public void addAttribute(String key, Map value)
    {
      // category get attrbute of key
      // reject if not exists.
    }
}
