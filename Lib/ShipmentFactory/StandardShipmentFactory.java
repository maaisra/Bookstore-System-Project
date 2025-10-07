package Lib.StrategyPattern;

import Lib.ShipmentFactory.Shipment;
import Lib.ShipmentFactory.StandardShipment;

public class StandardShipmentFactory extends ShipmentFactory {
    @Override
    public Shipment createShipment() {
        return new StandardShipment();
    }
}
