package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.service.comentario.ComentarioService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoDTO;
import br.com.juridico.totvs.fullstack.Backend.service.ponto_turistico.PontoTuristicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/comentario")
public class ComentarioController {
    private final ComentarioService service;
    private final PontoTuristicoService pontoTuristicoService;

    public ComentarioController(ComentarioService comentarioService, PontoTuristicoService pontoTuristicoService){
        this.service = comentarioService;
        this.pontoTuristicoService = pontoTuristicoService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public ComentarioDTO create(@RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO){
        return this.service.create(comentarioCreateUpdateDTO);
    }

    @GetMapping
    public List<ComentarioDTO> getAll(){
        return this.service.getAllComentarios();
    }

    @GetMapping("{idPontoTuristico}/ponto-turistico")
    public List<ComentarioDTO> getComentarioByPontoTuristico(@PathVariable Long idPontoTuristico){
        PontoTuristicoDTO pontoTuristicoDTO = pontoTuristicoService.getPontoTuristicoById(idPontoTuristico);
        return this.service.getComentarioByPontoTuristico(pontoTuristicoDTO);
    }

    @GetMapping("{idComentario}")
    public ComentarioDTO getComentarioById(@PathVariable Long idComentario){
        return this.service.getComentarioById(idComentario);
    }

    @DeleteMapping("{idComentario}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idComentario){
        this.service.delete(idComentario);
    }

    @PutMapping("{idComentario}")
    public ComentarioDTO update(@PathVariable Long idComentario,
                          @RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO ){
        return this.service.update(idComentario, comentarioCreateUpdateDTO);
    }
}
