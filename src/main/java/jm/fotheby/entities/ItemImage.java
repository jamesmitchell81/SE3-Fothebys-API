package jm.fotheby.entities;

import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
public class ItemImage
{
  @Column(name="REFERENCE_ID")
  private Long itemReference;

  private String fileName;
  private String altText;
  // private String caption;

  private byte[] original;

  private int displayWidth;
  private int displayHeight;

  private int frameX;
  private int frameY;
}