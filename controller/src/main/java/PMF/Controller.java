package PMF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {




    // Création des données pour les trois courbes du graphe dans la vue
    ArrayList<Double> xData = new ArrayList<>();
    ArrayList<Double> yData1 = new ArrayList<>();
    ArrayList<Double> yData2 = new ArrayList<>();
    ArrayList<Double> yData3 = new ArrayList<>();
    int T;

    double seconds;




   Controller(){
       xData.add(0.0);
       yData1.add(0.0);
       yData2.add(0.0);
       yData3.add(0.0);


      inialisation_Select_COM();

   }

   public void inialisation_Select_COM(){
      View.getView().setOptions(Model.getModel().list_ports());
   }


   public void demarrer() throws InterruptedException {
        //creation du graphe de l' evolution de la temperature en fonction du temps (S)


        //creation de l'interface IHM
       View.getView().create_Graph(View.getView().getxData(), View.getView().getyData1(),View.getView().getyData2(),View.getView().getyData3());
        View.getView().IHM();



        //gestion des evenements sur l'interface IHM et IHM_alertes

        View.getView().getButton1().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Code à exécuter lorsque le bouton valider  est appuyé
            // recuperer la valeur entrer dans l' imput 1 de l'interface
            try {

                 T = Integer.parseInt( View.getView().getTextPane().getText());

                View.getView().getyData3().add((double)T);
                View.getView().getxData1().add(seconds);

                View.getView().create_Graph(View.getView().getxData(), View.getView().getyData1(),View.getView().getyData2(),View.getView().getyData3());
                // Model.getModel().send_info(valeur1);

                //...
            }catch (NumberFormatException a){
                View.getView().alerte("Veuillez entrer un nombre");

            }
        }
    });


       View.getView().getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton alertes  est appuyé
                // Charge la fenetre contenant les alertes
                View.getView().setVisible_Frame1(false);
                View.getView().setVisible_Frame2(true);
                View.getView().alerte("Bienvenu sur la page d'alertes");

                //...
            }
        });



       View.getView().getSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le selecte est appuyé
                // Récupérer la valeur sélectionnée
                String selectedOption = (String)  View.getView().getSelect().getSelectedItem();
                Model.getModel().connect_ports(selectedOption);
                View.getView().set_TextEcranlabel_Humdite(Model.getModel().readData());
                setChanged();
                notifyObservers();
                //...
            }
        });

       View.getView().getButton3().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Code à exécuter lorsque le bouton 3  est appuyé
               // retour a la fenetre d'Admin
               View.getView().setVisible_Frame2(false);
               View.getView().setVisible_Frame1(true);

               //...
           }
       });

    int i=0;
       while (true){
           yData1.add(((double)Model.getModel().generateRandomT_embiante(20, 30)));
           yData2.add(((double)Model.getModel().generateRandomT_frigo(-10, 5)));
           // Recupere  le temps actuel en seconde
           long currentTimeMillis = System.currentTimeMillis();

           //double seconds = currentTimeMillis / 1000.0;

            seconds=i+1;
           View.getView().getxData1().add(seconds);
           View.getView().setxData(seconds);
           View.getView().setyData1(yData1);
           View.getView().setyData2(yData2);
           View.getView().getyData3().add((double)T);

           View.getView().create_Graph(View.getView().getxData(), View.getView().getyData1(),View.getView().getyData2(),View.getView().getyData3());
           // System.out.println("temps: "+View.getView().getxData().get(i));
           //System.out.println("yData1: "+View.getView().getyData1().get(i));
            i++;
           Thread.sleep(1000);
       }

   }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");

    }
}
