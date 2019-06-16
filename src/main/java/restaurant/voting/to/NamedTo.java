package restaurant.voting.to;

public class NamedTo extends BaseTo{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public NamedTo() {
    }
}
