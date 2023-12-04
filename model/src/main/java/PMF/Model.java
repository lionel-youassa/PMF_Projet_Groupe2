package PMF;
import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;


public class Model {
    Model(){
    }
    SerialPort[] ports = SerialPort.getCommPorts();
    View view=new View();
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
                 SerialPort selectedPort;
                 for (SerialPort port : ports) {
                    if(PORT==port.getSystemPortName()) {
                            selectedPort=port;
                        if (selectedPort.openPort()) {
                            System.out.println(selectedPort.getSystemPortName());
                            view.notificationInfo("Port série ouvert avec succès !");
                        } else {
                            view.notificationErro("Impossible d'ouvrir le port série.");
                        }
                        break;
                     }
                    }
                }






}
