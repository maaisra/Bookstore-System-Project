package Lib.ShipmentDecorator;

import Lib.DataModels.*;
import Lib.ShipmentFactory.Shipment;

public class InsuranceDecorator extends ShipmentDecorator{

    private final Customer order;

    public InsuranceDecorator(Shipment wrappedShipment, Customer order) {
        super(wrappedShipment);
        this.order = order;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Insurance";
    }

    @Override
    public double getCost() {
        return super.getCost() + (order.getTotalPrice() * 0.15);
    }
    
}
