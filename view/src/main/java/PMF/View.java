package PMF;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;
import java.time.Instant;
import javax.swing.JOptionPane;

public class View extends Observable {


     JFrame frame =new JFrame("Manager_PMF");
    JFrame frame2 =new JFrame("PMF_Alertes");
    JLabel label1 = new JLabel("Temperature de consigne:");
    JLabel label2 = new JLabel("Choix du port de connection arduino");
    JLabel label3 = new JLabel("Consulter vos alertes ici ");

     JPanel panel1= new JPanel();
    JPanel panel2= new JPanel();
    JTextPane textPane= new JTextPane();
    JButton   button1 =new JButton("valider");
    JButton   button2 =new JButton("Alertes");

    String[] options={"COM1","COM2","COM3","COM4","COM5"};
    JComboBox<String> select =new JComboBox<>(options);


    // Création du panneau de visualisation du graphique
     ChartPanel chartPanel;
    View() {

    }

    public void create_Graph(String T) {

        // Création de la variable contenant les données pour la courbe
        XYSeries series = new XYSeries("Courbe");

        while (T != "0") {
            Instant instant = Instant.now(); // Temps instantané actuel

            long timeInSeconds = instant.toEpochMilli() / 1000;

            // ajout des données pour la courbe
            series.add(timeInSeconds, Integer.parseInt(T));
        }

            // Création du dataset avec la série de données
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            // Création du graphique avec le dataset
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Courbe", // Titre du graphique
                    "X", // Titre de l'axe des abscisses
                    "Y", // Titre de l'axe des ordonnées
                    dataset, // Dataset contenant les données
                    PlotOrientation.VERTICAL, // Orientation du graphique
                    true, // Légende visible
                    true, // Infobulles activées
                    false // URLs cliquables
            );
        chartPanel = new ChartPanel(chart);
        }

        // interface d' Administration du frigo
        public void IHM(){
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(2,2));
            frame.setSize(500, 400);
            panel1.add(label1);
            panel1.add(textPane);
            panel1.add(label2);
            panel1.add(select);
            panel1.add(button1);

            panel2.add(chartPanel);
            frame.add(panel1);
            frame.add(panel2);
            frame.add(button2);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        }

        // interface des alertes
        public void IHM_alertes(String message){
            frame.setVisible(false);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(new GridLayout(6,1));
            frame2.setSize(500, 400);
            frame2.add(label3);
            alerte(message);
            frame2.setLocationRelativeTo(null);
            frame2.pack();
            frame2.setVisible(true);

        }

        public void alerte(String message){
            JOptionPane.showMessageDialog(frame2, message,
                    "Avertissement", JOptionPane.WARNING_MESSAGE);

        }



    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JComboBox<String> getSelect() {
        return select;
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}
