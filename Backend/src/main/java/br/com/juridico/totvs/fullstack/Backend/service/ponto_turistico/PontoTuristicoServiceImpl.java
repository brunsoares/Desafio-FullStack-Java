package br.com.juridico.totvs.fullstack.Backend.service.ponto_turistico;

import br.com.juridico.totvs.fullstack.Backend.domain.Pais;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.pais.PaisDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PontoTuristicoServiceImpl implements PontoTuristicoService {
    List<PontoTuristico> listPontoTuristico;

    PontoTuristicoServiceImpl(){
        this.listPontoTuristico = new ArrayList<>();
    }

    @Override
    public PontoTuristicoDTO create(PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO) {
        PontoTuristico pontoTuristico = new PontoTuristico(
                this.getNewId(),
                pontoTuristicoCreateUpdateDTO.getPais(),
                pontoTuristicoCreateUpdateDTO.getCidade(),
                pontoTuristicoCreateUpdateDTO.getNome(),
                pontoTuristicoCreateUpdateDTO.getMelhorEstacao(),
                new ArrayList<>());

        this.listPontoTuristico.add(pontoTuristico);

        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public PontoTuristicoDTO update(Long id, PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoComId(id);
        int index = this.listPontoTuristico.indexOf(pontoTuristico);
        if (pontoTuristico == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND); }

        pontoTuristico.setPais(pontoTuristicoCreateUpdateDTO.getPais());
        pontoTuristico.setCidade(pontoTuristicoCreateUpdateDTO.getCidade());
        pontoTuristico.setNome(pontoTuristicoCreateUpdateDTO.getNome());
        pontoTuristico.setMelhorEstacao(pontoTuristicoCreateUpdateDTO.getMelhorEstacao());
        pontoTuristico.setComentarios(pontoTuristicoCreateUpdateDTO.getComentarios());

        this.listPontoTuristico.set(index, pontoTuristico);
        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public void delete(Long id) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoComId(id);
        this.listPontoTuristico.remove(pontoTuristico);
    }

    @Override
    public PontoTuristicoDTO getPontoTuristicoById(Long id) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoComId(id);
        if (pontoTuristico == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ponto turístico não cadastrado!"); }
        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public List<PontoTuristicoDTO> getPontoTuristicoByPais(PaisDTO pais) {
        return this.listPontoTuristico.stream()
                .filter(x -> x.getPais().getNome().equalsIgnoreCase(pais.getNome()))
                .map(PontoTuristicoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PontoTuristicoDTO> getPontoTuristicoByCidade(String cidade) {
        return this.listPontoTuristico.stream()
                .filter(x -> x.getCidade().equalsIgnoreCase(cidade))
                .map(PontoTuristicoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PontoTuristicoDTO> getAllPontoTuristico() {
        return this.listPontoTuristico.stream()
                .map(PontoTuristicoDTO::new)
                .collect(Collectors.toList());
    }

    private Long getNewId(){
        if (this.listPontoTuristico.size() > 0){
            return this.listPontoTuristico.stream().max(Comparator
                            .comparingDouble(PontoTuristico::getId))
                    .get()
                    .getId()+1;
        } else {
            return 1L;
        }
    }

    private PontoTuristico getPontoTuristicoComId(Long id){
        return this.listPontoTuristico.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

}
