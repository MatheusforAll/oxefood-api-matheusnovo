package br.com.ifpe.oxefood.modelo.consultor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ConsultorService {

   @Autowired
   private ConsultorRepository repository;

   @Transactional
   public Consultor save(Consultor consultor) {

    consultor.setHabilitado(Boolean.TRUE);
    consultor.setVersao(1L);
    consultor.setDataCriacao(LocalDate.now());
       return repository.save(consultor);
   }

   public List<Consultor> listarTodos() {
  
    return repository.findAll();
}

public Consultor obterPorID(Long id) {

    return repository.findById(id).get();
}

@Transactional
   public void update(Long id, Consultor consultorAlterado) {

    Consultor consultor = repository.findById(id).get();
      consultor.setNome(consultorAlterado.getNome());
      consultor.setDataNascimento(consultorAlterado.getDataNascimento());
      consultor.setCpf(consultorAlterado.getCpf());
      consultor.setFoneCelular(consultorAlterado.getFoneCelular());
      consultor.setFoneFixo(consultorAlterado.getFoneFixo());
	    
      consultor.setVersao(consultor.getVersao() + 1);
      repository.save(consultor);
  }

  @Transactional
   public void delete(Long id) {

    Consultor consultor = repository.findById(id).get();
       consultor.setHabilitado(Boolean.FALSE);
       consultor.setVersao(consultor.getVersao() + 1);

       repository.save(consultor);
   }



}


