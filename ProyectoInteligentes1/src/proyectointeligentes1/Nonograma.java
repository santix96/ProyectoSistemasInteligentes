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

        this.setSolucion(Reglas.aplicarReglas(datos));
        printMatrix(this.solucion);

        
        Estado estadoInicial = new Estado(-1, null, this.datos, this.getSolucion());
        
        if (!estadoInicial.isSolucionado()) {
        } else {
            setSolucion(estadoInicial.getSolucion());
        }
    }
    
        /*
    Este metodo se encarga de inicializar la solucion principal, esta 
    es una matriz completa llena de numeros 2(estados no completados).
     */
    private static void inicializarSolucion(ArrayList<ArrayList<Integer>> solucion, int N) {

        for (int i = 0; i < N; i++) {

            ArrayList<Integer> x = new ArrayList<Integer>();

            for (int j = 0; j < N; j++) {
                x.add(2);
            }

            solucion.add(x);
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
    
    public static void printData(ArrayList<ArrayList<Dato>> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.print(i + " - ");
            for (int j = 0; j < data.get(i).size(); j++) {
                System.out.print("[" + data.get(i).get(j).getValor() + "," + data.get(i).get(j).isCompletado() + "] ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    /**
     * @return the solucion
     */
    public ArrayList<ArrayList<Integer>> getSolucion() {
        return solucion;
    }

    /**
     * @param solucion the solucion to set
     */
    public void setSolucion(ArrayList<ArrayList<Integer>> solucion) {
        this.solucion = solucion;
    }
}
