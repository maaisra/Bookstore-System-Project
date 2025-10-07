package Lib.StrategyPattern;

import Lib.ShipmentFactory.ExpressShipment;
import Lib.ShipmentFactory.Shipment;

public class ExpressShipmentFactory extends ShipmentFactory {
    @Override
    public Shipment createShipment() {
        return new ExpressShipment();
    }
}
