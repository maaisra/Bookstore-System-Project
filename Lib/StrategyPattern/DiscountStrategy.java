package Lib.StrategyPattern;

import Lib.OrderProcessor.*;

public interface DiscountStrategy {
    double applyDiscount(Order order);
}