package PMF;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.xy.*;




import java.time.Instant;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


public class View {


    DefaultXYDataset dataset  = new DefaultXYDataset();

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


     JPanel panel_input= new JPanel();
     JPanel panel_select=new JPanel();
     JPanel panel_boutton= new JPanel();

     JLabel label_humidite= new JLabel("Taux humidite:");
     JLabel ecranlabel_Humdite= new JLabel("12%");
     JPanel panel_humidite= new JPanel();
     JPanel panel_enfant_humidite=new JPanel();

     JPanel panel_menu =new JPanel();




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

//            double[] xData = {1, 2, 3, 4, 5}; // Valeurs x communes pour les trois courbes
//
//            double[] yData1 = {2, 4, 6, 8, 10}; // Valeurs y pour la première courbe
//            double[] yData2 = {1, 4, 2, 3, 8};// Valeurs y pour la deuxième courbe
//            double[] yData3 = {2, 3, 4, 1, 5};// Valeurs y pour la troisiemme courbe

                // Création des données pour les trois courbes du graphe dans la vue
                ArrayList<Double> xData = new ArrayList<>();
                ArrayList<Double> xData1=new ArrayList<>();

                ArrayList<Double> yData1 = new ArrayList<>();
                ArrayList<Double> yData2 = new ArrayList<>();
                ArrayList<Double> yData3 = new ArrayList<>(1);
    int i=0;
private static View view;
    View(){
        xData.add(0.0);
       xData1.add(0.0);
        yData1.add(0.0);
        yData2.add(0.0);
        yData3.add(0.0);;
       // yData3.add(0.0);
        //yData3.add(0.0);
        //View.getView().updateGraphe();

    }

    public static View getView(){
        if (view == null) {
            view = new View();
        }
        return view;

    }


    private static double[] convertToPrimitiveArray(ArrayList<Double> list) {
        double[] array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }


    public void create_Graph(ArrayList<Double> xData,ArrayList<Double> yData1,  ArrayList<Double> yData2,  ArrayList<Double> yData3) {


        // Créer un DefaultXYDataset pour stocker les données

        dataset.addSeries("Temperature_Embiante", new double[][]{convertToPrimitiveArray(xData), convertToPrimitiveArray(yData1)});
        dataset.addSeries("Temperature_Frigo", new double[][]{convertToPrimitiveArray(xData), convertToPrimitiveArray(yData2)});
        System.out.println(" xData1: "+View.getView().getxData1().size());
        System.out.println(" yData3: "+View.getView().getyData3().size());

        dataset.addSeries("Temperature_Consigne", new double[][]{convertToPrimitiveArray(View.getView().getxData1()), convertToPrimitiveArray(yData3)});


        // Création du graphique avec le dataset
          JFreeChart chart = ChartFactory.createXYLineChart(
                    "Evolution des temperatures en fonction du temps(S)", // Titre du graphique
                    "temps(s)", // Titre de l'axe des abscisses
                    "Temperature(k)", // Titre de l'axe des ordonnées
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
        View.getView().setChartPanel(new ChartPanel(chart));





        }

        // interface d' Administration du frigo
        public void IHM(){
            backgroundPanel.removeAll();

            label1.setBounds(5,8,200,20);
            label1.setFont(label1.getFont().deriveFont(16.0f)); // ajustez la taille de police
            label2.setFont(label2.getFont().deriveFont(16.0f)); // ajustez la taille de police
            label_humidite.setFont(label_humidite.getFont().deriveFont(16.0f)); // ajustez la taille de police
            ecranlabel_Humdite.setHorizontalAlignment(JLabel.CENTER);
            ecranlabel_Humdite.setFont(new Font("Arial",Font.PLAIN,24));
            ecranlabel_Humdite.setForeground(Color.WHITE);
            textPane.setBounds(5,35,200,40);
            //arrodissement des bodure de textpane
            RoundBoder_textpane();
            Rounder_button(button2);
            Rounder_button(button1);
           // RoundBoder_panel(panel_enfant_humidite);
            button2.setBounds(100,30,80,40);
            label2.setBounds(3,3,300,20);
            select.setBounds(3,30,200,40);
            button1.setBounds(10,30,80,40);
            label_humidite.setBounds(5,8,200,40);
            panel_enfant_humidite.setBounds(5,40,200,40);;

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1,2));
            frame.setSize(996, 700);


            panel_input.setBackground(new Color(175, 238, 238));
            panel_humidite.setBackground(new Color(175, 238, 238));
            panel_enfant_humidite.setBackground(new Color(56, 89, 105));
            panel_select.setBackground(new Color(175, 238, 238));
            panel_boutton.setBackground(new Color(175, 238, 238));
            panel_left.setBackground(new Color(175, 238, 238));

            panel_humidite.setLayout(null);
            panel_label1.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel_input.setLayout(null);
            panel_label2.setLayout(new FlowLayout(FlowLayout.LEFT));

            panel_select.setLayout(null);
            panel_boutton.setLayout(null);

            backgroundPanel.add(panel_left);
            backgroundPanel.add(panel_right);


            panel_left.add(panel_input);
            panel_left.add(panel_humidite);
            panel_left.add(panel_select);
            panel_left.add(panel_boutton);


            panel_right.removeAll();
            panel_right.add(View.getView().getChartPanel());
            panel_right.setBackground(Color.green);





            panel_input.add(label1);
            panel_input.add(textPane);
            panel_select.add(label2);
            panel_select.add(select);
            panel_boutton.add(button1);
            panel_boutton.add(button2);
            panel_enfant_humidite.add(ecranlabel_Humdite);
            panel_humidite.add(label_humidite);
            panel_humidite.add(panel_enfant_humidite);


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
        public void RoundBoder_panel(JPanel panel){
            // Crée une bordure arrondie
            Border roundedBorder = BorderFactory.createLineBorder(new Color(98,155,181), 4, true);

            // Applique la bordure arrondie au JTextPane
            panel.setBorder(roundedBorder);

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


    public ArrayList<Double> getxData() {
        return xData;
    }

    public ArrayList<Double> getyData1() {
        return yData1;
    }

    public ArrayList<Double> getyData2() {
        return yData2;
    }

    public ArrayList<Double> getyData3() {
        return yData3;
    }


    public void setxData(double t) {
        this.xData.add(t);
    }

    public void setyData1(ArrayList<Double> yData1) {
        this.yData1 = yData1;
    }

    public void setyData2(ArrayList<Double> yData2) {
        this.yData2 = yData2;
    }

    public void setyData3(ArrayList<Double> yData3) {
        // Recupere  le temps actuel en seconde
       // long currentTimeMillis = System.currentTimeMillis();
       // double seconds = currentTimeMillis / 1000.0;
        //seconds=i+1;
        //this.xData1.add(seconds);
        this.yData3 = yData3;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public void setChartPanel(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
    }

    public JPanel getPanel_right() {
        return panel_right;
    }



    public ArrayList<Double> getxData1() {
        return xData1;
    }

    public void updateGraphe(){
        // Mise à jour du graphique lorsque les données sont modifiées

        dataset.addChangeListener(new DatasetChangeListener() {
            @Override
            public void datasetChanged(DatasetChangeEvent event) {
                // Mettre à jour le graphique
                panel_right.removeAll();
                panel_right.add(View.getView().getChartPanel());
            }
        });
    }

    public void set_TextEcranlabel_Humdite(String text) {
        this.ecranlabel_Humdite.setText(text);
    }

    public void setxData1(ArrayList<Double> xData1) {
        this.xData1 = xData1;
    }
}
