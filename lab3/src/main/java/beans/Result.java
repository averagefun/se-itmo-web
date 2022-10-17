package beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "x", nullable = false)
    private int x;
    @Column(name = "y", nullable = false)
    private double y;
    @Column(name = "r", nullable = false)
    private double r;
    @Column(name = "isSuccess", nullable = false)
    private boolean isHit;

    public Result(int x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = x > 1; // todo
    }

    public String getStringSuccess() {
        return isHit ? "Попадание" : "Промах";
    }

    public String getClassSuccess() {
        return isHit ? "hit" : "miss";
    }
}