package beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "x", nullable = false)
    private int x;
    @Column(name = "y", nullable = false)
    private double y;
    @Column(name = "r", nullable = false)
    private double r;
    @Column(name = "hit", nullable = false)
    private boolean hit;

    public Result(Result sourceResult) {
        this.id = sourceResult.id;
        this.x = sourceResult.getX();
        this.y = sourceResult.getY();
        this.r = sourceResult.getR();
        this.hit = checkHit();
    }

    private boolean checkHit() {
        boolean circle = (x < 0) && (y < 0) && (x * x + y * y <= (r / 2) * (r / 2));
        boolean triangle = (x > 0) && (y > 0) && (y <= r - 2 * x);
        boolean rectangle = (x > 0) && (y < 0) && (x <= r) && (y >= -r / 2);
        return circle || triangle || rectangle;
    }

    public String getStringSuccess() {
        return hit ? "Попадание" : "Промах";
    }

    public String getClassSuccess() {
        return hit ? "hit" : "miss";
    }
}