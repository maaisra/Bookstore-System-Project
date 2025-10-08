package Lib.ShipmentFactory;

public class ShipmentFactory {
    public Shipment creatShipment(String type){
        if ("STANDARD".equalsIgnoreCase(type)) {
            return new StandardShipment();
        } else if ("EXPRESS".equalsIgnoreCase(type)) {
            return new ExpressShipment();
        }
        throw new IllegalArgumentException("Unknown Type");
    }
}