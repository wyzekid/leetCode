package patterns.abstractfactory;

public class Truck implements WheelTransport {
    @Override
    public void drive() {
        System.out.println("Drive a truck");
    }
}
