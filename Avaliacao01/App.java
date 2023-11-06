package Avaliacao01;

import Avaliacao01.Entidades.Perfil;
import Avaliacao01.Entidades.Postagem;
import Avaliacao01.Entidades.PostagemAvancada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class App {
    private static RedeSocial redeSocial = new RedeSocial();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        lerPerfisArquivo();
        lerPostagensArquivo();

        int opcao = -1;
        exibirLogo();
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|--------------------- Bem vindo!!! ---------------------|");
        do {
            System.out.println(menu());
            System.out.println("> Digite uma opção: ");
            opcao = scanner.nextInt();

            scanner.nextLine();
            meuCtrlL(20);
            exibirLogo();

            switch (opcao) {
                case 1:
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
                    curtirPostagem();
                    meuContinue(1);
                    break;
                case 7:
                    descurtirPostagem();
                    meuContinue(1);
                    break;
                case 8:
                    decrementarVisualizacoesRestantes();
                    meuContinue(1);
                    break;
                case 9:
                    exibirPostagensPorPerfil();
                    meuContinue(1);
                    break;
                case 10:
                    exibirPostagensPorHashtag();
                    meuContinue(1);
                    break;
                case 11:
                    exibirPostagensPopulares();
                    meuContinue(1);
                    break;
                case 12:
                    exibirHashtagsPopulares();
                    meuContinue(1);
                    break;
                case 13:
                    exibirPostagemMaisCurtida();
                    meuContinue(1);
                    break;
                case 14:
                    exibirPostagemMaisDescurtida();
                    meuContinue(1);
                    break;
                case 15:
                    exibirPostagemMaisVisualizacoesRestantes();
                    meuContinue(1);
                    break;
                case 0:
                    System.out.println("Tchau bb :)");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    meuCtrlL(1);
                    break;
            }
            meuCtrlL(20);
        } while (opcao != 0);
        salvarPerfisEmArquivo();
        salvarPostagensEmArquivo();
    }

    public static String menu(){
        // Funcionalidades além das solicitadas: exibirPerfis, postagemMaisCurtida, postagemMaisDescurtida, postagemMaisVisualizacoesRestantes
        return """
                |--------------------------------------------------------|
                | 1 - Incluir Perfil                                     |
                | 2 - Consultar Perfil                                   |
                | 3 - Exibir Perfis                                      |
                | 4 - Incluir Postagem                                   |
                | 5 - Consultar Postagem                                 |
                | 6 - Curtir Postagem                                    |
                | 7 - Descurtir Postagem                                 |
                | 8 - Decrementar Visualizações                          |
                | 9 - Exibir Postagens por Perfil                        |
                | 10 - Exibir Postagens por Hashtag                      |
                | 11 - Exibir Postagens populares                        |
                | 12 - Exibir Hashtags mais populares                    |
                | 13 - Exibir postagens mais curtidas                    |
                | 14 - Exibir postagens mais descurtidas                 |
                | 15 - Exibir postagens com mais visualizações restantes |
                | 0 - Sair                                               |
                |--------------------------------------------------------|""";
    }

    public static void incluirPerfil(){
        System.out.println("> Qual o nome do perfil?");
        String nome = scanner.nextLine().trim();
        System.out.println("> Qual o email do perfil?");
        String email = scanner.nextLine().trim();
        Perfil perfil = new Perfil(nome, email);
        if(redeSocial.incluirPerfil(perfil)){
            System.out.println("Conta incluída na rede social com sucesso!");
            System.out.printf("**(O id do perfil adicionado é %d)**\n", perfil.getId());
        } else {
            System.out.println("!!! Uma conta com algum(s) desses atributos já existe! Tente com novos atributos. !!!");
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
            System.out.println(perfil);
        }
    }

    public static void incluirPostagem(){
        System.out.println("> Qual o id do usuário autor da postagem? (Caso nao se lembre, use a opção 3)");
        int idPerfil = scanner.nextInt();
        scanner.nextLine();
        Perfil perfil = redeSocial.consultarPerfil(idPerfil);
        System.out.println("> Insira o texto da postagem: ");
        String texto = scanner.nextLine().trim();
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
            PostagemAvancada postagem = new PostagemAvancada(texto, perfil, hashtags, visualizacoes);
            if(redeSocial.incluirPostagem(postagem)){
                System.out.println("Postagem incluída com sucesso!");
                System.out.printf("**(O id da postagem adicionada é %d)**\n", postagem.getId());
            } else {
                System.out.println("!!! Postagem NÃO adicionada :( !!!");
            }
        } else if (tipo.equals("P")){
            Postagem postagem = new Postagem(texto, perfil);
            if(redeSocial.incluirPostagem(postagem)){
                System.out.println("Postagem incluída com sucesso!");
                System.out.printf("**(O id da postagem adicionada é %d)**\n", postagem.getId());
            } else {
                System.out.println("!!! Postagem NÃO adicionada :( !!!");
            }
        } else {
            System.out.println("!!! Tipo inválido! Postagem não será adicionada !!!");
        }
    }

    public static void consultarPostagem(){
        System.out.println("> Qual o id da postagem? ");
        int idPostagem = scanner.nextInt();
        Postagem postagem = redeSocial.consultarPostagem(idPostagem);
        if(postagem != null){
            System.out.println(postagem);
        } else {
            System.out.println("!!! Postagem não encontrada ou não possui mais visualizações restantes! !!!");
        }
    }

    public static void curtirPostagem(){
        System.out.println("> Qual o id da postagem a ser curtida?");
        int idPostagem = scanner.nextInt();
        scanner.nextLine();

        if(redeSocial.curtir(idPostagem)){
            System.out.println("Postagem curtida com sucesso!");
        } else {
            System.out.println("Não foi possível curtir essa postagem (não existe postagem com esse id)");
        }
    }
    public static void descurtirPostagem(){
        System.out.println("> Qual o id da postagem a ser descurtida?");
        int idPostagem = scanner.nextInt();
        scanner.nextLine();

        if(redeSocial.descurtir(idPostagem)){
            System.out.println("Postagem descurtida com sucesso!");
        } else {
            System.out.println("Não foi possível curtir essa postagem (não existe postagem com esse id)");
        }
    }

    public static void decrementarVisualizacoesRestantes(){
        System.out.println("> Qual o id da postagem a ter visualizações decrementadas?");
        int idPostagem = scanner.nextInt();
        scanner.nextLine();

        if(redeSocial.decrementrarVisualizacoesRestantes(idPostagem)){
            System.out.println("Visualizações Restantes foi decrementada com sucesso!");
        } else {
            System.out.println("Não foi possível decrementar, a postagem não possui mais visualizações restantes ou não é uma postagem avançada!");
        }
    }

    public static void exibirPostagensPorPerfil(){
        System.out.println("> Insira o id do perfil: ");
        int idPerfil = scanner.nextInt();
        scanner.nextLine();
        List<Postagem> postagens = redeSocial.exibirPostagensPorPerfil(idPerfil);
        if(postagens != null && !(postagens.isEmpty())){
            for (Postagem postagem:
                 postagens) {
                System.out.println(postagem + "\n");
            }
        }
        else {
            System.out.println("!!! Este perfil ainda não possui postagens ou simplesmente não existe! !!!");
        }
    }

    public static void exibirPostagensPorHashtag(){
        System.out.println("> Deseja procurar postagens que possuam qual hashtag?");
        String hashtag = scanner.nextLine();
        List<PostagemAvancada> postagensComHashtag = redeSocial.exibirPostagensPorHashtags(hashtag);
        if(postagensComHashtag.isEmpty()){
            System.out.println("!!! Nenhuma postagem com essa hashtag foi encontrada! !!!");
        } else {
            for (PostagemAvancada postagem:
                 postagensComHashtag) {
                System.out.println(postagem + "\n");
            }
        }
    }

    public static void exibirPostagensPopulares(){
        List<Postagem> postagensPopulares = redeSocial.exibirPostagensPopulares();
        if(postagensPopulares.isEmpty()){
            System.out.println("!!! Por enquanto não existem postagens populares! !!!");
        } else {
            for (Postagem postagem:
                 postagensPopulares) {
                System.out.println(postagem);
            }
        }
    }

    public static void exibirHashtagsPopulares() {
        List<String> hashtagPopulares = redeSocial.obterHashtagsMaisPopulares();
        System.out.println("Essas são as 3 hashtags mais populares: ");
        if(!(hashtagPopulares.isEmpty())){
            for (String hashtag:
                 hashtagPopulares) {
                System.out.println("#" + hashtag);
            }
        } else {
            System.out.println("!!! Ainda não existem hashtags mais populares !!!");
        }
    }

    public static void exibirPostagemMaisCurtida(){
        Postagem postagemMaisCurtida = redeSocial.exibirPostagemMaisCurtida();
        if(postagemMaisCurtida != null){
            System.out.println("A postagem mais curtida é a de id: " + postagemMaisCurtida.getId());
            System.out.println(postagemMaisCurtida);
        } else {
            System.out.println("!!! Ainda não existe uma postagem mais curtida ou ela não pode ser exibida !!!");
        }
    }

    public static void exibirPostagemMaisDescurtida() {
        Postagem postagemMaisDescurtida = redeSocial.obterPostagemMaisDescurtida();
        if (postagemMaisDescurtida != null) {
            System.out.println("A postagem mais curtida é a de id: " + postagemMaisDescurtida.getId());
            System.out.println(postagemMaisDescurtida);
        } else {
            System.out.println("!!! Ainda não existe uma postagem mais descurtida ou ela não pode ser exibida !!!");
        }
    }

    public static void exibirPostagemMaisVisualizacoesRestantes() {
        Postagem postagemMaisVisualizacoesRestantes = redeSocial.obterPostagemMaisVisualizacoesRestantes();
        if (postagemMaisVisualizacoesRestantes != null) {
            System.out.println("Postagem com mais visualizações restantes:");
            System.out.println(postagemMaisVisualizacoesRestantes);
        } else {
            System.out.println("Nenhuma postagem encontrada ou não é possível exibir postagens com mais visualizações restantes.");
        }
    }

    public static void salvarPerfisEmArquivo() throws IOException {
        File filePerfis = new File("Avaliacao01\\perfis.txt");
        FileWriter fileWriterPerfis = new FileWriter(filePerfis);

        for (Perfil perfil:
                redeSocial.perfisCadastrados()) {
            String conteudo = String.join("&&", perfil.getNome(), perfil.getEmail());
            fileWriterPerfis.write(conteudo + '\n');
        }
        fileWriterPerfis.close();
    }

    public static void salvarPostagensEmArquivo() throws IOException {
        File filePostagens = new File("Avaliacao01\\postagens.txt");
        FileWriter fileWriterPostagens = new FileWriter(filePostagens);

        for (Postagem postagem:
                redeSocial.postagensCadastradas()) {
                String conteudo = String.join("&&", String.valueOf(postagem.getPerfil().getId()),
                        postagem.getTexto(), String.valueOf(postagem.getCurtidas()),
                        String.valueOf(postagem.getDescurtidas()), postagem.getData().toString());
            if (!(postagem instanceof PostagemAvancada)){
                conteudo += "&&P\n";
            } else {
                String hashtags = "";
                int tamanho = ((PostagemAvancada) postagem).getHashtags().size();
                int count = 0;
                for (String hashtag:
                        ((PostagemAvancada) postagem).getHashtags()) {
                    if(count != tamanho - 1){
                        hashtags += hashtag + ",";
                    } else {
                        hashtags += hashtag;
                    }
                    count++;
                }
                conteudo += "&&PA&&" + ((PostagemAvancada) postagem).getVisualizacoesRestantes() + "&&" +
                        hashtags + '\n';
            }
            fileWriterPostagens.write(conteudo);
        }
        fileWriterPostagens.close();

    }

    public static void lerPerfisArquivo() throws IOException {
        File filePerfis = new File("Avaliacao01\\perfis.txt");
        FileReader fileReaderPerfis = new FileReader(filePerfis);
        BufferedReader bufferedReaderPerfis = new BufferedReader(fileReaderPerfis);

        String linhaPerfis = bufferedReaderPerfis.readLine();
        while(linhaPerfis != null){
            String[] partes = linhaPerfis.split("&&");
            String nome = partes[0];
            String email = partes[1];
            Perfil perfil = new Perfil(nome, email);
            redeSocial.incluirPerfil(perfil);
            linhaPerfis = bufferedReaderPerfis.readLine();
        }
        bufferedReaderPerfis.close();
    }

    public static void lerPostagensArquivo() throws IOException {
        File filePostagens = new File("Avaliacao01\\postagens.txt");
        FileReader fileReaderPostagens = new FileReader(filePostagens);
        BufferedReader bufferedReaderPostagens = new BufferedReader(fileReaderPostagens);

        String linhaPostagens = bufferedReaderPostagens.readLine();
        while(linhaPostagens != null){
            String[] partes = linhaPostagens.split("&&");
            int idPerfil = Integer.parseInt(partes[0]);
            Perfil perfil = redeSocial.consultarPerfil(idPerfil);
            String texto = partes[1];
            int curtidas = Integer.parseInt(partes[2]);
            int descurtidas = Integer.parseInt(partes[3]);
            LocalDate data = LocalDate.parse(partes[4]);
            String tipo = partes[5];
            if(tipo.equals("P")){
                Postagem postagem = new Postagem(texto, perfil, curtidas, descurtidas, data);
                redeSocial.incluirPostagem(postagem);
            } else {
                int visualizacoesRestantes = Integer.parseInt(partes[6]);
                List<String> hashtags = List.of(partes[7].split(","));
                PostagemAvancada postagemAvancada = new PostagemAvancada(texto, perfil, curtidas,
                        descurtidas, data, hashtags, visualizacoesRestantes);
                redeSocial.incluirPostagem(postagemAvancada);
            }
            linhaPostagens = bufferedReaderPostagens.readLine();
        }
        bufferedReaderPostagens.close();
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

    public static void exibirLogo(){
        System.out.println("""
                                __   __                                _\s
                                \\ \\ / /                               (_)
                                 \\ V /  __ _  ___  _ __ ___  _ __ ___  _\s
                                 /   \\ / _` |/ _ \\| '_ ` _ \\| '_ ` _ \\| |
                                / /^\\ \\ (_| | (_) | | | | | | | | | | | |
                                \\/   \\/\\__, |\\___/|_| |_| |_|_| |_| |_|_|
                                        __/ |                           \s
                                       |___/                            \s
                            """);
    }
}
