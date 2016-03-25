package jm.fotheby.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;

@Entity
public class ItemAppraisal
{
  @Id @GeneratedValue private Long id;
  // private appraisal request.
  @ManyToOne private Client client;
  @ManyToOne private Category category;
  @ManyToOne private Expert expert;

  // e.g. could be an abstract and a nude painting.
  @ManyToOne private Set<Classification> classifications;

  @Embedded private DatePeriod productionDate;
  @Embedded private ItemDimensions dimensions;

  private String itemName;
  private String artist;
  private double estimatedPrice;
  private String textualDescription;
  private String provenanceDetails;
  private String additionalNotes;
  private boolean authenticated = false;

  public void setClient(Client client)
  {
    this.client = client;
  }

  public void setCategory(Category category)
  {
    this.category = category;
  }

  public void setExpert(Expert expert)
  {
    this.expert = expert;
  }

  public void setClassifications(Set<Classification> classifications)
  {
    this.classifications = classifications;
  }

  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  public void setArtist(String artist)
  {
    this.artist = artist;
  }

  public void setEstimatedPrice(double estimatedPrice)
  {
    this.estimatedPrice = estimatedPrice;
  }

  public void setTextualDescription(String textualDescription)
  {
    this.textualDescription = textualDescription;
  }

  public void setProvenanceDetails(String provenanceDetails)
  {

  }

  public void addClassification(Classification classifications)
  {
    this.classifications.add(classifications);
  }
}