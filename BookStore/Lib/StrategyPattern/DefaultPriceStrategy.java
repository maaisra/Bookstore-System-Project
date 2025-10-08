package Lib.StrategyPattern;

import Lib.OrderProcessor.*;

public class DefaultPriceStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(Order order) {
        return order.getBook().price() * order.getQuantity();
    }
    
}
