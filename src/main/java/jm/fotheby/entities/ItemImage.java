package jm.fotheby.entities;

import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
public class ItemImage
{
  @Column(name="REFERENCE_ID")
  private Long itemReference;

  private String filename;
  private String altText;

  private byte[] image;

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

  public void setFilename(String filename)
  {
    this.filename = filename;
  }

  public void setAltText(String altText)
  {
    this.altText = altText;
  }

  public void setImage(byte[] image)
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

  public String getFilename()
  {
    return this.filename;
  }

  public String getAltText()
  {
    return this.altText;
  }

  public byte[] getImage()
  {
    return this.image;
  }

}