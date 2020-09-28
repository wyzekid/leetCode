package patterns.abstractfactory;

public class Car implements WheelTransport {
    @Override
    public void drive() {
        System.out.println("Drive a car");
    }
}
