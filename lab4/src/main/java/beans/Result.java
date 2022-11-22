package beans;

import lombok.*;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "result")
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "x", nullable = false)
    private Double x;
    @Column(name = "y", nullable = false)
    private Double y;
    @Column(name = "r", nullable = false)
    private Double r;
    @Column(name = "hit", nullable = false)
    private Boolean hit;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "username")
    private User owner;

    public Result(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = checkHit();
    }

    public JsonObject toJSONObject() {
        return Json.createObjectBuilder()
                .add("x", x)
                .add("y", y)
                .add("r", r)
                .add("result", hit)
                .build();
    }

    private boolean checkHit() {
        boolean circle = (x <= 0) && (y >= 0) && (x * x + y * y <= (r / 2) * (r / 2));
        boolean triangle = (x >= 0) && (y >= 0) && (y <= r - 2 * x);
        boolean rectangle = (x >= 0) && (y <= 0) && (x <= r) && (y >= -r / 2);
        return circle || triangle || rectangle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Result result = (Result) o;
        return id != null && Objects.equals(id, result.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}