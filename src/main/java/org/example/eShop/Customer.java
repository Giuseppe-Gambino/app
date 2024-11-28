package org.example.eShop;

public class Customer {


    private Long id;
    private String name;
    private Integer tier;

    public Customer(Integer tier, String name, Long id) {
        this.tier = tier;
        this.name = name;
        this.id = id;

        OrderManager.addCustomer(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }

}
