
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 */
public class Pratica72 {
    static final String EXTENSAO_IN = ".txt";
    static final String EXTENSAO_OUT = ".csv";

    public static void main(String[] args) {
        HashMap<String, Integer> listaPalavras = new HashMap<>();
        Scanner lerTeclado = new Scanner(System.in);
        BufferedReader lerTxt;
        BufferedWriter gravarTxt;
        String palavra, caminhoIn, caminhoOut, arqNome, linha;
        
        while(true){
            try{
                System.out.println("Digite o caminho do arquivo de texto(*.txt):");
                caminhoIn = lerTeclado.next();
                if(!caminhoIn.endsWith(EXTENSAO_IN)){
                    System.out.println("O arquivo deve ser do tipo *.txt");
                    continue;
                }
                lerTxt = new BufferedReader(new FileReader(caminhoIn));
                break;
            }catch(FileNotFoundException e){
                System.out.println("Arquivo não encontrado.");
            }
        }
        
        try {
            while((linha = lerTxt.readLine()) != null){
                linha = linha.trim();
                palavra = "";
                for(int i = 0; i < linha.length(); i++){
                    if(Character.isAlphabetic(linha.charAt(i))){
                        palavra += Character.toLowerCase(linha.charAt(i));
                    }else{
                        if(palavra != ""){
                            if(listaPalavras.containsKey(palavra))
                               listaPalavras.replace(palavra, listaPalavras.get(palavra)+1); 
                            else
                               listaPalavras.put(palavra,1);
                        }
                        palavra = "";
                    }
                }
            }
            lerTxt.close();
        } catch (IOException e) {
            Logger.getLogger(Pratica72.class.getName()).log(Level.SEVERE, null, e);
        }
        
        arqNome = "";
        for(int i = caminhoIn.length()-1; i > 0 && caminhoIn.charAt(i) != '\\' ;i--)
            arqNome = caminhoIn.charAt(i) + arqNome;
        arqNome = arqNome.substring(0, arqNome.length()-4);
        
        caminhoOut = caminhoIn.replace((arqNome+EXTENSAO_IN), "");
        caminhoOut = caminhoOut.concat(arqNome+"_out"+EXTENSAO_OUT);
        
        try {
            gravarTxt = new BufferedWriter(new FileWriter(caminhoOut));
            for(String p: listaPalavras.keySet()){
                gravarTxt.write("-"+p+", "+listaPalavras.get(p));
                gravarTxt.newLine();
            }
            gravarTxt.close();
        } catch (IOException e) {
            Logger.getLogger(Pratica72.class.getName()).log(Level.SEVERE, null, e);
        }
        
        while(true){
            System.out.println("Digite a palavra para busca.");
            palavra = lerTeclado.next().toLowerCase().trim();
            
            for(int i = 0; i < palavra.length(); i++)
                if(!Character.isAlphabetic(palavra.charAt(i))){
                    System.out.println("Digite apenas caracteres alfabeticos válidos.");
                    palavra = "";
                }
                    
            if(palavra == "")
                continue;
            
            if(listaPalavras.containsKey(palavra))
                System.out.println("A palavra \""+palavra+"\" ocorre "+listaPalavras.get(palavra)+" vezes.");
            else
                System.out.println("Palavra não encontrada.");
        }

    }
}
