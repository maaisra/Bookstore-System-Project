package Lib.StrategyPattern;

public class StandardShipment implements Shipment {
    @Override
    public String ship() {
        return "Shipping by Standard";
    }
}
