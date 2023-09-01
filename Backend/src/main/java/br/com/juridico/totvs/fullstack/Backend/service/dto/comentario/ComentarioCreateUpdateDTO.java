package br.com.juridico.totvs.fullstack.Backend.service.dto.comentario;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;

import java.util.Date;

public class ComentarioCreateUpdateDTO {
    private String nome;
    private String comentario;
    private Date data;
    private PontoTuristico pontoTuristico;

    public ComentarioCreateUpdateDTO(){

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public PontoTuristico getPontoTuristico() {
        return pontoTuristico;
    }

    public void setPontoTuristico(PontoTuristico pontoTuristico) {
        this.pontoTuristico = pontoTuristico;
    }
}
