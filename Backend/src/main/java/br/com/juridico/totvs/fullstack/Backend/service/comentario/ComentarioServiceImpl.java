package br.com.juridico.totvs.fullstack.Backend.service.comentario;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioDTO;
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
public class ComentarioServiceImpl implements ComentarioService {
    List<Comentario> listComentario;

    ComentarioServiceImpl(){
        this.listComentario = new ArrayList<>();
    }

    @Override
    public ComentarioDTO create(ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
        Comentario novoComentario = new Comentario(
                this.getNewId(),
                comentarioCreateUpdateDTO.getNome(),
                comentarioCreateUpdateDTO.getComentario(),
                comentarioCreateUpdateDTO.getData(),
                comentarioCreateUpdateDTO.getPontoTuristico());

        this.listComentario.add(novoComentario);

        return new ComentarioDTO(novoComentario);
    }

    @Override
    public ComentarioDTO update(Long id, ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
        Comentario comentario = this.getComentarioComId(id);
        int index = this.listComentario.indexOf(comentario);
        if (comentario == null){  throw new ResponseStatusException(HttpStatus.NOT_FOUND);  }

        comentario.setNome(comentarioCreateUpdateDTO.getNome());
        comentario.setComentario(comentarioCreateUpdateDTO.getComentario());
        comentario.setData(comentarioCreateUpdateDTO.getData());

        this.listComentario.set(index, comentario);
        return new ComentarioDTO(comentario);
    }

    @Override
    public void delete(Long id) {
        Comentario comentario = this.getComentarioComId(id);
        this.listComentario.remove(comentario);
    }

    @Override
    public ComentarioDTO getComentarioById(Long id) {
        Comentario comentario = this.getComentarioComId(id);
        if (comentario == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentário não cadastrado!"); }
        return new ComentarioDTO(comentario);
    }

    @Override
    public List<ComentarioDTO> getComentarioByPontoTuristico(PontoTuristicoDTO pontoTuristico) {
        return this.listComentario.stream()
                .filter(x -> x.getPontoTuristico().getNome().equalsIgnoreCase(pontoTuristico.getNome()))
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComentarioDTO> getAllComentarios() {
        return this.listComentario.stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    public Long getNewId(){
        if (this.listComentario.size() > 0){
            return this.listComentario.stream().max(Comparator
                            .comparingDouble(Comentario::getId))
                    .get()
                    .getId()+1;
        } else {
            return 1L;
        }
    }

    private Comentario getComentarioComId(Long id){
        return this.listComentario.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

}
