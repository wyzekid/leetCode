package patterns.abstractfactory;

/**
 * Абстрактная фабрика
 */
public interface TransportFactory {

    SeaTransport createSeaTransport();
    WheelTransport createWheelTransport();
}
