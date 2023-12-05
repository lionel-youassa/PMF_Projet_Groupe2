package PMF;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        View view = new View();
        Controller controller =new Controller();
        view.addObserver(controller);

        controller.demarrer();


    }
}