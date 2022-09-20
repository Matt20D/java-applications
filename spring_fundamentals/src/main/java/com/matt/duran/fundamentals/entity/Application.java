package com.matt.duran.fundamentals.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder; // https://medium.com/@vidishapal/save-data-to-h2-db-from-spring-boot-service-9a29e282b4fc
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // indicates that this is a JPA entity. This will be mapped to a tablename Application
@Table(name="application")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // changed from GenerationType.Auto
    // this generated value annotation should help with auto-incrementing from our databasae
    // one + what existed prior. but it requires more configs (not the point of this)
    @Column(name="application_id")
    private long id;

    @Column(name = "app_name", nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    private String owner;

//    public Application() {
//
//    }
//
//    public Application (String name, String owner, String description) {
//        this.name = name;
//        this.owner = owner;
//        this.description = description;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getOwner() {
//        return owner;
//    }
//
//    public void setOwner(String owner) {
//        this.owner = owner;
//    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
