/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompactadorAv2;

/*
 * @author Luis Guilherme
 */
public class No {

    private Frase f;
    private No prox;
    private No ant;

    public No(Frase f) {
        this.f = f;
        this.prox = null;
        this.ant = null;
    }       //Construtor

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public Frase getF() {
        return f;
    }

    public void setF(Frase f) {
        this.f = f;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

}
