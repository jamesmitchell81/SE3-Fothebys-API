package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class PaymentDetails
{
  private String nameOnCard;
  private String cardNumber;
  private String expiry;
  private String type;
  private String issueNo;
  private String securityCode;

  public String getNameOnCard()
  {
    return nameOnCard;
  }

  public void setNameOnCard(String nameOnCard)
  {
    this.nameOnCard = nameOnCard;
  }

  public String getCardNumber()
  {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber)
  {
    this.cardNumber = cardNumber;
  }

  public String getExpiry()
  {
    return expiry;
  }

  public void setExpiry(String expiry)
  {
    this.expiry = expiry;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getIssueNo()
  {
    return issueNo;
  }

  public void setIssueNo(String issueNo)
  {
    this.issueNo = issueNo;
  }

  public String getSecurityCode()
  {
    return securityCode;
  }

  public void setSecurityCode(String securityCode)
  {
    this.securityCode = securityCode;
  }

}