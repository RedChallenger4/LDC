package app.theducksneezes.com.ldc;

import android.text.Editable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable{
    private String name;
    private String desc;
    private Long date;
    private Integer startMin;
    private Integer startHour;
    private Integer endMin;
    private Integer endHour;

    Event () {
        this.name = null;
        this.desc = null;
        this.date = null;
        this.startMin = 0;
        this.startHour = 0;
        this.endMin = 0;
        this.endHour = 0;
    }

    Event (Editable name, Editable desc, long date, int startMin, int startHour, int endMin, int endHour) {
        this.name = name.toString();
        this.desc = desc.toString();
        this.date = date;
        this.startMin = startMin;
        if (startHour > 12){
            this.startHour = startHour - 12;
        } else {
            this.startHour = startHour;
        }
        this.endMin = endMin;
        if (endHour > 12){
            this.endHour = endHour - 12;
        } else {
            this.endHour = endHour;
        }
    }

    @Override
    public String toString() {
        if (name != null && desc != null && date != null && startMin != null && startHour != null && endMin != null && endHour!= null) {
            return name + "\n" + dateString() +
                    "\nStart: " + startHour.toString() + ":" + startMinString() +
                    "\nEnd: " + endHour.toString() + ":" + endMinString();
        } else {
            return null;
        }
    }

    public String dateString() {
        SimpleDateFormat fmt = new SimpleDateFormat("MMM d, yyyy");

        if (date != null) {
                Date d = new Date(this.date);
                return fmt.format(d);
            } else {
                return null;
            }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getStartMin() {return startMin; }

    public String startMinString() {
        if (startMin < 10) {
            return "0" + startMin.toString();
        } else {
            return startMin.toString();
        }
    }

    public void setStartMin(int startMin) {
        this.startMin = startMin;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public String endMinString() {
        if (endMin < 10) {
            return "0" + endMin.toString();
        } else {
            return endMin.toString();
        }
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
