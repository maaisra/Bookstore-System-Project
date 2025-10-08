package Lib.ShipmentFactory;

public class StandardShipment implements Shipment {

    @Override
    public String getInfo() {
        return "Standard Delivery";
    }

    @Override
    public double getCost() {
        return 30.0;
    }

}