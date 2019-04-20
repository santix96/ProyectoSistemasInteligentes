/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

import java.util.ArrayList;

/**
 * 
 * Esta clase contiene todas las reglas que serán aplicadas a nuestro algoritmo
 * para poder llegar a uno solución inicial, y a partir de esta se genera una descendencia
 * a la cual también se aplicarán estas mismas reglas acotando aun más el proceso
 * para llegar a una solucion final.
 *
 * @author Santiago
 */
public class Reglas {

    /*
    Esta funcion permite ejecutar las diferentes reglas en un orden especifico, 
    las dos primeras reglas no es necesario que las apliquemos mas de una vez, las
    reglas posteriores se aplicaran en orden repetidamente hasta que al ejecutar
    estas ya no se efectue ninguna modificacion en el nonograma solucion.
    @return matriz solucion(llena con 2, 1 o 0, dependiendo del punto en el que se encuentre)
     */
    public static ArrayList<ArrayList<Integer>> aplicarReglas(ArrayList<ArrayList<Dato>> datos) {

        ArrayList<ArrayList<Integer>> solucion = new ArrayList<ArrayList<Integer>>();

        boolean cambio = false;

        ArrayList respuesta = new ArrayList();

        int N = datos.size() / 2;

        inicializarSolucion(solucion, N);

        aplicarRegla1(datos, solucion, N);
        aplicarRegla2(datos, solucion, N);

        do {
            cambio = false;

            respuesta = aplicarRegla3(datos, solucion);

            cambio = Boolean.parseBoolean(respuesta.get(0).toString());
            solucion = (ArrayList<ArrayList<Integer>>) respuesta.get(1);
            datos = (ArrayList<ArrayList<Dato>>) respuesta.get(2);

            respuesta = aplicarRegla4(datos, solucion);

            if (!cambio) {
                cambio = Boolean.parseBoolean(respuesta.get(0).toString());
            }
            solucion = (ArrayList<ArrayList<Integer>>) respuesta.get(1);
            datos = (ArrayList<ArrayList<Dato>>) respuesta.get(2);

            respuesta = aplicarRegla5(datos, solucion);

            if (!cambio) {
                cambio = Boolean.parseBoolean(respuesta.get(0).toString());
            }
            solucion = (ArrayList<ArrayList<Integer>>) respuesta.get(1);
            datos = (ArrayList<ArrayList<Dato>>) respuesta.get(2);

            respuesta = aplicarRegla6(datos, solucion);

            if (!cambio) {
                cambio = Boolean.parseBoolean(respuesta.get(0).toString());
            }
            solucion = (ArrayList<ArrayList<Integer>>) respuesta.get(1);
            datos = (ArrayList<ArrayList<Dato>>) respuesta.get(2);

            respuesta = aplicarRegla7(datos, solucion);

            if (!cambio) {
                cambio = Boolean.parseBoolean(respuesta.get(0).toString());
            }
            solucion = (ArrayList<ArrayList<Integer>>) respuesta.get(1);
            datos = (ArrayList<ArrayList<Dato>>) respuesta.get(2);

        } while (cambio);

        return solucion;
    }

    /*
    Este metodo se encarga de rotar la matriz solucion hacia la izquierda, para
    poder acceder a las columnas ingresando a estas como si fueran filas:
    Entrada:
        2 2 2 2
        2 1 1 1
        0 1 0 1
        2 1 1 1
    Salida:
        2 2 0 2
        2 1 1 1
        2 1 0 1
        2 1 1 1
     */
    public static ArrayList<ArrayList<Integer>> transponerMatriz(ArrayList<ArrayList<Integer>> matriz) {
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

    /*
	Este metodo modifica todos los valores de una fila o columna,
        si el dato ingresado por el usuario es 0 marca todas las celdas como espacios
        en blanco (valor = 0) y si el dato ingresado es igual a N entonces los marca
        como casillas en negro (valor = 1).
     */
    private static void aplicarRegla1(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion, int N) {

        for (int i = 0; i < datos.size(); i++) {

            ArrayList<Dato> listaDatos = datos.get(i);

            for (int j = 0; j < listaDatos.size(); j++) {

                int dato = listaDatos.get(j).getValor();
                boolean completado = listaDatos.get(j).isCompletado();

                if ((dato == 0 || dato == N) && !completado) {

                    if (i < N) { //Filas

                        for (int k = 0; k < N; k++) {
                            if (dato == 0) {
                                solucion.get(i).set(k, 0);
                            } else {
                                solucion.get(i).set(k, 1);
                            }
                        }
                    } else { //Columnas

                        int numeroColumna = i - N;

                        for (int k = 0; k < N; k++) {

                            if (dato == 0) {
                                solucion.get(k).set(numeroColumna, 0);
                            } else {
                                solucion.get(k).set(numeroColumna, 1);
                            }
                        }
                    }

                    listaDatos.get(j).setCompletado(true);
                }

            }
        }

    }

    /*
	Si hay lÃ­neas donde la suma de los datos de entrada y los espacios 
        en blanco son iguales a N, significa que dicha lÃ­nea solamente tendrÃ¡ 
        una manera de ser completada. Se rellenan los datos en 1 para las celdas 
        de color negro y 0 las celdas vacÃ­as, toda la linea queda en estado 
        completado.
     */
    private static void aplicarRegla2(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion, int N) {

        for (int i = 0; i < datos.size(); i++) {

            ArrayList<Dato> listaDatos = datos.get(i);

            int total = 0;

            for (int j = 0; j < listaDatos.size(); j++) {

                int valor = listaDatos.get(j).getValor();
                boolean completado = listaDatos.get(j).isCompletado();
                if (!completado) {
                    total += valor;
                } else {
                    total = -1;
                    break;
                }
            }

            int numeroEspacios = listaDatos.size() - 1;

            if ((total + numeroEspacios) == N) {

                int contador = 0;
                int numeroColumna = i - N;

                for (int j = 0; j < listaDatos.size(); j++) {

                    int valor = listaDatos.get(j).getValor();

                    for (int k = 0; k < valor; k++) {

                        if (i < N) {
                            solucion.get(i).set(contador + k, 1);
                        } else {
                            solucion.get(contador + k).set(numeroColumna, 1);
                        }
                    }

                    contador += valor;

                    if (j < listaDatos.size() - 1) {

                        if (i < N) {
                            solucion.get(i).set(contador, 0);
                        } else {
                            solucion.get(contador).set(numeroColumna, 0);
                        }
                    }

                    contador++;

                    listaDatos.get(j).setCompletado(true);
                }

            }
        }
    }

    /*
	Se remueven los datos completados y en la estructura resultante se revisa 
        que la primera o la Ãºltima celda estÃ¡n pintadas, si alguna estÃ¡ pintada, 
        se llenan las celdas a partir de esta hasta completar el valor correspondiente
        a la entrada de dicha fila.
     */
    private static ArrayList aplicarRegla3(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {

        boolean cambio = false;

        /*cuando Z = 0 estamos recorriendo las filas, 
          cuando Z = 1 estamos recorriendo las columnas.*/
        for (int z = 0; z < 2; z++) {

            if (z == 1) {
                solucion = transponerMatriz(solucion);
            }

            ArrayList<ArrayList<Dato>> backupDatos = clonarDatos(datos);
            ArrayList<ArrayList<Integer>> backupSolucion = clonarSolucion(solucion);

            //Elimina elementos completados.
            datos = trimDatos(datos);
            solucion = trimSolucion(solucion);

            for (int i = 0; i < solucion.size(); i++) {
                ArrayList<Integer> fila = solucion.get(i);
                if (fila.size() > 0 && fila.get(0) == 1) {
                    Dato primerGrupo = null;
                    if (z == 0) {
                        if (datos.get(i).size() > 0) {
                            primerGrupo = datos.get(i).get(0);
                        }
                    } else {
                        if (datos.get(i + solucion.size()).size() > 0) {
                            primerGrupo = datos.get(i + solucion.size()).get(0);
                        }
                    }
                    if (primerGrupo != null && !primerGrupo.isCompletado()) {

                        int valor = primerGrupo.getValor();

                        for (int j = 0; j < valor; j++) {

                            fila.set(j, 1);
                        }
                        primerGrupo.setCompletado(true);
                        cambio = true;
                    }
                    if (primerGrupo != null && primerGrupo.getValor() < fila.size() && fila.get(primerGrupo.getValor()) == 2) {
                        fila.set(primerGrupo.getValor(), 0);
                        cambio = true;
                    }
                }

                if (fila.size() > 0 && fila.get(fila.size() - 1) == 1) {

                    Dato ultimoGrupo = null;

                    if (z == 0) {
                        if (datos.get(i).size() > 0) {
                            ultimoGrupo = datos.get(i).get(datos.get(i).size() - 1);
                        }
                    } else {
                        if (datos.get(i + solucion.size()).size() > 0) {
                            ultimoGrupo = datos.get(i + solucion.size()).get(datos.get(i + solucion.size()).size() - 1);
                        }
                    }

                    if (ultimoGrupo != null && !ultimoGrupo.isCompletado()) {

                        int valor = ultimoGrupo.getValor();

                        for (int j = fila.size() - 1; j >= fila.size() - valor; j--) {
                            fila.set(j, 1);
                        }

                        ultimoGrupo.setCompletado(true);
                        cambio = true;
                    }

                    if (ultimoGrupo != null && fila.size() - ultimoGrupo.getValor() - 1 >= 0 && fila.get(fila.size() - ultimoGrupo.getValor() - 1) == 2) {

                        fila.set(fila.size() - ultimoGrupo.getValor() - 1, 0);
                        cambio = true;
                    }

                }

            }

            datos = restaurarDatos(backupDatos, datos);
            solucion = restaurarSolucion(backupSolucion, solucion);

            if (z == 0) {
                actualizarDatos(datos, solucion);
            }

        }

        solucion = transponerMatriz(solucion);

        actualizarDatos(datos, solucion);

        ArrayList respuesta = new ArrayList();

        respuesta.add(cambio);
        respuesta.add(solucion);
        respuesta.add(datos);

        return respuesta;
    }

    /*
	Este metodo se encarga de revisar todas las posibles secciones que podrÃ­an ser la 
        soluciÃ³n, solo deja como completadas aquellas que se solapan entre sÃ­, 
        esto significa, que dichas celdas deberÃ¡n ir pintadas de negro.
     */
    private static ArrayList aplicarRegla4(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {

        boolean cambio = false;
        for (int z = 0; z < 2; z++) {

            if (z == 1) {
                solucion = transponerMatriz(solucion);
            }

            ArrayList<ArrayList<Dato>> backupDatos = clonarDatos(datos);
            ArrayList<ArrayList<Integer>> backupSolucion = clonarSolucion(solucion);

            datos = trimDatos(datos);
            solucion = trimSolucion(solucion);

            for (int i = 0; i < solucion.size(); i++) {

                ArrayList<Integer> fila = solucion.get(i);

                int tamañoFila, sumaGrupos, numeroGrupos;

                tamañoFila = fila.size();
                if (tamañoFila == 0) {
                    continue;
                }
                ArrayList<Dato> filasGrupos = null;
                if (z == 0) {
                    filasGrupos = datos.get(i);
                } else {
                    filasGrupos = datos.get(i + solucion.size());
                }

                sumaGrupos = numeroGrupos = 0;
                for (Dato d : filasGrupos) {
                    sumaGrupos += d.getValor();
                }

                numeroGrupos = filasGrupos.size();

                int gl = tamañoFila - sumaGrupos - numeroGrupos + 1;
                for (int j = 0; j < filasGrupos.size(); j++) {
                    Dato grupo = filasGrupos.get(j);

                    int contadorCeldasFijas = 0;
                    if (grupo.getValor() > gl) {
                        contadorCeldasFijas = grupo.getValor() - gl;
                    }

                    for (int k = 0; k < contadorCeldasFijas; k++) {
                        int sumaGruposAnteriores = 0;

                        for (int l = 0; l < j; l++) {
                            sumaGruposAnteriores += filasGrupos.get(l).getValor();
                        }

                        if (Integer.parseInt(fila.get(sumaGruposAnteriores + j + gl + k).toString()) != 1) {
                            cambio = true;
                        }
                        fila.set(sumaGruposAnteriores + j + gl + k, 1);
                    }
                }

            }

            datos = restaurarDatos(backupDatos, datos);
            solucion = restaurarSolucion(backupSolucion, solucion);

            if (z == 0) {
                actualizarDatos(datos, solucion);
            }

        }

        solucion = transponerMatriz(solucion);

        actualizarDatos(datos, solucion);

        ArrayList res = new ArrayList();

        res.add(cambio);
        res.add(solucion);
        res.add(datos);

        return res;

    }

    /*
	Si solo queda un dato de los ingresados por el usuario por ubicar
        entonces se recorre la fila de no completados hasta encontrar un dato en
        negro (1)  una vez encontrado,  se le suma el valor del dato faltante hacia
        adelante y hacia atrÃ¡s, y los que no estÃ©n dentro de este rango se completan
        como espacios en blanco (0).

     */
    private static ArrayList aplicarRegla5(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {

        boolean cambio = false;

        for (int z = 0; z < 2; z++) {

            if (z == 1) {
                solucion = transponerMatriz(solucion);
            }

            ArrayList<ArrayList<Dato>> backupDatos = clonarDatos(datos);
            ArrayList<ArrayList<Integer>> backupSolucion = clonarSolucion(solucion);

            datos = trimDatos(datos);
            solucion = trimSolucion(solucion);
            for (int i = 0; i < solucion.size(); i++) {

                ArrayList<Integer> fila = solucion.get(i);

                ArrayList<Dato> datosFila = null;
                if (z == 0) {
                    datosFila = datos.get(i);
                } else {
                    datosFila = datos.get(i + solucion.size());
                }

                if (datosFila.size() == 1) {
                    for (int j = 0; j < fila.size(); j++) {

                        int elemento = fila.get(j);

                        if (elemento == 1) {

                            int dato = datosFila.get(0).getValor();
                            for (int k = j + dato; k < fila.size(); k++) {
                                int elem = fila.get(k);

                                if (elem == 2) {
                                    fila.set(k, 0);
                                    cambio = true;
                                }
                            }

                            break;
                        }
                    }

                    for (int j = fila.size() - 1; j >= 0; j--) {
                        int elemento = fila.get(j);

                        if (elemento == 1) {

                            int dato = datosFila.get(0).getValor();

                            for (int k = j - dato; k >= 0; k--) {

                                int elem = fila.get(k);

                                if (elem == 2) {
                                    fila.set(k, 0);
                                    cambio = true;
                                }
                            }

                            break;
                        }
                    }

                }

            }

            datos = restaurarDatos(backupDatos, datos);
            solucion = restaurarSolucion(backupSolucion, solucion);

            if (z == 0) {
                actualizarDatos(datos, solucion);
            }

        }

        solucion = transponerMatriz(solucion);

        actualizarDatos(datos, solucion);

        ArrayList res = new ArrayList();

        res.add(cambio);
        res.add(solucion);
        res.add(datos);

        return res;
    }

    /*
	Se verifica los espacios sin llenar de un fila, si ninguno
        de los datos de entrada que faltan por completar, pueden ser
        ubicados en este espacio entonces se concluye que el espacio 
        es muy pequeÃ±o, por ende la Ãºnica soluciÃ³n es completar el espacio
        con 0 indicando espacios en blanco.
     */
    private static ArrayList aplicarRegla6(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {

        boolean cambio = false;

        for (int z = 0; z < 2; z++) {

            if (z == 1) {
                solucion = transponerMatriz(solucion);
            }

            ArrayList<ArrayList<Dato>> backupDatos = clonarDatos(datos);
            ArrayList<ArrayList<Integer>> backupSolucion = clonarSolucion(solucion);

            datos = trimDatos(datos);
            solucion = trimSolucion(solucion);

            for (int i = 0; i < solucion.size(); i++) {

                ArrayList<Integer> fila = solucion.get(i);

                ArrayList<Dato> datosFila = null;
                if (z == 0) {
                    datosFila = datos.get(i);
                } else {
                    datosFila = datos.get(i + solucion.size());
                }

                int j = 0;
                while (j < fila.size()) {
                    int tamañoBrecha = 0;

                    int elemento = fila.get(j);

                    if (elemento == 2 && !anteriorEsUno(j, fila)) {
                        int k = j;

                        while (k < fila.size()) {

                            int elem = fila.get(k);

                            if (elem != 2) {
                                break;
                            }

                            tamañoBrecha++;
                            k++;
                        }

                        if (k >= fila.size() || fila.get(k) == 0) { // It was actually a gap

                            if (brechaPequeña(tamañoBrecha, datosFila)) {

                                for (int l = j; l < (j + tamañoBrecha); l++) {
                                    fila.set(l, 0);
                                }

                                cambio = true;
                            }
                        }

                        j += tamañoBrecha;

                    } else {
                        j++;
                    }

                }

            }

            datos = restaurarDatos(backupDatos, datos);
            solucion = restaurarSolucion(backupSolucion, solucion);

            if (z == 0) {
                actualizarDatos(datos, solucion);
            }

        }

        solucion = transponerMatriz(solucion);

        actualizarDatos(datos, solucion);

        ArrayList res = new ArrayList();

        res.add(cambio);
        res.add(solucion);
        res.add(datos);

        return res;
    }

    /*
	Si todas las entradas de una fila ya se encuentran en estado completado,
        se asume que la fila estÃ¡ terminada, por esto todos los datos que se
        encuentren aun sin cambiar de estado (2), se asumirÃ¡ que son espacios en 
        blanco (0).
     */
    private static ArrayList aplicarRegla7(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {

        boolean cambio = false;

        for (int z = 0; z < 2; z++) {

            if (z == 1) {
                solucion = transponerMatriz(solucion);
            }

            ArrayList<ArrayList<Dato>> backupDatos = clonarDatos(datos);
            ArrayList<ArrayList<Integer>> backupSolucion = clonarSolucion(solucion);

            datos = trimDatos(datos);
            solucion = trimSolucion(solucion);

            //Apply rule
            for (int i = 0; i < solucion.size(); i++) {

                ArrayList<Integer> fila = solucion.get(i);

                ArrayList<Dato> datosFila = null;

                if (z == 0) {
                    datosFila = datos.get(i);
                } else {
                    datosFila = datos.get(i + solucion.size());
                }

                if (datosFila.size() == 0) {

                    //Set as "0" the elements of the row (which are not "0" or "1")
                    for (int j = 0; j < fila.size(); j++) {

                        if (fila.get(j) == 2) {
                            fila.set(j, 0);
                            cambio = true;
                        }
                    }
                }

            }

            datos = restaurarDatos(backupDatos, datos);
            solucion = restaurarSolucion(backupSolucion, solucion);

            if (z == 0) {
                actualizarDatos(datos, solucion);
            }

        }

        solucion = transponerMatriz(solucion);

        actualizarDatos(datos, solucion);

        ArrayList res = new ArrayList();

        res.add(cambio);
        res.add(solucion);
        res.add(datos);

        return res;

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

    /*
    Este metodo se encarga de clonar la matriz solucion y retorna este resultado.
     */
    private static ArrayList<ArrayList<Integer>> clonarSolucion(ArrayList<ArrayList<Integer>> lista) {

        ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();

        for (ArrayList<Integer> l : lista) {

            ArrayList<Integer> aux = new ArrayList<Integer>();

            for (Integer i : l) {

                aux.add(i);
            }

            resultado.add(aux);
        }

        return resultado;
    }

    /*
    Este metodo se encarga de clonar la matriz de datos, retorna una copia exacta de la matriz entrante
     */
    private static ArrayList<ArrayList<Dato>> clonarDatos(ArrayList<ArrayList<Dato>> lista) {

        ArrayList<ArrayList<Dato>> resultado = new ArrayList<ArrayList<Dato>>();

        for (ArrayList<Dato> l : lista) {

            ArrayList<Dato> aux = new ArrayList<Dato>();

            for (Dato d : l) {

                aux.add(d.clonar());
            }

            resultado.add(aux);
        }

        return resultado;
    }

    /*
    Este metodo se encarga de verificar que la matriz solucion y los datos ingresados por el usuario
    sean coherentes, en caso tal de que no haya coincidencia, los actualiza.
     */
    private static void actualizarDatos(ArrayList<ArrayList<Dato>> datos, ArrayList<ArrayList<Integer>> solucion) {

        for (int z = 0; z < 2; z++) {

            if (z == 1) {
                solucion = transponerMatriz(solucion);
            }

            for (int i = 0; i < solucion.size(); i++) {

                ArrayList<Integer> fila = solucion.get(i);

                int contadorGrupos = 0;
                boolean celdaFunciona = false;
                int j = 0;
                while (j < fila.size()) {

                    Integer elemento = fila.get(j);

                    if (elemento == 0 && celdaFunciona) {

                        if (z == 0) {
                            datos.get(i).get(contadorGrupos).setCompletado(true);
                        } else {
                            datos.get(i + solucion.size()).get(contadorGrupos).setCompletado(true);
                        }
                        contadorGrupos++;
                        celdaFunciona = false;
                    } else if (elemento == 1) {
                        celdaFunciona = true;
                    } else if (elemento == 2) {
                        break;
                    }

                    j++;
                }

                contadorGrupos = 0;
                celdaFunciona = false;
                j = fila.size() - 1;

                while (j >= 0) {

                    Integer elemento = fila.get(j);

                    if (elemento == 0 && celdaFunciona) {

                        if (z == 0) {
                            datos.get(i).get(datos.get(i).size() - 1 - contadorGrupos).setCompletado(true);
                        } else {
                            datos.get(i + solucion.size()).get(datos.get(i + solucion.size()).size() - 1 - contadorGrupos).setCompletado(true);
                        }
                        contadorGrupos++;
                        celdaFunciona = false;
                    } else if (elemento == 1) {
                        celdaFunciona = true;
                    } else if (elemento == 2) {
                        break;
                    }

                    j--;
                }
            }
        }

        solucion = transponerMatriz(solucion);

    }

    /*
	Eliminas de la lista de datos ingresados por el usuario, los datos ya completados.
     */
    private static ArrayList<ArrayList<Dato>> trimDatos(ArrayList<ArrayList<Dato>> datos) {

        ArrayList<ArrayList<Dato>> resultado = new ArrayList<ArrayList<Dato>>();

        for (ArrayList<Dato> dList : datos) {

            ArrayList<Dato> aux = new ArrayList<Dato>();

            int contadorInferior = 0;
            int contadorSuperior = dList.size() - 1;

            while (contadorInferior < dList.size()) {

                if (dList.get(contadorInferior).isCompletado()) {
                    contadorInferior++;
                } else {
                    break;
                }
            }

            while (contadorSuperior >= 0) {

                if (dList.get(contadorSuperior).isCompletado()) {
                    contadorSuperior--;
                } else {
                    break;
                }
            }

            if (contadorInferior <= contadorSuperior) {

                for (int i = contadorInferior; i <= contadorSuperior; i++) {
                    aux.add(dList.get(i));
                }
            }
            resultado.add(aux);
        }

        return resultado;
    }

    /*
	Elimina los elementos completados de la matriz solucion, los que sean diferentes a 2.
     */
    private static ArrayList<ArrayList<Integer>> trimSolucion(ArrayList<ArrayList<Integer>> solucion) {

        ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();

        for (ArrayList<Integer> a : solucion) {

            ArrayList<Integer> aux = new ArrayList<Integer>();

            int contadorInferior = 0;
            int contadorSuperior = a.size() - 1;
            int contadorAuxilia = 0;
            int i = 0;

            while (i < a.size()) {

                Integer elemento = a.get(i);

                if (elemento == 1) {
                    contadorAuxilia++;
                } else if (elemento == 2) {
                    break;
                } else {
                    contadorInferior += contadorAuxilia + 1;
                    contadorAuxilia = 0;
                }
                i++;
            }

            contadorAuxilia = 0;
            i = a.size() - 1;

            while (i >= 0) {

                Integer elemento = a.get(i);

                if (elemento == 1) {
                    contadorAuxilia++;
                } else if (elemento == 2) {
                    break;
                } else {
                    contadorSuperior -= (contadorAuxilia + 1);
                    contadorAuxilia = 0;
                }
                i--;
            }

            if (contadorInferior <= contadorSuperior) {

                for (int j = contadorInferior; j <= contadorSuperior; j++) {
                    aux.add(a.get(j));
                }
            }
            resultado.add(aux);
        }

        return resultado;
    }

    /*
    Este metodo se encarga de restaurar la informacion principal de la matriz de datos
    igualandola al backup previamente almacenado.
     */
    private static ArrayList<ArrayList<Dato>> restaurarDatos(ArrayList<ArrayList<Dato>> backupDatos, ArrayList<ArrayList<Dato>> datos) {

        ArrayList<ArrayList<Dato>> resultado = new ArrayList<ArrayList<Dato>>();

        int i = 0;

        for (ArrayList<Dato> a : backupDatos) {

            ArrayList<Dato> aux = new ArrayList<Dato>();

            int contadorInferior = 0;
            int contadorSuperior = a.size() - 1;

            while (contadorInferior < a.size()) {

                if (a.get(contadorInferior).isCompletado()) {
                    contadorInferior++;
                } else {
                    break;
                }
            }

            while (contadorSuperior >= 0) {

                if (a.get(contadorSuperior).isCompletado()) {
                    contadorSuperior--;
                } else {
                    break;
                }
            }

            if (contadorInferior <= contadorSuperior) {

                for (int j = 0; j < contadorInferior; j++) {
                    aux.add(a.get(j));
                }
                for (int j = 0; j < contadorSuperior - contadorInferior + 1; j++) {
                    aux.add(datos.get(i).get(j));
                }
                for (int j = contadorSuperior + 1; j < a.size(); j++) {
                    aux.add(a.get(j));
                }
            } else {
                aux = a;
            }

            resultado.add(aux);

            i++;
        }

        return resultado;
    }

    /*
    Este metodo se encarga de restaurar la informacion principal de la matriz solucion
    igualandola al backup previamente almacenado.
     */
    private static ArrayList<ArrayList<Integer>> restaurarSolucion(ArrayList<ArrayList<Integer>> backupSolucion, ArrayList<ArrayList<Integer>> solucion) {

        ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();

        int k = 0;

        for (ArrayList<Integer> a : backupSolucion) {

            ArrayList<Integer> aux = new ArrayList<Integer>();

            int contadorInferior = 0;
            int contadorSuperior = a.size() - 1;
            int contadorAuxiliar = 0;
            int i = 0;

            while (i < a.size()) {

                Integer elemento = a.get(i);

                if (elemento == 1) {
                    contadorAuxiliar++;
                } else if (elemento == 2) {
                    break;
                } else {
                    contadorInferior += contadorAuxiliar + 1;
                    contadorAuxiliar = 0;
                }
                i++;
            }

            contadorAuxiliar = 0;
            i = a.size() - 1;

            while (i >= 0) {

                Integer elemento = a.get(i);

                if (elemento == 1) {
                    contadorAuxiliar++;
                } else if (elemento == 2) {
                    break;
                } else {
                    contadorSuperior -= (contadorAuxiliar + 1);
                    contadorAuxiliar = 0;
                }
                i--;
            }

            if (contadorInferior <= contadorSuperior) {

                for (int j = 0; j < contadorInferior; j++) {
                    aux.add(a.get(j));
                }
                for (int j = 0; j < contadorSuperior - contadorInferior + 1; j++) {
                    aux.add(solucion.get(k).get(j));
                }
                for (int j = contadorSuperior + 1; j < a.size(); j++) {
                    aux.add(a.get(j));
                }

            } else {
                aux = a;
            }

            resultado.add(aux);

            k++;
        }

        return resultado;
    }

    /*
	Este metodo se encarga de verificar si la brecha, previamente contada
        es lo suficientemente grande como para almacenar uno de los datos faltantes
        en caso tal de no serlo, sera considerada una brecha pequeÃ±a, 
        se retornara true en este caso.
     */
    private static boolean brechaPequeña(int brecha, ArrayList<Dato> datosFila) {

        for (Dato d : datosFila) {

            int valor = d.getValor();

            if (valor <= brecha) {
                return false;
            }
        }

        return true;
    }

    private static boolean anteriorEsUno(int i, ArrayList<Integer> fila) {

        while (i >= 0) {

            Integer element = fila.get(i);

            if (element == 1) {
                return true;
            }

            i--;
        }

        return false;
    }
}
