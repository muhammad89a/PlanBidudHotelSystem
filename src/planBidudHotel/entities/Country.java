package planBidudHotel.entities;

public class Country {
    private int id;
    private String name;
    private boolean needIsolation;

    public Country(int id, String name, boolean needIsolation) {
        this.id = id;
        this.name = name;
        this.needIsolation = needIsolation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeedIsolation() {
        return needIsolation;
    }

    public void setNeedIsolation(boolean needIsolation) {
        this.needIsolation = needIsolation;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", needIsolation=" + needIsolation +
                '}';
    }
}
