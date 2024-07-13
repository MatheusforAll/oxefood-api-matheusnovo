package br.com.ifpe.oxefood.api.consultor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.consultor.Consultor;
import br.com.ifpe.oxefood.modelo.consultor.ConsultorService;

@RestController
@RequestMapping("/api/consultor")
@CrossOrigin
public class ConsultorController {

   @Autowired
   private ConsultorService consultorService;

   @PostMapping
   public ResponseEntity<Consultor> save(@RequestBody ConsultorRequest request) {

    Consultor consultor = consultorService.save(request.build());
       return new ResponseEntity<Consultor>(consultor, HttpStatus.CREATED);
   }
   @GetMapping
    public List<Consultor> listarTodos() {
        return consultorService.listarTodos();
    }
    

    @GetMapping("/{id}")
    public Consultor obterPorID(@PathVariable Long id) {
        return consultorService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultor> update(@PathVariable("id") Long id, @RequestBody ConsultorRequest request) {

        consultorService.update(id, request.build());
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

       consultorService.delete(id);
       return ResponseEntity.ok().build();
    }



    //@GetMapping("/{cpf}")
    //public Cliente obterPorID(@PathVariable string cpf) {
    //  ROTAS  return clienteService.obterPorID(id);
    //}

}


