package beans;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TimeKeeper implements Serializable {
    private String time;

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
    }

    public TimeKeeper() {
        this.time = formatDate(new Date());
    }

    public void updateTime() {
        time = formatDate(new Date());
    }
}
