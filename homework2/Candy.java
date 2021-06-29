package homework2;

public class Candy {
    String name;
    String flavour;
    String weight;
    String price;

    public Candy(String name, String flavour, String weight, String price) {
        this.name = name;
        this.flavour = flavour;
        this.weight = weight;
        this.price = price;
    }

    public Candy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
