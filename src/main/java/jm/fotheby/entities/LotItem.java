package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotItem implements Serializable
{
    @Id @GeneratedValue
    Long id;

    private String itemName;
    private Date signingDate;

    private DatePeriod productionDate;

    private String textualDescription;

    // @ManyToOne
    private Category category; // !!

    // currency...
    private Double estimatedPrice;

    // @Embedded.
    private ItemDimensions dimensions;

    // Artist -> Name, Value.
    // Medium -> Name, Value.
    // Material.
    // Weight.
    // Classification.
    private Map<String, Map> attributes;

    public void addAttribute(String key, Map value)
    {
      // category get attrbute of key
      // reject if not exists.



    }
}
