/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes1;

/**
 *
 * @author Lenovo
 */
class Dato {

    private int valor;
    private boolean completado;

    public Dato(int valor, boolean completado) {
        this.valor = valor;
        this.completado = completado;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @return the completado
     */
    public boolean isCompletado() {
        return completado;
    }
    
    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @param completado the completado to set
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public Dato clonar() {
        return (new Dato(this.getValor(), this.isCompletado()));
    }
}
