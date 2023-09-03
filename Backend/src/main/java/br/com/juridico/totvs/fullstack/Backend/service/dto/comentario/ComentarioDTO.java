package br.com.juridico.totvs.fullstack.Backend.service.dto.comentario;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;

public class ComentarioDTO {
    private Long id;
    private String nome;
    private String comentario;
    private String data;
    private PontoTuristico pontoTuristico;

    public ComentarioDTO(Long id, String nome, String comentario, String data, PontoTuristico pontoTuristico) {
        this.id = id;
        this.nome = nome;
        this.comentario = comentario;
        this.data = data;
        this.pontoTuristico = pontoTuristico;
    }

    public ComentarioDTO(Comentario comentario){
        this.id = comentario.getId();
        this.nome = comentario.getNome();
        this.comentario = comentario.getComentario();
        this.data = comentario.getData();
        this.pontoTuristico = comentario.getPontoTuristico();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PontoTuristico getPontoTuristico() {
        return pontoTuristico;
    }

    public void setPontoTuristico(PontoTuristico pontoTuristico) {
        this.pontoTuristico = pontoTuristico;
    }
}
