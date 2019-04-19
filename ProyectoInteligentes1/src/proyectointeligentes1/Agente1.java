/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Lenovo
 */
public class Agente1 extends Agent {

    InterfazNonograma gui;

    @Override
    public void setup() {
        gui = new InterfazNonograma(this);
        gui.setVisible(true);
        EnviaMensaje em = new EnviaMensaje();
        addBehaviour(em);
    }

    class EnviaMensaje extends CyclicBehaviour {

        @Override
        public void action() {
            AID idReceptor = new AID();
            idReceptor.setLocalName("Agente2");
            ACLMessage msj = new ACLMessage(ACLMessage.REQUEST);
            msj.setSender(getAID());
            msj.addReceiver(idReceptor);
            doWait(10000);
            msj.setContent(gui.getDatosEnviar());
            send(msj);


            ACLMessage respuesta = blockingReceive();

            if (respuesta != null) {
                String solucion = respuesta.getContent();
                gui.pintarSolucion(solucion);
                takeDown();
            }
        }
    }

}
