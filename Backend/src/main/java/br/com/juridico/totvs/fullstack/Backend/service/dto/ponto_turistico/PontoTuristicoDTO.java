package br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.domain.Pais;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;

import java.util.List;

public class PontoTuristicoDTO {
    private Long id;
    private Pais pais;
    private String cidade;
    private String nome;
    private String melhorEstacao;
    private List<Comentario> comentarios;


    public PontoTuristicoDTO(Long id, Pais pais, String cidade, String nome, String melhorEstacao, List<Comentario> comentarios) {
        this.id = id;
        this.pais = pais;
        this.cidade = cidade;
        this.nome = nome;
        this.melhorEstacao = melhorEstacao;
        this.comentarios = comentarios;
    }

    public PontoTuristicoDTO(PontoTuristico pontoTuristico){
        this.id = pontoTuristico.getId();
        this.pais = pontoTuristico.getPais();
        this.cidade = pontoTuristico.getCidade();
        this.nome = pontoTuristico.getNome();
        this.melhorEstacao = pontoTuristico.getMelhorEstacao();
        this.comentarios = pontoTuristico.getComentarios();
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
