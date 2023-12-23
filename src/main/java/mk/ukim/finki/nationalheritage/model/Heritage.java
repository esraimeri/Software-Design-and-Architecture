package mk.ukim.finki.nationalheritage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Heritage {
    public Heritage() {
    }

    public Heritage(Long id, String name) {
        this.id = id;
        this.name = name;
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
