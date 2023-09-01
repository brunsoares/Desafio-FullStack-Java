package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.service.dto.pais.PaisDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoDTO;
import br.com.juridico.totvs.fullstack.Backend.service.pais.PaisService;
import br.com.juridico.totvs.fullstack.Backend.service.ponto_turistico.PontoTuristicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/ponto-turistico")
public class PontoTuristicoController {
    private final PontoTuristicoService service;
    private final PaisService paisService;

    public PontoTuristicoController(PontoTuristicoService pontoTuristicoService, PaisService paisService){
        this.service = pontoTuristicoService;
        this.paisService = paisService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public PontoTuristicoDTO create(@RequestBody PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO){
        return this.service.create(pontoTuristicoCreateUpdateDTO);
    }

    @GetMapping
    public List<PontoTuristicoDTO> getAll(){
        return this.service.getAllPontoTuristico();
    }

    @GetMapping("{idPais}/pais")
    public List<PontoTuristicoDTO> getPontoTuristicoByPais(@PathVariable Long idPais){
        PaisDTO paisDTO = paisService.getPaisbyId(idPais);
        return this.service.getPontoTuristicoByPais(paisDTO);
    }

    @GetMapping("{cidade}/cidade")
    public List<PontoTuristicoDTO> getPontoTuristicoByCidade(@PathVariable String cidade){
        return this.service.getPontoTuristicoByCidade(cidade);
    }

    @GetMapping("{idPontoTuristico}")
    public PontoTuristicoDTO getPontoTuristicoByID(@PathVariable Long idPontoTuristico){
        return this.service.getPontoTuristicoById(idPontoTuristico);
    }

    @DeleteMapping("{idPontoTuristico}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idPontoTuristico){
        this.service.delete(idPontoTuristico);
    }

    @PutMapping("{idPontoTuristico}")
    public PontoTuristicoDTO update(@PathVariable Long idPontoTuristico,
                          @RequestBody PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO ){
        return this.service.update(idPontoTuristico, pontoTuristicoCreateUpdateDTO);
    }
}
