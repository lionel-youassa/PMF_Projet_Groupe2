package PMF;
import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;


public class Model {
    SerialPort[] ports = SerialPort.getCommPorts();// liste des port COM
    SerialPort selectedPort;//port de connection a la carte arduino

    private static Model model;
    Model(){
    }
        public static Model getModel(){
            if(model== null){
                model=new Model();
            }
            return model;

        }

        public  String[] list_ports(){
            String[] name_ports= new String[ports.length];
            int i=0;

            //System.out.println("Ports série disponibles:");
            for (SerialPort port : ports) {

                name_ports[i]=port.getSystemPortName();
                //System.out.println(name_ports[i]);
                i++;
            }
            return name_ports;

            }
             public void connect_ports(String PORT){


                 //System.out.println(PORT);
                 for (SerialPort port : ports) {

                    if(Objects.equals(PORT, port.getSystemPortName())) {
                            selectedPort=port;
                        System.out.println(selectedPort.getSystemPortName());
                        break;

                     }
                    }
                }
                public void close_connection_port() {
                    if (selectedPort.isOpen()) {
                        selectedPort.closePort();
                        System.out.println("Connexion série fermée.");
                    }
                }
                public void open_connection_port(){
                    if (selectedPort.openPort()) {
                        System.out.println(selectedPort.getSystemPortName());
                        View.getView().notificationInfo("Port série ouvert avec succès !");

                    } else {
                        View.getView().notificationErro("Impossible d'ouvrir le port série.");

                    }
                }

                public void send_info(int T){

                    // Paramétrage  du débit de transmission et ouverture de la connexion série
                    if(selectedPort==null){
                        View.getView().alerte("Veuillez selectioner un port");

                    }else {
                    selectedPort.setBaudRate(9600); // débit de transmission

                    open_connection_port();

                    // Obtenir le flux de sortie pour envoyer des données à l'Arduino
                    OutputStream outputStream = selectedPort.getOutputStream();

                    // Insérer les informations dans la carte Arduino
                    String dataToSend =String.valueOf(T); // Les informations à envoyer
                    try {
                        outputStream.write(dataToSend.getBytes());
                        outputStream.flush();
                        View.getView().notificationInfo("Informations insérées avec succès.");
                        // Fermer la connexion série
                        selectedPort.closePort();
                    } catch (Exception e) {
                        View.getView().notificationErro("Erreur lors de l'insertion des informations : " );
                        System.out.println(e.getMessage());
                        // Fermer la connexion série
                        close_connection_port();
                    }




                }
        }

        public  double generateRandomT_embiante(double min, double max) {


                // Créer une instance de Random
                Random random = new Random();
                // Générer un entier aléatoire entre min (inclus) et max (exclus)

            return random.nextDouble(max - min) + min;

        }

        public  float generateRandomT_frigo(float min, float max) {

                // Créer une instance de Random
                Random random = new Random();
                // Générer un entier aléatoire entre min (inclus) et max (exclus)

            return random.nextFloat(max - min) + min;

        }

        public  float generateRandomX(float min, float max){


                // Créer une instance de Random
                Random random = new Random();
                // Générer un entier aléatoire entre min (inclus) et max (exclus)

            float result = random.nextFloat(max - min) + min;
            System.out.println(result);
            return result;

        }

    public String readData() {
        //open_connection_port();
        if (selectedPort.isOpen()) {
            byte[] buffer = new byte[1024];
            int numRead = selectedPort.readBytes(buffer, buffer.length);
           // close_connection_port();
           // return new String(buffer, 0, numRead);


        }
       // return null;
        String v = "20%";
        return v;
    }






}
