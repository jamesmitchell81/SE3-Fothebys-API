import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InitTest
{
  @Before
  public void setUp()
  {

  }

  @After
  public void cleanUp()
  {

  }

  @Test
  public void testName()
  {
    this.initCategory();
    this.initClassification();
    this.initCountries();
    this.initLocations();
  }

  public void initCategory()
  {
    String location;
    Response response;

    JSONObject root = new JSONObject();
    root.put("name", "Drawings");

    JSONArray attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                            .request()
                            .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Paintings");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Photographic Images");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Sculptures");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();

    root = new JSONObject();
    root.put("name", "Carvings");

    attributes = new JSONArray();
    attributes.put("Artist");
    attributes.put("Medium");
    attributes.put("Material");
    attributes.put("Weight");

    root.put("attributes", attributes);

    response = this.client.target("http://localhost:8080/services/category")
                          .request()
                          .post(Entity.json(root.toString()));

    if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    location = response.getLocation().toString();
    System.out.println(location);

    response.close();
  }

  public void initClassification()
  {
    // landscape, seascape, portrait, figure, still life, nude, animal, abstract, or other

    JSONObject json = new JSONObject();
    Response response;

    json.put("name", "landscape");
    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "seascape");
    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "portrait");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "figure");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "still life");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();

    json = new JSONObject();
    json.put("name", "nude");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));
    response.close();

    json = new JSONObject();
    json.put("name", "animal");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));
    response.close();

    json = new JSONObject();
    json.put("name", "abstract");

    response = this.client.target("http://localhost:8080/services/classification")
                          .request()
                          .post(Entity.json(json.toString()));

    response.close();
  }

  public void initCountries()
  {
    EntityManager em = Persistence.createEntityManagerFactory("$objectdb/db/country.odb").createEntityManager();

    // String countries = this.client.target("https://restcountries.eu/rest/v1/all")
    //                        .request().get(String.class);

    String countries = "";

    try {
      String file = "/Users/jm/Development/SE3/SE3-Fothebys-API/countries.json";
      BufferedReader br = new BufferedReader(new FileReader(file));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while ( line != null )
      {
        sb.append(line);
        line = br.readLine();
      }
      countries = sb.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }

    JSONArray cArr = new JSONArray(countries);

    for ( int i = 0; i < cArr.length(); i++ )
    {
      JSONObject obj = cArr.getJSONObject(i);
      Country c = new Country();

      if ( obj.has("name") )
        c.setName(obj.getString("name"));

      if ( obj.has("region") )
        c.setRegion(obj.getString("region"));

      if ( obj.has("alpha3Code") )
        c.setShortCode(obj.getString("alpha3Code"));

      if ( obj.has("currencies") )
      {
        Object k = obj.get("currencies");

        if ( k instanceof JSONArray ) {
          JSONArray currencies = obj.getJSONArray("currencies");
          List<String> crns = new ArrayList();

          for ( int j = 0; j < currencies.length(); j++ )
          {
            crns.add(currencies.getString(j));
          }
          c.setCurrency(crns);
        }
      }

      if ( obj.has("timezones") )
      {
        Object t = obj.get("timezones");

        if ( t instanceof JSONArray ) {
          JSONArray tzs = obj.getJSONArray("timezones");
          List<String> tzsarr = new ArrayList();

          for ( int j = 0; j < tzs.length(); j++ )
          {
            tzsarr.add(tzs.getString(j));
          }
          c.setTimezones(tzsarr);
        }
      }

      try {
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
      } catch (PersistenceException e) {
        System.out.println(e.getMessage());
      }
    }

    List<Country> allcountries = em.createQuery("SELECT c FROM Country c", Country.class).getResultList();

    System.out.println(allcountries.size());


    for ( Country count : allcountries )
    {
      JSONObject j = new JSONObject(count);
      System.out.println(j.toString());
    }

  }

  public void initLocation()
  {
    JSONObject root = new JSONObject();
    root.put("name", "London");
    root.put("capacity", 500);
    root.put("telNumber", "020 7946 0929");

    JSONObject country = new JSONObject();
    country.put("shortCode", "GBR");

    root.put("country", country);

    JSONObject address = new JSONObject();
    address.put("firstLine", "Fotheby's London");
    address.put("secondLine", "24 Carnaby Street");
    address.put("townCity", "London");
    address.put("postalCode", "W1F 7DB");

    root.put("address", address);

    Response response = this.client.target("http://localhost:8080/services/location")
                            .request()
                            .post(Entity.json(root.toString()));

    System.out.println(response.getStatus());
  }



}