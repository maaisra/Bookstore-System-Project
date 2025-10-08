package Lib.ShipmentDecorator;

import Lib.ShipmentFactory.*;
import Lib.OrderProcessor.*;

public class InsuranceDecorator extends ShipmentDecorator{

    private final Cart order;

    public InsuranceDecorator(Shipment wrappedShipment, Cart order) {
        super(wrappedShipment);
        this.order = order;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Insurance";
    }

    @Override
    public double getCost() {
        return super.getCost() + ( order.getTotalPrice() * 0.15);
    }
    
}