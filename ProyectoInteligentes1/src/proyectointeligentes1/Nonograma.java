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
        String[] cadenaDatos = {"2","2","1,2","5","3","2","2","1,2","5","3"};
        
        for (int i = 0; i < cadenaDatos.length; i++) {
            String[] nums = cadenaDatos[i].split(",");
            ArrayList<Dato> elemArray = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                int num= Integer.parseInt(nums[j]);
                elemArray.add(new Dato(num, false));
            }
            this.datos.add(elemArray);
        }

        //
        //
        //
        //
        //
        System.out.println("Antes de iniciar, en clase Nonograma, datos: ");
        printData(datos);
        Estado estadoInicial = new Estado(-1, null, this.datos, this.solucion);
        
        if (!estadoInicial.isSolucionado()) {
            System.out.println("Una solución no pudo ser encontrada");
        } else {
            printMatrix(estadoInicial.getSolucion());
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
}
