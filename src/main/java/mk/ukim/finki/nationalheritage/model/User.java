package mk.ukim.finki.nationalheritage.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Table (name="users") // TODO ASSIGN DATA SOURCE!!
@Entity
@Data

public class User {

    public User() {
    }

    public User(String name, String email, String password, List<Heritage> visited, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.visited = visited;
        this.birthday = birthday;
        generateRandomCoordinates();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birthday;

    private String name;

    private String email;

    private String password;

    private Double lat;
    private Double lon;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Heritage> visited;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Heritage> getVisited() {
        return visited;
    }

    public void setVisited(List<Heritage> visited) {
        this.visited = visited;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    private void generateRandomCoordinates() {
        double minLat = 40.853660;
        double maxLat = 42.358662;
        double minLon = 20.463663;
        double maxLon = 22.720202;

        Random random = new Random();


        this.lat = minLat + (maxLat - minLat) * random.nextDouble();
        this.lon = minLon + (maxLon - minLon) * random.nextDouble();
    }
}
