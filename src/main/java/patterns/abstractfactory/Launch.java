package patterns.abstractfactory;

public class Launch implements SeaTransport {
    @Override
    public void swim() {
        System.out.println("Swim on launch");
    }
}
