package jm.fotheby.entities;

import javax.persistence.*;

@Entity
public class Image
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private byte[] image;
  private String filename;
  private String extension;
  private long size;

  public Long getId()
  {
    return this.id;
  }

  public void setImage(byte[] image)
  {
    this.image = image;
  }

  public void setFilename(String filename)
  {
    this.filename = filename;
    // set extension
  }

  public void setSize(long size)
  {
    this.size = size;
  }

  public void setExtension(String extension)
  {
    this.extension = extension;
  }

  public byte[] getImage()
  {
    return this.image;
  }

  public String getFilename()
  {
    return this.filename;
  }

  public String getExtension()
  {
    return this.extension;
  }

  public long getSize()
  {
    return this.size;
  }
}
