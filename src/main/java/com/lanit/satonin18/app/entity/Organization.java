package com.lanit.satonin18.app.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "organizations")

@Data //get and set
//@ToString //can be loop
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Organization implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 2, max = 200)
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "org_type", nullable = false)
    private boolean government;

    //@NotNull //у гос Орг это поле = null, но если случайно поставить анотацию, то прост не будет правельно работать другие сущности (без показа ВалидЭксепшена, прост не будут возрващаться сущнлсти только в Spring Data JpaRepository
    @ManyToOne
    @JoinColumn(name = "id_gos_org", referencedColumnName = "id")
    private Organization government_org;

    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")//, fetch = FetchType.EAGER)
    private List<User> users;

    @org.hibernate.annotations.LazyCollection(
            org.hibernate.annotations.LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "organization")//, fetch = FetchType.EAGER)
    private List<Notification> notifications;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", IS_government?=" + government +
                ", government_org = " + government_org +
                ", users=" + users +
                ", notifications=" + notifications +
                '}';
    }
}
