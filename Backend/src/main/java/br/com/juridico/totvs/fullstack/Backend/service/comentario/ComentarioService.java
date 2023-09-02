package br.com.juridico.totvs.fullstack.Backend.service.comentario;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.comentario.ComentarioDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ponto_turistico.PontoTuristicoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ComentarioService {
    List<Comentario> listComentarios = new ArrayList<>();
    ComentarioDTO create(ComentarioCreateUpdateDTO comentarioCreateUpdateDTO);
    ComentarioDTO update(Long id, ComentarioCreateUpdateDTO comentarioCreateUpdateDTO);
    void delete(Long id);
    ComentarioDTO getComentarioById(Long id);
    List<ComentarioDTO> getComentarioByPontoTuristico(PontoTuristicoDTO pontoTuristico);
    List<ComentarioDTO> getAllComentarios();
    Long getNewId();
}
