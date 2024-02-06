package sit.int204.classicmodelsservice.services;

import sit.int204.classicmodelsservice.entities.Order;

public class Testing {
    public static void main(String[] args) {
        Order o = new Order();
        System.out.println(o.getClass().getSimpleName());
    }
}
