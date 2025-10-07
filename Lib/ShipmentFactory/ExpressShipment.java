package Lib.ShipmentFactory;

public class ExpressShipment implements Shipment {
    @Override
    public String getInfo() {
        return "Shipping by Express";
    }

    @Override
    public double getCost() {
        return 50.0; // กำหนดค่าเป็นตัวอย่าง เช่น ค่าส่งแบบ Express
    }
}
