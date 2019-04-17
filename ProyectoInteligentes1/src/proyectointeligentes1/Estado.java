/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Estado {

    private int nivel;
    private Estado padre;
    private ArrayList<Estado> hijos;
    private ArrayList<ArrayList<Dato>> datos;
    private ArrayList<ArrayList<Integer>> solucion;
    private boolean solucionado;

    public Estado(int nivel, Estado padre, ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {
        this.nivel = nivel;
        this.padre = padre;
        this.datos = datos;
        this.solucion = solucion;
        hijos = new ArrayList<>();
        solucionado = false;
        System.out.println("nivel: " + nivel);
        this.generarHijos();
    }

    public void generarHijos() {
        System.out.println("Entra a generar hijos");
        int cant2 = 0; //Cantidad de números '2' que contiene la lista de números
        //de la fila correspondiente a este nivel, para saber cuántas combinaciones
        //se deben hacer
        for (int i = 0; i < getSolucion().get(nivel + 1).size(); i++) {
            if (getSolucion().get(nivel + 1).get(i) == 2) {
                cant2++;
            }
        }
        System.out.println("cantidad de '2' en la fila" + (this.nivel + 1) + ": " + cant2);

        String[] elementos = {"1", "0"};
        int n = cant2; //La cantidad de 2, que son los que se permutarán
        int r = 2;   //Cantidad de elementos elegidos, "1" y "0"
        Perm(elementos, "", n, r);
    }

    private void Perm(String[] elem, String act, int n, int r) {
        if (n == 0) {
            //Aquí la variable 'act' ya tiene una permutación completa,
            //acomodamos éstos números permutados a la fila y
            //verificamos si esta permutación es válida, comparándola con datosFila

            System.out.println("act: " + act);
            String[] perm = act.split(",");
            int k = 0; //Contador para ir asignando los números de 'act' a la fila solución
            //en las celdas que tiene un '2'
            ArrayList<Integer> numsFila = new ArrayList<>();

            for (int i = 0; i < this.solucion.get(nivel + 1).size(); i++) {
                numsFila.add(this.solucion.get(nivel + 1).get(i));
            }

            System.out.println("numsFila::: " + numsFila);
            for (int i = 0; i < numsFila.size(); i++) {
                System.out.println("numsFila.get(i) = " + numsFila.get(i));
                if (numsFila.get(i) == 2) {
                    int num = Integer.parseInt(perm[k]);
                    System.out.println("numsFila antes: " + numsFila);
                    numsFila.set(i, num);
                    System.out.println("numsFila después: " + numsFila);
                    k++;
                }
            }

            if (cumpleCondicion(numsFila, true)) {

                if (this.nivel + 1 < this.getSolucion().size()) {
                    this.agregarHijo(numsFila);

                } else if (this.nivel + 1 == this.getSolucion().size() && cumpleColumnas(numsFila)) {

                    //Asignar esta fila a la solución real
                    for (int i = 0; i < numsFila.size(); i++) {
                        if (this.getSolucion().get(nivel + 1).get(i) == 2) {
                            int num = numsFila.get(i);
                            this.getSolucion().get(nivel + 1).set(i, num);
                        }
                    }

                    setSolucionado(true);
                    this.setSolucion(this.getSolucion());
                }
            }

            act = "";
        } else {
            for (int i = 0; i < r; i++) {
                Perm(elem, act + elem[i] + ",", n - 1, r);
            }
        }
    }

    public boolean cumpleCondicion(ArrayList<Integer> numsFila, boolean filas) {
        //int cantDatos = this.datos.get(nivel+1).size(); //Cantidad de datos, en el nivel
        ////es decir, cantidad de bloques de 1 que deben haber

        ArrayList<Integer> bloques = new ArrayList<>(); //Contador de cuántos datos (bloques de 1) ya se han comprobado 
        //en el parámetro numsFila
        int cont1 = 0; //Cuenta los número '1' consecutivos que se va encontrando
        boolean cumple = false;
        for (int i = 0; i < numsFila.size(); i++) {
            if (numsFila.get(i) == 1) {
                cont1++;
                System.out.println("entró, celda=1");
                if (i == numsFila.size() - 1) {
                    bloques.add(cont1);
                }
            } else if (numsFila.get(i) == 0) {
                System.out.println("entró, celda=0");
                if (i > 0) //Si no nos encontramos en la primera posición
                {
                    System.out.println("entró, i>0");
                    if (numsFila.get(i - 1) == 1) //Si el anterior número era un '1'
                    {
                        System.out.println("entró, anterior es 1");
                        bloques.add(cont1); //Agrega la cantidad de '1's que encontró, es decir la longitud del bloque
                    }
                }
                cont1 = 0;
            }
        }

        //Obtener los valores de cada dato
        //Cuando se refiere a filas
        ArrayList<Integer> valores = new ArrayList<>();
        if (filas) {

            for (int i = 0; i < this.datos.get(nivel + 1).size(); i++) {
                valores.add(this.datos.get(nivel + 1).get(i).getValor());
            }
        } else {

            int N = this.datos.size();
            for (int i = 0; i < this.datos.get(N + nivel + 1).size(); i++) {
                valores.add(this.datos.get(N + nivel + 1).get(i).getValor());
            }
        }

        System.out.println("bloques: " + bloques);
        System.out.println("valores: " + valores);
        boolean c = bloques.equals(valores);
        System.out.println("" + c);
        return bloques.equals(valores);
    }

    public void agregarHijo(ArrayList<Integer> numsFila) {

        ArrayList<ArrayList<Integer>> copiaSolucion = new ArrayList<>();
        for (int i = 0; i < this.solucion.size(); i++) {
            ArrayList<Integer> nums = new ArrayList<>();
            for (int j = 0; j < this.solucion.get(i).size(); j++) {
                nums.add(this.solucion.get(i).get(j));
            }
            copiaSolucion.add(nums);
        }

        for (int i = 0; i < numsFila.size(); i++) {
            if (copiaSolucion.get(nivel + 1).get(i) == 2) {
                int num = numsFila.get(i);
                copiaSolucion.get(nivel + 1).set(i, num);
            }
        }
        this.hijos.add(new Estado(this.nivel++, this, this.datos, copiaSolucion));
    }

    public boolean cumpleColumnas(ArrayList<Integer> numsFila) {

        ArrayList<ArrayList<Integer>> copiaSolucion = new ArrayList<>();
        for (int i = 0; i < this.solucion.size(); i++) {
            ArrayList<Integer> nums = new ArrayList<>();
            for (int j = 0; j < this.solucion.get(i).size(); j++) {
                nums.add(this.solucion.get(i).get(j));
            }
            copiaSolucion.add(nums);
        }
        for (int i = 0; i < numsFila.size(); i++) {
            copiaSolucion.get(nivel + 1).set(i, numsFila.get(i));
        }

        copiaSolucion = transponerMatriz(copiaSolucion);

        int N = this.datos.size() / 2; //Cantidad de columnas 
        int cantDatos = this.datos.get(N + this.nivel + 1).size(); //Cantidad de datos, en el nivel
        //es decir, cantidad de bloques de 1 que deben haber

        boolean flag = false;
        for (int i = 0; i < copiaSolucion.size(); i++) {
            if (cumpleCondicion(copiaSolucion.get(i), false)) {
                flag = true;
            } else {

                flag = false;
                break;
            }
        }

        return flag;
    }

    public ArrayList<ArrayList<Integer>> transponerMatriz(ArrayList<ArrayList<Integer>> matriz) {

        ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < matriz.size(); i++) {

            ArrayList<Integer> fila = new ArrayList<Integer>();

            for (int j = 0; j < matriz.size(); j++) {
                Integer elemento = ((ArrayList<Integer>) matriz.get(j)).get(i);
                fila.add(elemento);
            }

            resultado.add(fila);
        }

        return resultado;
    }

    /**
     * @param solucion the solucion to set
     */
    public void setSolucion(ArrayList<ArrayList<Integer>> solucion) {
        this.solucion = solucion;
        if (this.nivel >= 0) {
            this.padre.setSolucion(solucion);
            this.padre.setSolucionado(true);
        }
    }

    /**
     * @param solucionado the solucionado to set
     */
    public void setSolucionado(boolean solucionado) {
        this.solucionado = solucionado;
    }

    /**
     * @return the solucion
     */
    public ArrayList<ArrayList<Integer>> getSolucion() {
        return solucion;
    }

    /**
     * @return the solucionado
     */
    public boolean isSolucionado() {
        return solucionado;
    }
}
