package Avaliacao02.Interfaces;

import Avaliacao02.Entidades.Perfil;
import Avaliacao02.Entidades.Postagem;
import Avaliacao02.Entidades.PostagemAvancada;
import Avaliacao02.Exceptions.*;
import Avaliacao02.Repositorios.RepositorioDePerfisArrayList;
import Avaliacao02.Repositorios.RepositorioDePostagensArrayList;

import java.util.List;

public interface IRedeSocial {
    RepositorioDePerfisArrayList repositorioDePerfis = new RepositorioDePerfisArrayList();

    RepositorioDePostagensArrayList repositorioDePostagens = new RepositorioDePostagensArrayList();

    public void incluirPerfil(Perfil perfil) throws ContaJaExiste;

    public Perfil consultarPerfil(int id) throws ContaNaoEncontrada;

    public List<Perfil> perfisCadastrados();

    public List<Postagem> postagensCadastradas();

    public void incluirPostagem(Postagem postagem) throws PostagemExistente;

    public Postagem consultarPostagem(int id) throws PostagemNaoExiste, PostagemNaoPodeExibir;

    public List<Postagem> consultarPostagens(int id, String texto, Perfil perfil);

    public List<PostagemAvancada> consultarPostagens(int id, String texto, Perfil perfil, String hashtag);

    public void curtir(int idPostagem) throws PostagemNaoExiste;

    public void descurtir(int idPostagem) throws PostagemNaoExiste;

    public void decrementrarVisualizacoesRestantes(int idPostagem) throws PostagemNaoExiste, PostagemNaoPodeExibir;

    public List<Postagem> exibirPostagensPorPerfil(int idPerfil) throws ContaNaoEncontrada;

    public List<PostagemAvancada> exibirPostagensPorHashtags(String hashtag);

    public List<String> obterHashtagsMaisPopulares();

    public List<Postagem> exibirPostagensPopulares();

    public Postagem exibirPostagemMaisCurtida();

    public Postagem obterPostagemMaisDescurtida();

    public Postagem obterPostagemMaisVisualizacoesRestantes();
}
