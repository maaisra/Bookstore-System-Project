package Lib.StrategyPattern;
import Lib.OrderProcessor.*;

public class BulkDiscount implements DiscountStrategy {

    private final int minQuan;
    private final double Discountpercent;

    public BulkDiscount(int minQuan, double Discountpercent){
        this.minQuan = minQuan;
        this.Discountpercent = Discountpercent;
    }

    @Override
    public double applyDiscount(Order order) {
        double originPrice = order.getBook().price() * order.getQuantity();
        if (order.getQuantity() >= minQuan) {
            return originPrice * (1.0 - Discountpercent);
        }
        return originPrice;
    }
}