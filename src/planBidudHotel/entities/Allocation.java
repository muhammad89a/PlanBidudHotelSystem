package planBidudHotel.entities;

import java.sql.Date;

public class Allocation {

    private Date fromDate;
    private Date toDate;

    public Allocation(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
