package Lib.StrategyPattern;

public class StandardShipmentFactory extends ShipmentFactory {
    @Override
    public Shipment createShipment() {
        return new StandardShipment();
    }
}
