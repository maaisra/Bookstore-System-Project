package Lib.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

import Lib.OrderProcessor.Order;

public class OrderProcessor {
    private final List<OrderObserver> observers = new ArrayList<>();

    public void register(OrderObserver observer){
        observers.add(observer);
    }

    public void unregister(OrderObserver observer){
        observers.remove(observer);
    }

    public void notify(Order order){
        for (OrderObserver orderObserver : observers) {
            orderObserver.update(order);
        }
    }
    public void processOrder(Order order){
        System.out.println("\n Processing order " + order.getBook().ISBN() + ". . .");
        System.out.println("Order processed successfully.");

        notify(order);
    }
}
