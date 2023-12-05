package PMF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Controller extends Observable implements Observer {











   Controller(){

      inialisation_Select_COM();
   }

   public void inialisation_Select_COM(){
      View.getView().setOptions(Model.getModel().list_ports());
   }


   public void demarrer() throws InterruptedException {
        //creation du graphe de l' evolution de la temperature en fonction du temps (S)


        //creation de l'interface IHM
        View.getView().IHM();
        //creation de l'interface des alertes
       View.getView().IHM_alertes();

        //gestion des evenements sur l'interface IHM et IHM_alertes
        View.getView().getButton1().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Code à exécuter lorsque le bouton valider  est appuyé
            // recuperer la valeur entrer dans l' imput 1 de l'interface
            try {

                int valeur1 = Integer.parseInt( View.getView().getTextPane().getText());
                View.getView().setyData3(valeur1);
                setChanged();
                notifyObservers();

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
       while (true){

       View.getView().setyData2(Model.getModel().generateRandomT_embiante(20,34));
       setChanged();
       notifyObservers();
       View.getView().setyData1(Model.getModel().generateRandomT_frigo(-10,1));
       setChanged();
       notifyObservers();
       View.getView().setxData(Model.getModel().generateRandomT_frigo(2,9));
       setChanged();
       notifyObservers();
       Thread.sleep(1000);
}

   }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("update");
        View.getView().IHM();
    }
}
