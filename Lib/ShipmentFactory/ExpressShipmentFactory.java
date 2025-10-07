package Lib.StrategyPattern;

public class ExpressShipmentFactory extends ShipmentFactory {
    @Override
    public Shipment createShipment() {
        return new ExpressShipment();
    }
}
