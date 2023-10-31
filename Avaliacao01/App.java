package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
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
        System.out.println("-------------------- Bem vindo! --------------------");
        do {
            System.out.println(menu());
            System.out.println("Digite uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    incluirPerfil();
                    break;
                case 2:
                    consultarPerfil();
                    break;
                case 3:
                    exibirPerfis();
                    break;
                case 4:
                    incluirPostagem();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            System.out.println("----------------------------------------------------");
        } while (opcao != 0);

    }

    public static String menu(){
        return """
                1 - Incluir Perfil
                2 - Consultar Perfil
                3 - Exibir Perfis
                4 - Incluir Postagem
                5 - Consultar Postagem
                6 - Curtir Postagem
                7 - Descurtir Postagem
                8 - Decrementar Visualizacoes
                9 - Exibir postagens por Perfil
                10 - Exibir postagens por Hashtag
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
        scanner.nextLine();
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
            redeSocial.incluirPostagem(postagem);
        } else{
            Postagem postagem = new Postagem(texto, perfil, data);
            redeSocial.incluirPostagem(postagem);
        }
    }
}
