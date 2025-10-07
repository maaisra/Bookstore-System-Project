package Lib.StrategyPattern;

public class ExpressShipment implements Shipment {
    @Override
    public String ship() {
        return "Shipping by Express";
    }
}
