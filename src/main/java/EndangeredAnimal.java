import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.apache.commons.lang3.EnumUtils;



public class EndangeredAnimal extends Animal {
  public enum Health {
    HEALTHY("Healthy"), ILL("Ill");

    private final String camelCase;

    private Health(String desc) {
      camelCase = desc;
    }

    public String camelCase() {
      return camelCase;
    }
  }

  public enum Age {
    NEWBORN("Newborn"), YOUTH("Youth"), ADULT("Adult");

    private final String camelCase;

    private Age(String desc) {
      camelCase = desc;
    }

    public String camelCase() {
      return camelCase;
    }
  }

  public boolean endangered;
  private Health health;
  private Age age;


  public EndangeredAnimal(String name, String health, String age) {
    super(name);
    this.health = EndangeredAnimal.Health.valueOf(health.toUpperCase());
    this.age = EndangeredAnimal.Age.valueOf(age.toUpperCase());;
    endangered = true;
  }

  public String getHealth() {
    return health.toString();
  }

  public String getHealthCamelCase() {
    return health.camelCase();
  }

  public String getAge() {
    return age.toString();
  }

  public String getAgeCamelCase() {
    return age.camelCase();
  }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return  this.getName().equals(newEndangeredAnimal.getName()) &&
              this.getHealth().equals(newEndangeredAnimal.getHealth()) &&
              this.getAge().equals(newEndangeredAnimal.getAge());
    }
  }

  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, health, age, endangered) VALUES (:name, :health, :age, :endangered)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("health", this.health.toString())
        .addParameter("age", this.age)
        .addParameter("endangered", this.endangered)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<EndangeredAnimal> allEndangered() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE endangered=true";
      return con.createQuery(sql)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id";
      EndangeredAnimal endangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredanimal;
    }
  }

  public void updateHealth(String health) {
    if(!EnumUtils.isValidEnum(Health.class, health)) {
      throw new IllegalArgumentException("That is not a valid health");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET health=:health WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("health", health.toUpperCase())
        .executeUpdate();
    }
  }

  public void updateAge(String age) {
    if(!EnumUtils.isValidEnum(Age.class, age)) {
      throw new IllegalArgumentException("That is not a valid Age");
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET age=:age WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }


}
