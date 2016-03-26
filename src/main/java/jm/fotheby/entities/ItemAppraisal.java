package jm.fotheby.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;

@Entity
public class ItemAppraisal // extends.
{
  // @Id @GeneratedValue private Long id;
  @ManyToOne private Client client;
  @ManyToOne private Expert expert;
  private Date appraisalDate;
  private String additionalNotes;
  private boolean agreement;

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

  private String setAdditionNotes(String additionalNotes)
  {
    this.additionalNotes = additionalNotes;
  }

  private void setAgreement(boolean agreement)
  {
    this.agreement = agreement;
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
    this.appraisalDate = appraisalDate;
  }

  private String getAdditionNotes()
  {
    return this.additionalNotes;
  }

  private boolean getAgreement()
  {
    return this.agreement;
  }
}
