package planBidudHotel.entities;

public class Relatives {
    private int requestID;
    private int relativeID;
    private RelativeType relativeType;

    public Relatives(int requestID, int relativeID, RelativeType relativeType) {
        this.requestID = requestID;
        this.relativeID = relativeID;
        this.relativeType = relativeType;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getRelativeID() {
        return relativeID;
    }

    public void setRelativeID(int relativeID) {
        this.relativeID = relativeID;
    }

    public RelativeType getRelativeType() {
        return relativeType;
    }

    public void setRelativeType(RelativeType relativeType) {
        this.relativeType = relativeType;
    }

    @Override
    public String toString() {
        return "Relatives{" +
                "requestID=" + requestID +
                ", relativeID=" + relativeID +
                ", relativeType=" + relativeType +
                '}';
    }
}
