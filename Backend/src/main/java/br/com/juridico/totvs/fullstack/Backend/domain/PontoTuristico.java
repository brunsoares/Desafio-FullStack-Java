package br.com.juridico.totvs.fullstack.Backend.domain;

import java.util.List;

public class PontoTuristico {

    private Long id;
    private Pais pais;
    private String cidade;
    private String nome;
    private String melhorEstacao;
    private List<Comentario> comentarios;


    public PontoTuristico(Long id, Pais pais, String cidade, String nome, String melhorEstacao, List<Comentario> comentarios) {
        this.id = id;
        this.pais = pais;
        this.cidade = cidade;
        this.nome = nome;
        this.melhorEstacao = melhorEstacao;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMelhorEstacao() {
        return melhorEstacao;
    }

    public void setMelhorEstacao(String melhorEstacao) {
        this.melhorEstacao = melhorEstacao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
