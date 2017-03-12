package br.edu.ifpb.ads.questao_06_node3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 12/03/2017, 04:47:23
 */
public class Node3 {

    public static void main(String[] args){
        System.out.println("Iniciando Node3 ...");
        File file = new File(System.getProperty("user.dir"));
        Path path = Paths.get(file.getParentFile() + "/disk.txt");
        String text = "Texto para ser escrito \n";
        update(path, text);
    }

    public static void update(Path path, String text) {
        System.out.println("Checando o arquivo existe e se ele pode ser escrito no momento ...");
        if (Files.exists(path) && Files.isWritable(path)) {
            try {
                System.out.println("Escrevendo no arquivo ...");
                Files.write(path, text.getBytes(), StandardOpenOption.APPEND);
                System.out.println("Escrita concluida!");
            } catch (IOException ex) {
                System.out.println("Erro ao tentar modificar o arquivo");
            }
        } else {
            System.out.println("O arquivo não podê ser modificado no momento");
        }
    }
}
