package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
import Avaliacao01.Entidades.PostagemAvancada;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class App {
    private static RedeSocial redeSocial = new RedeSocial();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int opcao = -1;
        System.out.println("|---------------------------------------------------|");
        System.out.println("|------------------- Bem vindo!! -------------------|");
        do {
            System.out.println(menu());
            System.out.println("> Digite uma opção: ");
            opcao = scanner.nextInt();

            scanner.nextLine();
            meuCtrlL(20);

            switch (opcao) {
                case 1:
                    System.out.println("""
                                  X  X      XXXX
                                   X  X   XXXX
                                    X  XXXXXX
                                     X  X            Inscreva-se hoje!
                                  XXXXX  X
                                 XXXX  X  X
                               XXXX     X  X 
                            """);
                    incluirPerfil();
                    meuContinue(1);
                    break;
                case 2:
                    consultarPerfil();
                    meuContinue(2);
                    break;
                case 3:
                    exibirPerfis();
                    meuContinue(1);
                    break;
                case 4:
                    incluirPostagem();
                    meuContinue(1);
                    break;
                case 5:
                    consultarPostagem();
                    meuContinue(2);
                    break;
                case 6:
                    salvarEmArquivo();
                    break;
                case 9:
                    exibirPostagensPorPerfil();
                    meuContinue(1);
                    break;
                default:
                    if(opcao != 0){
                        System.out.println("Opção inválida!");
                    }
                    break;
            }
            meuCtrlL(20);
        } while (opcao != 0);
        System.out.println("Tchau bb :)");
    }

    public static String menu(){
        return """
                |---------------------------------------------------|
                | 1 - Incluir Perfil                                |
                | 2 - Consultar Perfil                              |
                | 3 - Exibir Perfis                                 |
                | 4 - Incluir Postagem                              |
                | 5 - Consultar Postagem                            |
                | 6 - Curtir Postagem                               |
                | 7 - Descurtir Postagem                            |
                | 8 - Decrementar Visualizações                     |
                | 9 - Exibir postagens por Perfil                   |
                | 10 - Exibir postagens por Hashtag                 |
                | 0 - Sair                                          |
                |---------------------------------------------------|""";
    }

    public static void incluirPerfil(){
        System.out.println("> Qual o id do perfil?");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("> Qual o nome do perfil?");
        String nome = scanner.nextLine().trim();
        System.out.println("> Qual o email do perfil?");
        String email = scanner.nextLine().trim();
        Perfil perfil = new Perfil(nome, email, id);
        if(redeSocial.incluirPerfil(perfil)){
            System.out.println("Conta incluída na rede social com sucesso!");
        } else {
            System.out.println("Uma conta com esses atributos já existe! Tente com novos atributos.");
        }
    }

    public static void consultarPerfil(){
        System.out.println("> Qual o id do perfil a ser consultado?");
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
        System.out.println("> Qual o id do usuário autor da postagem? (Caso nao se lembre, use a opção 3)");
        int idPerfil = scanner.nextInt();
        scanner.nextLine();
        Perfil perfil = redeSocial.consultarPerfil(idPerfil);

        System.out.println("> Insira o id da postagem: ");
        int idPostagem = scanner.nextInt();
        scanner.nextLine();
        System.out.println("> Insira o texto da postagem: ");
        String texto = scanner.nextLine().trim();
        System.out.println("> Insira a data da postagem no formato yyyy-MM-dd: ");
        String data = scanner.nextLine().trim();
        System.out.println("> Qual o tipo da postagem? \n\tP - postagem normal\tPA - postagem avançada");
        String tipo = scanner.nextLine().trim();
        if (tipo.equals("PA")) {
            System.out.println("> Quantas visualizações a publicação pode ter?");
            int visualizacoes = scanner.nextInt();
            System.out.println("> Quantas hashtags tem a postagem?");
            int n = scanner.nextInt();
            scanner.nextLine();
            List<String> hashtags = new ArrayList<>();
            for (int count = 0; count < n; count++) {
                System.out.println("> Escreva a hashtag: ");
                String hashtag = scanner.nextLine().trim();
                hashtags.add(hashtag);
            }
            PostagemAvancada postagem = new PostagemAvancada(texto, perfil, data, idPostagem, hashtags, visualizacoes);
            if(redeSocial.incluirPostagem(postagem)){
                perfil.adicionarPostagem(postagem);
                System.out.println("Postagem incluída com sucesso!");
            } else {
                System.out.println("Postagem NÃO adicionada :(");
            }
        } else if (tipo.equals("P")){
            Postagem postagem = new Postagem(texto, perfil, data, idPostagem);
            if(redeSocial.incluirPostagem(postagem)){
                perfil.adicionarPostagem(postagem);
                System.out.println("Postagem incluída com sucesso!");
            } else {
                System.out.println("Postagem NÃO adicionada :(");
            }
        } else {
            System.out.println("Tipo inválido! Postagem não será adicionada");
        }
    }

    public static void consultarPostagem(){
        System.out.println("> Qual o id da postagem? ");
        int idPostagem = scanner.nextInt();
        Postagem postagem = redeSocial.consultarPostagem(idPostagem);
        if(postagem != null){
            if(postagem instanceof PostagemAvancada){
                if(((PostagemAvancada) postagem).podeExibir()){
                    ((PostagemAvancada) postagem).decrementarVisualizacoes();
                    System.out.println(postagem);
                } else {
                    System.out.println("Postagem não possui mais visualizações restantes!");
                }
            } else {
                System.out.println(postagem);
            }
        } else {
            System.out.println("Postagem não encontrada!");
        }
    }









    public static void exibirPostagensPorPerfil(){
        System.out.println("> Insira o id do perfil: ");
        int idPerfil = scanner.nextInt();
        scanner.nextLine();

        List<Postagem> postagens = redeSocial.exibirPostagensPorPerfil(idPerfil);
        for (Postagem postagem:
             postagens) {
            System.out.println(postagem);
        }
    }

    public static void salvarEmArquivo() throws IOException {
        File filePerfis = new File("C:\\Users\\José Guilherme\\Documents\\AvaliacoesPOO\\Avaliacao01\\perfis.txt");
        File filePostagens = new File("C:\\Users\\José Guilherme\\Documents\\AvaliacoesPOO\\Avaliacao01\\postagens.txt");
        FileWriter fileWriterPerfis = new FileWriter(filePerfis);
        FileWriter fileWriterPostagens = new FileWriter(filePostagens);

        for (Perfil perfil:
                redeSocial.perfisCadastrados()) {
            String conteudo = String.join("&&", String.valueOf(perfil.getId()), perfil.getNome(), perfil.getEmail());
            fileWriterPerfis.write(conteudo + '\n');
        }
        fileWriterPerfis.close();

        for (Postagem postagem:
             redeSocial.postagensCadastradas()) {
            String conteudo = String.join("&&", String.valueOf(postagem.getPerfil().getId()),
                    String.valueOf(postagem.getId()), postagem.getTexto(), String.valueOf(postagem.getCurtidas()),
                    String.valueOf(postagem.getDescurtidas()), postagem.getData());
            if (!(postagem instanceof PostagemAvancada)){
                conteudo += "&&P\n";
            } else {
                conteudo += "&&PA&&" + ((PostagemAvancada) postagem).getVisualizacoesRestantes() + "&&" +
                        ((PostagemAvancada) postagem).getHashtags() + '\n';
            }
            fileWriterPostagens.write(conteudo);
        }
        fileWriterPostagens.close();

    }
    public static void meuCtrlL(int qtdPulos){
        System.out.println("\n".repeat(qtdPulos));
    }

    public static void meuContinue(int qtdTecladas){
        System.out.print("> Pressione <QualquerTecla> para continuar...");
        for (int i = 0; i < qtdTecladas; i++) {
            scanner.nextLine();
        }
    }
}
