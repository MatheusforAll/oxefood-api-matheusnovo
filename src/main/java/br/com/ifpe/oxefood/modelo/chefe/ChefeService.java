package br.com.ifpe.oxefood.modelo.chefe;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.modelo.chefe.ChefeRepository;
import br.com.ifpe.oxefood.modelo.chefe.Chefe;
import jakarta.transaction.Transactional;

@Service
public class ChefeService {
    
 @Autowired
   private ChefeRepository repository;


     @Transactional
   public Chefe save(Chefe chefe ) {

    chefe.setHabilitado(Boolean.TRUE); 
    chefe.setVersao(1L);
    chefe.setDataCriacao(LocalDate.now());
       return repository.save(chefe);
   }

   public List<Chefe> listarTodos() {
  
    return repository.findAll();
}

public Chefe obterPorID(Long id) {

  return repository.findById(id).get();
}

@Transactional
   public void update(Long id,  Chefe  chefeAlterado) {

   Chefe chefe = repository.findById(id).get();
   chefe.setNome(chefeAlterado.getNome());
   chefe.setEndereco(chefeAlterado.getEndereco());
   chefe.setValorMercado(chefeAlterado.getValorMercado());
   chefe.setPaginaWeb(chefeAlterado.getPaginaWeb());
   chefe.setQtdFuncionarios(chefeAlterado.getQtdFuncionarios());

   chefe.setVersao(chefe.getVersao() + 1);
   repository.save(chefe);
}


@Transactional
   public void delete(Long id) {

   Chefe chefe = repository.findById(id).get();
   chefe.setHabilitado(Boolean.FALSE);
   chefe.setVersao( chefe.getVersao() + 1);

       repository.save(chefe);
   }




    
}
