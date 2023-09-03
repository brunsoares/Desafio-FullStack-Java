package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.comentario.ComentarioService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoDTO;
import br.com.juridico.totvs.fullstack.Backend.service.ponto_turistico.PontoTuristicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        PontoTuristico pontoTuristico = comentarioCreateUpdateDTO.getPontoTuristico();
        PontoTuristicoDTO pontoTuristicoDTO = this.pontoTuristicoService.getPontoTuristicoById(pontoTuristico.getId());
        if(pontoTuristicoDTO != null){
            List<Comentario> listComentario = pontoTuristicoDTO.getComentarios();
            listComentario.add(new Comentario(this.service.getNewId(),
                    comentarioCreateUpdateDTO.getNome(),
                    comentarioCreateUpdateDTO.getComentario(),
                    comentarioCreateUpdateDTO.getData(),
                    pontoTuristico));
            pontoTuristicoDTO.setComentarios(listComentario);
            PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO = new PontoTuristicoCreateUpdateDTO(pontoTuristicoDTO);
            this.pontoTuristicoService.update(pontoTuristicoDTO.getId(), pontoTuristicoCreateUpdateDTO);
        }
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
        ComentarioDTO comentarioDTO = this.service.getComentarioById(idComentario);
        PontoTuristico pontoTuristico = comentarioDTO.getPontoTuristico();
        PontoTuristicoDTO pontoTuristicoDTO = this.pontoTuristicoService.getPontoTuristicoById(pontoTuristico.getId());
        if(pontoTuristicoDTO != null){
            List<Comentario> listComentario = pontoTuristicoDTO.getComentarios();
            listComentario.removeIf(c -> c.getId().equals(idComentario));
            pontoTuristicoDTO.setComentarios(listComentario);
            PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO = new PontoTuristicoCreateUpdateDTO(pontoTuristicoDTO);
            this.pontoTuristicoService.update(pontoTuristicoDTO.getId(), pontoTuristicoCreateUpdateDTO);
        }

        this.service.delete(idComentario);
    }

    @PutMapping("{idComentario}")
    public ComentarioDTO update(@PathVariable Long idComentario,
                          @RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO ){
        PontoTuristico pontoTuristico = comentarioCreateUpdateDTO.getPontoTuristico();
        PontoTuristicoDTO pontoTuristicoDTO = this.pontoTuristicoService.getPontoTuristicoById(pontoTuristico.getId());
        if(pontoTuristicoDTO != null){
            List<Comentario> listComentario = pontoTuristicoDTO.getComentarios();
            listComentario.removeIf(c -> c.getId().equals(idComentario));
            listComentario.add(new Comentario(idComentario,
                    comentarioCreateUpdateDTO.getNome(),
                    comentarioCreateUpdateDTO.getComentario(),
                    comentarioCreateUpdateDTO.getData(),
                    pontoTuristico));
            pontoTuristicoDTO.setComentarios(listComentario);
            PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO = new PontoTuristicoCreateUpdateDTO(pontoTuristicoDTO);
            this.pontoTuristicoService.update(pontoTuristicoDTO.getId(), pontoTuristicoCreateUpdateDTO);
        }

        return this.service.update(idComentario, comentarioCreateUpdateDTO);
    }
}
