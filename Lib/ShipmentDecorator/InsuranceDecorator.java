package Lib.ShipmentDecorator;

import Lib.ShipmentFactory.*;
import Lib.OrderProcessor.*;

public class InsuranceDecorator extends ShipmentDecorator{

    public InsuranceDecorator(Shipment wrappedShipment) {
        super(wrappedShipment);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Insurance";
    }

    @Override
    public double getCost() {
        return super.getCost() * 1.15;
    }

    
}