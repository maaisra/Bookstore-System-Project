package Lib.StrategyPattern;

import Lib.DataModels.*;

public interface DiscountStrategy {
    double applyDiscount(Order order);
}
