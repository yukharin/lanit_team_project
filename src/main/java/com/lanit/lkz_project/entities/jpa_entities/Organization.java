package com.lanit.lkz_project.entities.jpa_entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "organizations")
@EqualsAndHashCode(exclude = {"id", "notifications", "users"})
@ToString(exclude = {"notifications", "users"})
@NoArgsConstructor
@JsonIgnoreProperties({"notifications", "users"})
public class Organization implements Serializable {

    private static int counter = 0;

    {
        counter++;
        System.err.println("Organizations: " + counter);
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 3, max = 200)
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "org_type", nullable = false)
    private boolean government;

    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization government_org;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private transient List<Notification> notifications;

    @OneToMany(mappedBy = "organization")
    private transient List<User> users;

}
