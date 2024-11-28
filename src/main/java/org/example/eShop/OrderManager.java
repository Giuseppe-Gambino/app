package org.example.eShop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderManager {

    private static List<Order> totOrdini = new ArrayList<>();
    private static List<Customer> totCustomer = new ArrayList<Customer>();

//    inserire ordini in lista
    public static void addOrder(Order order) {
        totOrdini.add(order);
    }

    public static List<Order> getTotOrdini() {
        return totOrdini;
    }

//    inserire clienti in lista

    public static void addCustomer(Customer customer) {
        totCustomer.add(customer);
    }

    public static List<Customer> getTotCustomer() {
        return totCustomer;
    }

    public HashMap<Customer, List<Order>> groupOrdersByCustomer(List<Order> orders, Customer customer) {
        // Filtra gli ordini del cliente specificato
        List<Order> filteredOrders = orders.stream()
                .filter(order -> order.getCustomer() == customer)
                .collect(Collectors.toList());

        // Inserisce gli ordini filtrati in una HashMap
        HashMap<Customer, List<Order>> customerOrdersMap = new HashMap<>();
        customerOrdersMap.put(customer, filteredOrders);

        return customerOrdersMap;
    }

    public List<Order> filterOrdersByTierAndDate(List<Order> orders, int tier, LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(order -> order.getCustomer().getTier() == tier &&
                        (order.getOrderDate().isEqual(startDate) || order.getOrderDate().isAfter(startDate)) &&
                        (order.getOrderDate().isEqual(endDate) || order.getOrderDate().isBefore(endDate)))
                .collect(Collectors.toList());
    }

    public HashMap<String, Double> getSellByCustomer(List<Order> orders) {
        HashMap<String, Double> customerSellMap = new HashMap<>();

        // Itera su tutti i clienti
        for (Customer customer : totCustomer) {
            // Filtra gli ordini associati al cliente specificato
            List<Order> filteredOrders = orders.stream()
                    .filter(order -> order.getCustomer() == customer)
                    .toList();

            // Calcola il totale dei prezzi dei prodotti per il cliente
            Double totalSell = filteredOrders.stream()
                    .flatMap(order -> order.getProducts().stream()) // Ottieni tutti i prodotti dagli ordini
                    .mapToDouble(Product::getPrice) // Estrai i prezzi dei prodotti
                    .sum(); // Somma i prezzi

            // Inserisci il totale nella mappa
            customerSellMap.put(customer.getName() , totalSell);
        }

        return customerSellMap;
    }

    public HashMap<String, Double> getSellByCustomerMedia(List<Order> orders) {
        HashMap<String, Double> customerSellMap = new HashMap<>();

        // Itera su tutti i clienti
        for (Customer customer : totCustomer) {
            // Filtra gli ordini associati al cliente specificato
            List<Order> filteredOrders = orders.stream()
                    .filter(order -> order.getCustomer() == customer)
                    .toList();

            // Ottieni tutti i prodotti degli ordini del cliente
            List<Product> allProducts = filteredOrders.stream()
                    .flatMap(order -> order.getProducts().stream()) // Ottieni tutti i prodotti dagli ordini
                    .toList();

            // Calcola il totale dei prezzi dei prodotti
            double totalSell = allProducts.stream()
                    .mapToDouble(Product::getPrice) // Estrai i prezzi dei prodotti
                    .sum(); // Somma i prezzi

            // Calcola il prezzo medio (evita divisione per zero)
            double averageSell = allProducts.isEmpty() ? 0.0 : totalSell / allProducts.size();


            // Inserisci il totale nella mappa
            customerSellMap.put(customer.getName() , averageSell);
        }

        return customerSellMap;
    }





}
