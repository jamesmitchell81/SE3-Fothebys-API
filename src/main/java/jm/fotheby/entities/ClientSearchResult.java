package jm.fotheby.entities;

public class ClientSearchResult
{
  private Long id;
  private String title;
  private String firstName;
  private String surname;
  private String addressFirstLine;
  private String name;

  public ClientSearchResult(Long id, String title, String firstName,
                            String surname, String firstLine)
  {
    this.id = id;
    this.title = title;
    this.firstName = firstName;
    this.surname = surname;
    this.addressFirstLine = firstLine;

    this.name = this.title + " " + this.firstName + " " + this.surname;
  }

  public Long getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getAddressFirstLine()
  {
    return this.addressFirstLine;
  }
}