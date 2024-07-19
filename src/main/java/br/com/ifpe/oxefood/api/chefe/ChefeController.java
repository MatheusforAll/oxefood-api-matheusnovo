package br.com.ifpe.oxefood.api.chefe;

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


import br.com.ifpe.oxefood.api.chefe.ChefeRequest;
import br.com.ifpe.oxefood.modelo.chefe.ChefeService;
import br.com.ifpe.oxefood.modelo.chefe.Chefe;


@RestController
@RequestMapping("/api/chefe")
@CrossOrigin
public class ChefeController {
    
     @Autowired
   private ChefeService chefeService;


    @PostMapping
   public ResponseEntity<Chefe> save(@RequestBody ChefeRequest request) {

    Chefe chefe = chefeService.save(request.build());
       return new ResponseEntity<Chefe>(chefe, HttpStatus.CREATED);
   }

   @GetMapping
    public List<Chefe> listarTodos() {
        return chefeService.listarTodos();
    }

@GetMapping("/{id}")
    public Chefe obterPorID(@PathVariable Long id) {
        return chefeService.obterPorID(id);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Chefe> update(@PathVariable("id") Long id, @RequestBody ChefeRequest request) {

        chefeService.update(id, request.build());
       return ResponseEntity.ok().build();
    }

      @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        chefeService.delete(id);
       return ResponseEntity.ok().build();
    }

}
