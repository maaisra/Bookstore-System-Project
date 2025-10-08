package Lib.StrategyPattern;

import Lib.*;

public interface DiscountStrategy {
    double applyDiscount(Order order);
}