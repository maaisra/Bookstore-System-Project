package Lib.StrategyPattern;
import Lib.DataModels.Order;
public interface DiscountStrategy {
    double applyDiscount(Order order);
}
