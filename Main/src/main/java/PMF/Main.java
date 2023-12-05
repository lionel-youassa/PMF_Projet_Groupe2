package PMF;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100);
        Controller controller =new Controller();
        controller.addObserver(controller);
       // controller.inialisation_Select_COM()

        controller.demarrer();

    }
}