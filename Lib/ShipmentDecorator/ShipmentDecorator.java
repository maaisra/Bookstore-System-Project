package Lib.ShipmentDecorator;

import Lib.ShipmentFactory.Shipment;

public abstract class ShipmentDecorator implements Shipment{
    protected Shipment wrappedShipment;
}
