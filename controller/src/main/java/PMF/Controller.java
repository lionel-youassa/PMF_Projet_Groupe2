package PMF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import PMF.View;
public class Controller implements Observer {
    Model model=new Model();

    View view= new View();





   Controller() throws InterruptedException {

        inialisation_Select_COM();


   }

   public void inialisation_Select_COM(){
      view.setOptions(model.list_ports());
   }
    @Override
    public void update (Observable o, Object arg){

        //creation de l'interface IHM
        if(o instanceof View) {
            System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
          view.create_Graph(((View) o).getxData(), ((View) o).getyData1(), ((View) o).getyData2(), ((View) o).getyData3());
            view.IHM();
        }

    }



   public void demarrer() throws InterruptedException {

       //creation de l'interface IHM
       view.IHM();


        //gestion des evenements sur l'interface IHM et IHM_alertes
        view.getButton1().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Code à exécuter lorsque le bouton valider  est appuyé
            // recuperer la valeur entrer dans l' imput 1 de l'interface
            try {

                int Temperature_consigne=Integer.parseInt(view.getTextPane().getText());
                view.setyData3(Temperature_consigne);
                int i=0;
                System.out.println(view.getyData3()[i]);


                view.IHM();

                 //envoie la valeure entrer a la carte arduino
                   // model.send_info(Temperature_consigne);

            }catch (NumberFormatException a){
                view.alerte("Veuillez entrer un nombre");

            }
        }
    });


        view.getButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton alertes  est appuyé
                // Charge la fenetre contenant les alertes
                view.setVisible_Frame1(false);
               view.setVisible_Frame2(true);
               view.notificationInfo("Bienvenu sur la page d'alertes");
                //...
            }
        });



        view.getSelect().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le selecte est appuyé
                // Récupérer la valeur sélectionnée

                String selectedOption = (String) view.getSelect().getSelectedItem();
                model.connect_ports(selectedOption);
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
       while (true) {
          Thread.sleep(1000);

            view.setxData(model.generateRandomX(1, 8));
           view.setyData1(model.generateRandomT_frigo(-10,10));
            view.setyData2(model.generateRandomT_embiante(-10, 10));


            view.IHM();
           Thread.sleep(1000);


       }









}








}
