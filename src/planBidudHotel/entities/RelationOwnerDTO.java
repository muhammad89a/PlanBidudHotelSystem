package planBidudHotel.entities;

import java.util.ArrayList;

public class RelationOwnerDTO {
    private Citizen owner;
    private Request request;
    private ArrayList<Relatives> relatives;

    public RelationOwnerDTO(Citizen owner, Request request, ArrayList<Relatives> relatives) {
        this.owner = owner;
        this.request = request;
        this.relatives = relatives;
    }

    public Citizen getOwner() {
        return owner;
    }

    public void setOwner(Citizen owner) {
        this.owner = owner;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ArrayList<Relatives> getRelatives() {
        return relatives;
    }

    public void setRelatives(ArrayList<Relatives> relatives) {
        this.relatives = relatives;
    }

    @Override
    public String toString() {
        return "RelationOwnerDTO{" +
                "owner=" + owner +
                ", request=" + request +
                ", relatives=" + relatives +
                '}';
    }
}
