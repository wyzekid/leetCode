package patterns.abstractfactory;

public class SmallTransportFactory implements TransportFactory {

    @Override
    public SeaTransport createSeaTransport() {
        return new Launch();
    }

    @Override
    public WheelTransport createWheelTransport() {
        return new Car();
    }
}
