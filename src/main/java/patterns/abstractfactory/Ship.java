package patterns.abstractfactory;

public class Ship implements SeaTransport {
    @Override
    public void swim() {
        System.out.println("Swim on ship");
    }
}
