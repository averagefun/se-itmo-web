package beans;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner")
    @ToString.Exclude
    private List<Result> usersResults;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return username != null && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
