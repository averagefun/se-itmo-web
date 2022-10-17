package beans;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class TimeKeeper implements Serializable {
    private final static SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private String time;

    public TimeKeeper() {
        this.time = sdfDate.format(new Date());
    }

    public void updateTime() {
        time = sdfDate.format(new Date());
    }
}
