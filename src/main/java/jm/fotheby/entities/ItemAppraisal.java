package jm.fotheby.entities;

import java.util.Set;
import java.sql.Date;
import javax.persistence.*;

@Entity
public class ItemAppraisal
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  private Item item;
  @ManyToOne
  private Client client;
  @ManyToOne
  private Expert expert;

  private Date appraisalDate;
  private String additionalNotes;
  private boolean agreement;
  private double estimatedPrice;
  private double agreedPrice;

  public void setItem(Item item)
  {
    this.item = item;
  }

  public void setClient(Client client)
  {
    this.client = client;
  }

  public void setExpert(Expert expert)
  {
    this.expert = expert;
  }

  public void setAppraisalDate(Date appraisalDate)
  {
    this.appraisalDate = appraisalDate;
  }

  public void setAdditionalNotes(String additionalNotes)
  {
    this.additionalNotes = additionalNotes;
  }

  public void setAgreement(boolean agreement)
  {
    this.agreement = agreement;
  }

  public void setEstimatedPrice(double estimatedPrice)
  {
    this.estimatedPrice = estimatedPrice;
  }

  public void setAgreedPrice(double agreedPrice)
  {
    this.agreedPrice = agreedPrice;
  }

  public Item getItem()
  {
    return this.item;
  }

  public Client getClient()
  {
    return client;
  }

  public Expert getExpert()
  {
    return this.expert;
  }

  public Date getAppraisalDate()
  {
    return this.appraisalDate;
  }

  public String getAdditionalNotes()
  {
    return this.additionalNotes;
  }

  public boolean getAgreement()
  {
    return this.agreement;
  }

  public double getEstimatedPrice()
  {
    return this.estimatedPrice;
  }

  public double getAgreedPrice()
  {
    return this.agreedPrice;
  }
}
