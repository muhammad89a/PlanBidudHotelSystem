package planBidudHotel.entities;

public class Relatives {
    private RelativeType relativeType;

    public Relatives(RelativeType relativeType) {
        this.relativeType = relativeType;
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
                "relativeType=" + relativeType +
                '}';
    }
}
