package CompactadorAv2;

import java.util.Scanner;

/*
 * @author Luis Guilherme
 */
public class Main {

    public static void main(String[] args) {
        Scanner l = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo com .txt");
        String entrada = l.next();
        Arquivo arquivo = new Arquivo();

        //Leitura do arquivo txt
        String texto = Arquivo.Read(entrada);
        if (texto.isEmpty()) {
            System.out.println("Erro ao ler o arquivo!");
        } else {
            String s = texto;
            String v = "";
            Frase f;
            int i, j, cont = 0;
            for (i = 0; i < s.length(); i++) {
                f = new Frase();
                for (j = 0; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        cont++;
                    }
                }
                char c = s.charAt(i);
                if (c >= 'A' && c <= 'z' && !v.contains("" + c)) {
                    v = v + c;
                    //Inserindo na fila
                    f.setLetra(Character.toString(c));
                    f.setQuant(cont);
                    arquivo.inserir(f);
                }
                cont = 0;
            }
            //Exibe a fila
            System.out.println("Fila: " + arquivo.imprimir());
            //Saída de arquivo txt
            String saida = arquivo.imprimir();
            if (Arquivo.Write("saida.txt", saida)) {
                System.out.println("Arquivo salvo com sucesso!");
            } else {
                System.out.println("Erro ao salvar o arquivo!");
            }
        }
    }
}
