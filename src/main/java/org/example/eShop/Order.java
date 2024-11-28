package org.example.eShop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Order {

    private LocalDate deliveryDate;
    private String status;
    private LocalDate orderDate;
    private List<Product> products = new ArrayList<>();
    private Customer customer;


    public Order(Customer customer) {

        this.status = "Attivo";
        this.orderDate = LocalDate.now();
        this.customer = customer;

        OrderManager.addOrder(this);
    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Order{\n" +
                "  orderDate=" + orderDate + ",\n" +
                "  deliveryDate=" + deliveryDate + ",\n" +
                "  products=" + products + ",\n" +
                "  customer=" + customer + "\n" +
                '}';
    }


    public void getByCategory(String categoria) {

        List<Product> filterList = products.stream()
                .filter(product -> product.getCategory().equals(categoria))
                .toList();

        System.out.println(filterList);
    }

    public void getByCategory(String categoria, int price) {

        List<Product> filterList = products.stream()
                .filter(product -> product.getCategory().equals(categoria) && product.getPrice() > price )
                .toList();

        System.out.println(filterList);
    }

    public void getByCategory10(String categoria) {

        List<Product> filterList = products.stream()
                .filter(product -> product.getCategory().equals(categoria))
                .peek(product -> product.setPrice(product.getPrice()  * 0.90) )
                .toList();

        System.out.println(filterList);
    }

    public void getByPriceLow() {

        List<Product> filterList = products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();

        System.out.println(filterList);
    }

    public void getByPriceHight() {

        List<Product> filterList = products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .toList();

        System.out.println(filterList);
    }

}
