package jm.fotheby.entities;

import javax.persistence.*;

@Entity
public class ItemImage
{
  @Column(name="REFERENCE_ID")
  private Long itemReference;
  private String altText;

  @ManyToOne
  private Image image;

  private int displayWidth;
  private int displayHeight;
  private int frameX;
  private int frameY;

  public ItemImage(Long itemReference)
  {
    this.itemReference = itemReference;
  }

  public Long getItemReference()
  {
    return this.itemReference;
  }

  public void setAltText(String altText)
  {
    this.altText = altText;
  }

  public void setImage(Image image)
  {
    this.image = image;
  }

  public void setDisplayDimensions(int displayWidth, int displayHeight)
  {
    this.displayWidth = displayWidth;
    this.displayHeight = this.displayHeight;
  }

  public void setFrameStart(int frameX, int frameY)
  {
    this.frameX = frameX;
    this.frameY = frameY;
  }

  public String getAltText()
  {
    return this.altText;
  }

  public Image getImage()
  {
    return this.image;
  }

}