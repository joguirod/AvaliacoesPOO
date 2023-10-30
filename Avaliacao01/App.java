package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.PostagemAvancada;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class App {
    private static RedeSocial redeSocial = new RedeSocial();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        int opcao = -1;
        do {
            switch (opcao) {
                case 1:
                    incluirPerfil();
                    break;
                case 2:
                    consultarPerfil();
                    break;
                case 3:
                    exibirPerfis();
                case 4:
                    incluirPostagem();
                    break;
            }
        } while (opcao != 0);

    }

    public String menu(){
        return """
                1 - Incluir Perfil
                2 - Consultar Perfil
                3 - Exibir Perfis
                4 - Incluir Postagem
                4 - Consultar Postagem
                5 - Curtir Postagem
                6 - Descurtir Postagem
                7 - Decrementar Visualizacoes
                8 - Exibir postagens por Perfil
                9 - Exibir postagens por Hashtag
                0 - Sair
                """;
    }

    public static void incluirPerfil(){
        System.out.println("> Qual o nome do perfil?");
        String nome = scanner.nextLine();
        System.out.println("> Qual o email do perfil?");
        String email = scanner.nextLine();
        Perfil perfil = new Perfil(nome, email);
        redeSocial.incluirPerfil(perfil);
    }

    public static void consultarPerfil(){
        System.out.println("Qual o id do perfil a ser consultado?");
        int id = scanner.nextInt();
        System.out.println("Perfil desejado: " + redeSocial.consultarPerfil(id).toString());
    }

    public static void exibirPerfis(){
        for (Perfil perfil:
                redeSocial.perfisCadastrados()) {
            System.out.println(perfil.toString());
        }
    }

    public static void incluirPostagem(){
        System.out.println("Qual o id do usuário autor da postagem? (Caso nao se lembre, use a opção 3");
        int id = scanner.nextInt();
        Perfil perfil = redeSocial.consultarPerfil(id);
        System.out.println("Insira o texto da postagem: ");
        String texto = scanner.nextLine();
        System.out.println("Insira a data da postagem no formato yyyy-MM-dd: ");
        String data = scanner.nextLine();
        System.out.println("Qual o tipo da postagem? \n\tP - postagem normal\tPA - postagem avançada");
        String tipo = scanner.nextLine();
        if (tipo.equals("PA")) {
            System.out.println("Quantas visualizações a publicação pode ter?");
            int visualizacoes = scanner.nextInt();
            System.out.println("Quantas hashtags tem a postagem?");
            int n = scanner.nextInt();
            List<String> hashtags = new ArrayList<String>();
            for (int count = 0; count < n; count++) {
                System.out.println("Escreva a hashtag: ");
                String hashtag = scanner.nextLine();
                hashtags.add(hashtag);
            }
            PostagemAvancada postagem = new PostagemAvancada(texto, perfil, data, hashtags, visualizacoes);
        }
    }
}
