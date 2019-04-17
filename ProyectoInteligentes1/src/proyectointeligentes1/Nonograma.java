/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Nonograma {

    private ArrayList<ArrayList<Dato>> datos;
    private ArrayList<ArrayList<Integer>> solucion;

    public Nonograma(ArrayList<ArrayList<Dato>> datos) {
        this.datos = datos;
        solucion = new ArrayList<>();
    }

    public void resolverNonograma() {

        //
        //
        //AQUÍ APLICAR LAS REGLAS
        //
        //
        //Después de aplicar las reglas, con las cuales se obtiene la solución inicial
        //para ingresar a nuestro algoritmo
        //
        //
        //
        //
        //Ejemplo temporal de solución y datos obtenidos después de aplicar las reglas
        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> x = new ArrayList<Integer>();
            for (int j = 0; j < 5; j++) {
                x.add(2);
            }
            this.solucion.add(x);
        }

        Dato dato1 = new Dato(1, false);
        Dato dato2 = new Dato(2, false);
        Dato dato3 = new Dato(3, false);
        Dato dato4 = new Dato(5, false);
        ArrayList<Dato> elemArray = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            elemArray.add(dato2);
            this.datos.add(elemArray);
            elemArray.add(dato2);
            this.datos.add(elemArray);
            elemArray.add(dato1);
            elemArray.add(dato2);
            this.datos.add(elemArray);
            elemArray.add(dato4);
            this.datos.add(elemArray);
            elemArray.add(dato3);
            this.datos.add(elemArray);
        }

        //
        //
        //
        //
        //
        Estado estadoInicial = new Estado(-1, null, this.datos, this.solucion);

        if (!estadoInicial.isSolucionado()) {
            System.out.println("Una solución no pudo ser encontrada");
        } else {
            printMatrix(solucion);
        }
    }

    public void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            System.out.print(i + " - ");
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

}
