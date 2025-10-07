package Lib.ShipmentFactory;

public class StandardShipment implements Shipment {
    @Override
    public String getInfo() {
        return "Shipping by Standard";
    }

    @Override
    public double getCost() {
        return 20.0; // กำหนดค่าเป็นตัวอย่าง เช่น ค่าส่งแบบ Standard
    }
}
