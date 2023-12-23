package mk.ukim.finki.nationalheritage.model;

import jakarta.persistence.*;
import lombok.Data;

@Table (name="heritages") // TODO ASSIGN DATA SOURCE!!
@Entity
@Data
public class Heritage {
    public Heritage() {
    }

    public Heritage(Long id, HeritageType heritageType, String name, String englishName, String description, String phoneNumber, String website, String location) {
        this.id = id;
        this.heritageType = heritageType;
        this.name = name;
        this.englishName = englishName;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.location = location;
        lat = Double.parseDouble(location.split(" ")[0]);
        lon = Double.parseDouble(location.split(" ")[1]);
    }

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private HeritageType heritageType;

    private String name;

    private String englishName;

    private String description;

    private String phoneNumber;

    private String website;

    private String location;
    private Double lat;
    private Double lon;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public HeritageType getHeritageType() {
        return heritageType;
    }

    public void setHeritageType(HeritageType heritageType) {
        this.heritageType = heritageType;
    }
}
