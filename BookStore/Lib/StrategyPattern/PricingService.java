package Lib.StrategyPattern;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import Lib.DataModels.*;
import Lib.OrderProcessor.*;

public class PricingService {
    // Rep
    Dictionary<String, DiscountStrategy> pricingservice;

    public PricingService(){
        pricingservice = new Hashtable<>();
    }

    public void addStrategy(String sku, DiscountStrategy strategy){
        pricingservice.put(sku, strategy);
    }

    public double calculateBookPrice(Order order){
        Enumeration<String> k = pricingservice.keys();
        while (k.hasMoreElements()) {
            String key = k.nextElement();
            if (key == order.getBook().ISBN()) {
                return pricingservice.get(key).applyDiscount(order);
            } else return order.getBook().price() * order.getQuantity();
        }
        return 0;
    }
}