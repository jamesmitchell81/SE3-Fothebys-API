package jm.fotheby.entities;

public class ExpertSearchResult
{
  private Long id;
  private String title;
  private String firstName;
  private String surname;
  private String emailAddress;
  private Category category;
  private Location location;
  private String name;

  public ExpertSearchResult(Long id, String title, String firstName,
                            String surname, String emailAddress, Location location, Category category)
  {
    this.id = id;
    this.title = title;
    this.firstName = firstName;
    this.surname = surname;
    this.emailAddress = emailAddress;
    this.location = location;
    this.category = category;
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

  public Category getCategory()
  {
    return this.category;
  }

  public String getEmailAddress()
  {
    return this.emailAddress;
  }

  public Location getLocation()
  {
    return this.location;
  }
}