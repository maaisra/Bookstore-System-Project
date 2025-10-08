package Lib.StrategyPattern;
import Lib.*;
/**
 * การคิดราคาแบบปกติ (ไม่มีส่วนลด, โปรโมชั่น)
 */
public class DefaultPricingStrategy implements DiscountStrategy {
    @Override
    public double calculatePrice(CartItem item) {
        return item.getProduct().getPrice() * item.getQuantity();
    }
}
