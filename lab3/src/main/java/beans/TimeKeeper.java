package beans;

import annotations.MyBean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@MyBean
public class TimeKeeper implements Serializable {
    private String time;

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date);
    }

    public TimeKeeper() {
        this.time = formatDate(new Date());
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void updateTime() {
        time = formatDate(new Date());
    }
}
