package ParkingLot;

public class Car {
    private boolean compact;
    private boolean handicapped;

    public Car(boolean isCom, boolean isHandi){
        compact = isCom;
        handicapped = isHandi;
    }

    public boolean isCompatat(){
        return compact;
    }

    public boolean isHandicapped(){
        return handicapped;
    }


}
