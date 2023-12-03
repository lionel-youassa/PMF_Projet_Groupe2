package PMF;


import java.util.Enumeration;
import gnu.io.CommPortIdentifier;

public class Model {
Model(){

}
    public String[] arduinoport() {
        String[] arduinoPorts = new String[0];
        int i=0;
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

                    arduinoPorts[i]=portId.getName();
                    i++;

            }
        }

        return arduinoPorts;
    }

    private static boolean isArduino(CommPortIdentifier portId) {
        return true;
    }
}
