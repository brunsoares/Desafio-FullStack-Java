package br.com.juridico.totvs.fullstack.Backend.service.pais;

import br.com.juridico.totvs.fullstack.Backend.domain.Pais;
import br.com.juridico.totvs.fullstack.Backend.service.dto.pais.PaisCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.pais.PaisDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaisService {
    List<Pais> listPais = null;
    public PaisDTO create(PaisCreateUpdateDTO paisCreateUpdateDTO);
    public PaisDTO update(Long id, PaisCreateUpdateDTO paisCreateUpdateDTO);
    public void delete(Long id);
    public PaisDTO getPaisbyId(Long id);
    public List<PaisDTO> getPaisByContinente(String continente);
    public List<PaisDTO> getAllPais();
}
