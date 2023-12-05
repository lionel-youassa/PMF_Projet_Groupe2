package PMF;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;


import java.time.Instant;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


public class View {



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

    String[] options=new String[4];

    JComboBox<String> select =new JComboBox<>(this.options);

    // Création du panneau de visualisation du graphique
     ChartPanel chartPanel;

     // Creation du tableau des Y de la temperature de consigne
    float T_Embiante , T_Frigo;

    // Création des données pour les trois courbes du graphe dans la vue

            double[] xData = {1, 2, 3, 4, 5}; // Valeurs x communes pour les trois courbes

            double[] yData1 = {2, 4, 6, 8, 10}; // Valeurs y pour la première courbe
            double[] yData2 = {1, 4, 2, 3, 8};// Valeurs y pour la deuxième courbe
            double[] yData3 = {2, 3, 4, 1, 5};// Valeurs y pour la troisiemme courbe

private static View view;
    View(){

    }

    public static View getView(){
        if (view == null) {
            view = new View();
        }
        return view;

    }





    public void create_Graph(  double[] xData,double[] yData1,double[] yData2,double[] yData3) {

        // Création de la variable contenant les données pour la courbe
        XYSeries series = new XYSeries("Courbe");

       // while (T != "0") {

            Instant instant = Instant.now(); // Temps instantané actuel


        // Création des données pour les trois courbes
        //double[] xData = {1, 2, 3, 4, 5}; // Valeurs x communes pour les trois courbes

        //double[] yData1 = {2, 4, 6, 8, 10}; // Valeurs y pour la première courbe
       // double[] yData2 = {1, 4, 2, 3, 8};// Valeurs y pour la deuxième courbe






        //}

            // Création du jeu de données
            DefaultXYDataset dataset = new DefaultXYDataset();
            dataset.addSeries("Temperature embiante", new double[][] { xData, yData1 });
            dataset.addSeries("Temperature du frigo", new double[][] { xData, yData2 });
            dataset.addSeries("Temperature de consigne", new double[][] { xData, yData3 });


            // Création du graphique avec le dataset
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Evolution des temperatures en fonction du temps(S)", // Titre du graphique
                    "X", // Titre de l'axe des abscisses
                    "Y", // Titre de l'axe des ordonnées
                    dataset, // Dataset contenant les données
                    PlotOrientation.VERTICAL, // Orientation du graphique
                    true, // Légende visible
                    true, // Infobulles activées
                    false // URLs cliquables
            );

        // Personnalisation du graphique si nécessaire
        XYPlot plot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(false); // Empêche l'axe des ordonnées
        chartPanel = new ChartPanel(chart);
        }

        // interface d' Administration du frigo
        public void IHM(){
            backgroundPanel.removeAll();
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
            frame.setSize(896, 500);


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

            create_Graph(getxData(), getyData1(), getyData2(), getyData3());
            panel_right.removeAll();
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



            //creation de l'interface des alertes
            IHM_alertes();
        }

        // interface des alertes
        public void IHM_alertes(){
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(new GridLayout(8,1));
            frame2.setSize(600, 400);
            label3.setFont(label3.getFont().deriveFont(16.0f)); // ajustez la taille de police
            Rounder_button(button3);


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

    public void setOptions(String[] options) {
        for (int i=0; i<options.length; i++) {
            this.options[i] = options[i];
        }
         this.select =new JComboBox<>(this.options);

    }
    public void notificationInfo(String message){
        JOptionPane.showMessageDialog(panel_left, message,

                "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationErro(String message){
        JOptionPane.showMessageDialog(panel_left, message,
                "Erreure", JOptionPane.ERROR_MESSAGE);
    }

    public void setyData3(float T) {
        for(int i=0; i<=yData3.length-1;i++) {
            this.yData3[i]=T;
            System.out.println("toto"+yData3[i]);


        }

    }

    public void setxData(float T) {

            this.xData[0] = T;
            System.out.println("toto"+xData[0]);


        }







    public void setyData1(float T) {
            this.yData1[0]=T;

        //this.yData1 = Data;


    }

    public void setyData2(float T) {
            this.yData2[0]=T;

        }
        //this.yData2 = Data;




    public double[] getyData3() {
        return yData3;
    }

    public double[] getxData() {
        return xData;
    }

    public double[] getyData1() {
        return yData1;
    }

    public double[] getyData2() {
        return yData2;
    }

    public  void  updateView(){
        IHM();
    }
}
