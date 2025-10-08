package Lib.ShipmentFactory;

public class ExpressShipment implements Shipment {

    @Override
    public String getInfo() {
        return "Express Delivery";
    }

    @Override
    public double getCost() {
        return 50.0;
    }

}