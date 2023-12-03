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

    JLabel label4=new JLabel("Evolution de la temperature en fonction de t(S)");

    JPanel panel_label1=new JPanel();
    JPanel panel_label2=new JPanel();
    JPanel panel_label3=new JPanel();
    JPanel panel_label4=new JPanel();
     JPanel panel_input= new JPanel();
     JPanel panel_select=new JPanel();
     JPanel panel_boutton1= new JPanel();

    JPanel panel_courbe=new JPanel();

    JPanel panelboutton_frame2=new JPanel();
    JPanel panel2_frame2=new JPanel();

    JPanel panel_boutton0= new JPanel();
    JTextPane textPane= new JTextPane();
    JButton   button1 =new JButton("valider");
    JButton   button2 =new JButton("Alertes");

    JButton   button3 =new JButton("Retour au menu");

    String[] options={"COM1","COM2","COM3","COM4","COM5"};
    JComboBox<String> select =new JComboBox<>(options);


    // Création du panneau de visualisation du graphique
     ChartPanel chartPanel;
    View() {

    }

    public void create_Graph(String T) {

        // Création de la variable contenant les données pour la courbe
        XYSeries series = new XYSeries("Courbe");

       // while (T != "0") {
        for (int i=10 ; i!=0;i--) { // il faut en lever seulement pour le test
            T=String.valueOf(i);
            Instant instant = Instant.now(); // Temps instantané actuel

            long timeInSeconds = instant.toEpochMilli() / 1000;

            // ajout des données pour la courbe
            series.add(timeInSeconds, Integer.parseInt(T));
        }
        //}

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

            textPane.setPreferredSize(new Dimension(100,20));
            select.setPreferredSize(new Dimension(100,20));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(8,1));
            //frame.setSize(300, 200);
            panel_label4.add(label4);
            panel_label1.add(label1);
            panel_label2.add(label2);
            panel_label4.add(label4);
            panel_boutton0.add(button2);
            panel_input.add(textPane);
            panel_select.add(select);
            panel_boutton1.add(button1);
            panel_courbe.setSize(new Dimension(400,200));
            panel_courbe.add(chartPanel);


            frame.add(panel_boutton0);
            frame.add(panel_label1);
            frame.add(panel_input);
            frame.add(panel_label2);
            frame.add(panel_select);

            frame.add(panel_boutton1);
            frame.add(panel_label4);
            frame.add(panel_courbe);

            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        }

        // interface des alertes
        public void IHM_alertes(){
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(new GridLayout(8,1));
            frame2.setSize(300, 200);
            panelboutton_frame2.add(button3);
            frame2.add(panelboutton_frame2);
            frame2.add(label3);
            frame2.add(panel2_frame2);
            frame2.setLocationRelativeTo(null);
            frame2.pack();
            frame2.setVisible(false);

        }

        public void alerte(String message){
            JOptionPane.showMessageDialog(panel2_frame2, message,
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

    public JButton getButton3() {
        return button3;
    }

    public void setVisible_Frame1(Boolean t){
        this.frame.setVisible(t);
    }

    public void setVisible_Frame2(Boolean t){
        this.frame2.setVisible(t);
    }
}
