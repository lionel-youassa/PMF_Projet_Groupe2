package PMF;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;
import java.time.Instant;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class View extends Observable {


     JFrame frame =new JFrame("Manager_PMF");
    JFrame frame2 =new JFrame("PMF_Alertes");
    JLabel label1 = new JLabel("Temperature de consigne:");
    JLabel label2 = new JLabel("Choix du port de connection arduino");
    JLabel label3 = new JLabel("Consulter vos alertes ici ");
    JPanel backgroundPanel = new JPanel(new GridLayout(1,2));
    JPanel panel_left = new JPanel(new GridLayout(6,1));
    JPanel panel_right = new JPanel(new GridLayout(1,2));
    JPanel panel_label1=new JPanel(new BorderLayout());
    JPanel panel_label2=new JPanel(new BorderLayout());

    JPanel panel_vide1=new JPanel();
     JPanel panel_input= new JPanel();
     JPanel panel_select=new JPanel();
     JPanel panel_boutton= new JPanel();


    JPanel panelboutton_frame2=new JPanel();
    JPanel panel2_frame2=new JPanel();


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
            series.add(i+20, Integer.parseInt(T));
        }
        //}

            // Création du dataset avec la série de données
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            // Création du graphique avec le dataset
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Evolution de la temperature en fonction du temps(S)", // Titre du graphique
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
            //select.setPreferredSize(new Dimension(100,20));
            label1.setBounds(5,8,200,20);
            label1.setFont(label1.getFont().deriveFont(16.0f)); // ajustez la taille de police
            label2.setFont(label2.getFont().deriveFont(16.0f)); // ajustez la taille de police
            textPane.setBounds(4,35,200,40);
            //arrodissement des bodure de textpane
            RoundBoder_textpane();
            Rounder_button(button2);
            Rounder_button(button1);
            button2.setBounds(100,30,80,40);
            label2.setBounds(3,3,300,20);
            select.setBounds(3,30,200,40);
            button1.setBounds(10,30,80,40);
            panel_vide1.setSize(20,20);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1,2));
            frame.setSize(880, 480);


            panel_input.setBackground(new Color(175, 238, 238));
            panel_vide1.setBackground(new Color(175, 238, 238));
            panel_select.setBackground(new Color(175, 238, 238));
            panel_boutton.setBackground(new Color(175, 238, 238));
            panel_left.setBackground(new Color(175, 238, 238));

            panel_label1.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel_input.setLayout(null);
            panel_label2.setLayout(new FlowLayout(FlowLayout.LEFT));

            panel_select.setLayout(null);
            panel_boutton.setLayout(null);

            backgroundPanel.add(panel_left);
            backgroundPanel.add(panel_right);


            panel_left.add(panel_input);
            panel_left.add(panel_vide1);
            panel_left.add(panel_select);
            panel_left.add(panel_boutton);

            panel_right.add(chartPanel);
            panel_right.setBackground(Color.green);





            panel_input.add(label1);
            panel_input.add(textPane);
            panel_select.add(label2);
            panel_select.add(select);
            panel_boutton.add(button1);
            panel_boutton.add(button2);


            frame.add(backgroundPanel);
            frame.setLocationRelativeTo(null);
           // frame.pack();
            frame.setVisible(true);
        }

        // interface des alertes
        public void IHM_alertes(){
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(new GridLayout(8,1));
            frame2.setSize(600, 400);
            panelboutton_frame2.add(button3);
            frame2.add(panelboutton_frame2);
            frame2.add(label3);
            frame2.add(panel2_frame2);
            frame2.setLocationRelativeTo(null);

            frame2.setVisible(false);

        }

        public void alerte(String message){
            JOptionPane.showMessageDialog(panel2_frame2, message,
                    "Avertissement", JOptionPane.WARNING_MESSAGE);

        }

        public void RoundBoder_textpane(){
            // Crée une bordure arrondie
            Border roundedBorder = BorderFactory.createLineBorder(Color.GRAY, 4, true);

            // Applique la bordure arrondie au JTextPane
            textPane.setBorder(roundedBorder);

        }
        public void Rounder_button(JButton button){
            // Applique une bordure arrondie prédéfinie
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));


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
