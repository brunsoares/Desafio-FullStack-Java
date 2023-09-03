package br.com.juridico.totvs.fullstack.Backend.service.ponto_turistico;

import br.com.juridico.totvs.fullstack.Backend.domain.Pais;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.pais.PaisDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface PontoTuristicoService {
    List<PontoTuristico> listPais = new ArrayList<>();
    PontoTuristicoDTO create(PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO);
    PontoTuristicoDTO update(Long id, PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO);
    void delete(Long id);
    PontoTuristicoDTO getPontoTuristicoById(Long id);
    List<PontoTuristicoDTO> getPontoTuristicoByPais(PaisDTO pais);
    List<PontoTuristicoDTO> getPontoTuristicoByCidade(String cidade);
    List<PontoTuristicoDTO> getAllPontoTuristico();
}
