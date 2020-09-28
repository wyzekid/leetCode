package patterns.abstractfactory;

public class BigTransportFactory implements TransportFactory {

    @Override
    public SeaTransport createSeaTransport() {
        return new Ship();
    }

    @Override
    public WheelTransport createWheelTransport() {
        return new Truck();
    }
}
