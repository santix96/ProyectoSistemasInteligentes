/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Agente2 extends Agent {

    @Override
    public void setup() {
        RecibeMensaje rm = new RecibeMensaje();
        addBehaviour(rm);
    }

    class RecibeMensaje extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msj = receive();
            if (msj != null) {

                System.out.println("Recibí: " + msj.getContent());
                System.out.println("viene de: " + msj.getSender().getLocalName());

                String[] infoMatriz = msj.getContent().split("-");
                ArrayList<ArrayList<Dato>> datos = new ArrayList<>();
                
                for (int i = 0; i < infoMatriz.length; i++) {
                    ArrayList<Dato> datosLinea = new ArrayList<>();
                    
                    String[] infoLinea = infoMatriz[i].split(",");
                    
                    for (int j = 0; j < infoLinea.length; j++) {
                        Dato d= new Dato(Integer.parseInt(infoLinea[j]), false);
                        datosLinea.add(d);
                    }
                    datos.add(datosLinea);
                }
                System.out.println(datos);
                Nonograma nonograma = new Nonograma(datos);
                
                nonograma.resolverNonograma();

                ACLMessage respuesta = msj.createReply();
                respuesta.setContent("hola"); //Aquí se le manda una cadena que representa la solución del nonograma
                send(respuesta);

                System.out.println("Solución enviada");
            }
        }

    }
}