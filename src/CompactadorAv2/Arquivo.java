package CompactadorAv2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * @author Luis Guilherme
 */
public class Arquivo {

    private No primeiro;
    private No ultimo;

    public Arquivo() {
        this.primeiro = null;
        this.ultimo = null;
    }       //Construtor

    public No getUltimo() {
        return ultimo;
    }

    public void setUltimo(No ultimo) {
        this.ultimo = ultimo;
    }

    public No getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(No primeiro) {
        this.primeiro = primeiro;
    }

    //Toda leitura do arquivo
    public static String Read(String Caminho) {
        String conteudo = "";
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";
            try {
                linha = lerArq.readLine();
                while (linha != null) {
                    conteudo += linha + "\n";
                    linha = lerArq.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
    }

    //Processo do arquivo de saída
    public static boolean Write(String caminho, String texto) {
        try {
            FileWriter saida = new FileWriter(caminho);
            PrintWriter gravarSaida = new PrintWriter(saida);
            gravarSaida.println(texto);
            gravarSaida.flush();
            gravarSaida.close();
            saida.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Inserir na fila
    public void inserir(Frase f) {
        No novo = new No(f);
        if (this.primeiro == null) {     //Checa se está vazio e insere
            this.primeiro = novo;
            this.ultimo = novo;
        //Checa a "colocação" na fila 
        } else if (this.primeiro.getF().getQuant() >= f.getQuant()) {
            novo.setProx(this.primeiro);
            this.primeiro.setAnt(novo);
            this.primeiro = novo;
        } else if (f.getQuant() >= this.ultimo.getF().getQuant()) {
            this.ultimo.setProx(novo);
            novo.setAnt(this.ultimo);
            this.ultimo = novo;
        } else {
            No atual = this.primeiro;
            while (atual != null && novo.getF().getQuant() >= atual.getF().getQuant()) {
                atual = atual.getProx();
            }
            novo.setProx(atual);
            novo.setAnt(atual.getAnt());
            atual.getAnt().setProx(novo);
            atual = novo;
        }
    }

    //Checa se é vazia
    public boolean vazia() {
        return (this.primeiro == null);
    }

    //Imprimir lista
    public String imprimir() {
        String msg = "";
        if (vazia()) {
            msg = "A fila está vazia";
        } else {
            No atual = this.primeiro;
            while (atual != null) {
                msg += "\nA letra '" + atual.getF().getLetra() + "' repete " + atual.getF().getQuant() + " vez(es)";
                atual = atual.getProx();
            }
        }
        return msg;
    }
}
