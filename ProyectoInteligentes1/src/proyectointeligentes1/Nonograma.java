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

        //Luna
        String[] cadenaDatos = {"2", "2", "1,2", "5", "3", "2", "2", "1,2", "5", "3"};
        
        //Unicornio
//        String[] cadenaDatos = {"11,13", "11,10,2", "3,2,8,3", "1,1,1,5,4", "3,2,2,3,5",
//            "1,2,2,6", "2,1,7", "1,1,1,8", "1,3,9", "2,3,8", "2,1,7",
//            "1,1,7", "2,6", "1,5", "2,4", "2,1,3", "6,3", "8,4", "8,5",
//            "17", "17", "18", "18", "19", "19", "3,2,1,2", "3,1,2",
//            "3,1,2", "2,2", "2,2", "2,1", "2,1,3,2", "2,2,3,4", "3,2,6",
//            "4,10", "2,9", "1,9", "5,2,9", "3,1,2,9", "4,1,1,9", "6,1,8",
//            "6,1,8", "5,3,6", "4,6,6", "3,8,6", "2,10,1,7", "2,12,8",
//            "1,23", "25", "25"};
        
        for (int i = 0; i < cadenaDatos.length; i++) {
            String[] nums = cadenaDatos[i].split(",");
            ArrayList<Dato> elemArray = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                int num = Integer.parseInt(nums[j]);
                elemArray.add(new Dato(num, false));
            }
            this.datos.add(elemArray);
        }
        
        this.setSolucion(Reglas.aplicarReglas(datos));
        printMatrix(this.solucion);

        Estado estadoInicial = new Estado(-1, null, this.datos, this.getSolucion());
        
        if (!estadoInicial.isSolucionado()) {
        } else {
            this.solucion = estadoInicial.getSolucion();
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
