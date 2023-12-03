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

    // Écouteur pour le bouton 1

    Controller(){

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
                view.IHM_alertes("Bienvenu sur la page d'alertes");
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
}}
