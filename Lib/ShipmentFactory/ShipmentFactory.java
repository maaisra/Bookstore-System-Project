package Lib.StrategyPattern;

import Lib.ShipmentFactory.Shipment;

public abstract class ShipmentFactory {
    public abstract Shipment createShipment(); 
}