package PMF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    View view = new View();
    @Override
    public void update(Observable o, Object arg) {

    }

   Controller(){

   }

   public void demarrer(){
        //creation du graphe de l' evolution de la temperature en fonction du temps (S)
        view.create_Graph("o");

        //creation de l'interface IHM
        view.IHM();
        //creation de l'interface des alertes
       view.IHM_alertes();

        //gestion des evenements sur l'interface IHM et IHM_alertes
        view.getButton1().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Code à exécuter lorsque le bouton valider  est appuyé
            // recuperer la valeur entrer dans l' imput 1 de l'interface
            int valeur1 = Integer.parseInt(view.getTextPane().getText());
            //...
        }
    });


        view.getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton alertes  est appuyé
                // Charge la fenetre contenant les alertes
                view.setVisible_Frame1(false);
               view.setVisible_Frame2(true);
               view.alerte("Bienvenu sur la page d'alertes");
                //...
            }
        });



        view.getSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le selecte est appuyé
                // Récupérer la valeur sélectionnée
                String selectedOption = (String) view.getSelect().getSelectedItem();

                //...
            }
        });

       view.getButton3().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Code à exécuter lorsque le bouton 3  est appuyé
               // retour a la fenetre d'Admin
               view.setVisible_Frame2(false);
               view.setVisible_Frame1(true);
               //...
           }
       });
}}
