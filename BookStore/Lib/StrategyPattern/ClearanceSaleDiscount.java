package Lib.StrategyPattern;
import Lib.DataModels.*;
import Lib.OrderProcessor.*;

public class ClearanceSaleDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(Order order) {
        return order.getTotalPrice() * 0.5;
    }
}