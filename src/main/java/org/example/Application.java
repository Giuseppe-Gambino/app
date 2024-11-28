package org.example;

import org.example.eShop.Customer;
import org.example.eShop.Order;
import org.example.eShop.OrderManager;
import org.example.eShop.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        Customer cl1 = new Customer(2,"giuseppe", 2L);
        Customer cl2 = new Customer(1,"pino", 1L);

        Product p1 = new Product(3L,"banana","alimenti",50.0);
        Product p2 = new Product(2L,"mouse","informatica",77.95);
        Product p3 = new Product(1L,"cassa","informatica",20.0);
        Product p4 = new Product(4L, "apple", "alimenti", 30.0);
        Product p5 = new Product(5L, "keyboard", "informatica", 55.5);
        Product p6 = new Product(6L, "printer", "informatica", 120.0);
        Product p7 = new Product(7L, "notebook", "books", 15.0);
        Product p8 = new Product(8L, "dictionary", "books", 45.0);
        Product p9 = new Product(9L, "novel", "books", 25.0);
        Product p10 = new Product(10L, "juice", "alimenti", 10.0);
        Product p11 = new Product(11L, "tablet", "informatica", 199.99);
        Product p12 = new Product(12L, "science textbook", "books", 75.0);

        Order orderCl2 = new Order(cl2);
        Order orderCl2P2 = new Order(cl2);


        Order orderCl1 = new Order(cl1);

        orderCl1.addProduct(p1);
        orderCl1.addProduct(p2);
        orderCl1.addProduct(p3);
        orderCl1.addProduct(p4);
        orderCl1.addProduct(p5);
        orderCl1.addProduct(p6);
        orderCl1.addProduct(p12);

        orderCl2.addProduct(p1);
        orderCl2.addProduct(p2);
        orderCl2.addProduct(p3);
        orderCl2.addProduct(p4);
        orderCl2.addProduct(p5);
        orderCl2.addProduct(p6);
        orderCl2.addProduct(p12);

        orderCl2P2.addProduct(p1);
        orderCl2P2.addProduct(p2);
        orderCl2P2.addProduct(p3);
        orderCl2P2.addProduct(p4);



        List<Order> totOrdini = OrderManager.getTotOrdini();
//        System.out.println("Tutti gli ordini: " + totOrdini);

        OrderManager orderManager = new OrderManager();

        // Filtro ordini per cliente
        HashMap<Customer, List<Order>> ordiniClientex = orderManager.groupOrdersByCustomer(totOrdini, cl2);
//        System.out.println(ordiniClientex);

        // Filtro per tier e data
        int tier = 1;
        LocalDate startDate = LocalDate.of(2024, 12, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 26);
        List<Order> filterOrder = orderManager.filterOrdersByTierAndDate(totOrdini, tier, startDate, endDate);
//        System.out.println("Filtro per tier e data: " + filterOrder);

        System.out.println(orderManager.getSellByCustomer(totOrdini));

//       orderCl1.getByPriceHight();

        System.out.println(orderManager.getSellByCustomerMedia(totOrdini));

    }
}
