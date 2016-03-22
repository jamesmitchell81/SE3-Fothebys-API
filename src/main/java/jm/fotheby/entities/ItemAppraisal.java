package jm.fotheby.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;

@Entity
public class ItemAppraisal
{
  // private appraisal request.
  @ManyToOne private Client client;
  @ManyToOne private Category category;
  @ManyToOne private Expert expert;
  @Embedded private DatePeriod productionDate;
  @Embedded private ItemDimensions dimensions;

  private String itemName;
  private double estimatedPrice;
  private String textualDescription;
  private String provenanceDetails;
  private String additionalNotes;
  private boolean authenticated = false;

}