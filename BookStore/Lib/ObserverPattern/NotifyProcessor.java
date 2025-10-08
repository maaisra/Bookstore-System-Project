package Lib.ObserverPattern;

import Lib.OrderProcessor.Order;

public class NotifyProcessor implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println("Confirmation Notify sent to [User]");
    }
    
}
