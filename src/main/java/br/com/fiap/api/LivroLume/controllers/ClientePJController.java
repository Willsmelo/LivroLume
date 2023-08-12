package br.com.fiap.api.LivroLume.controllers;

import br.com.fiap.api.LivroLume.dto.UpdatePJDTO;
import br.com.fiap.api.LivroLume.entities.ClientePJ;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Plano;
import br.com.fiap.api.LivroLume.services.ClientePJServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cliente/pj")
public class    ClientePJController {

    private final ClientePJServices clientePJServices;

    @Autowired
    public ClientePJController( ClientePJServices clientePJServices) {
        this.clientePJServices = clientePJServices;
    }

    @GetMapping
    public ResponseEntity<List<ClientePJ>> getAllClientes() {
        List<ClientePJ> clientes = clientePJServices.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/plano/greater/{plano}")
    public ResponseEntity<List<ClientePJ>> getClientesByPlanoGreaterThanOrEqual(@PathVariable Plano plano) {
        List<ClientePJ> clientes = clientePJServices.findByPlanoGreaterThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/plano/less/{plano}")
    public ResponseEntity<List<ClientePJ>> getClientesByPlanoLessThanOrEqual(@PathVariable Plano plano) {
        List<ClientePJ> clientes = clientePJServices.findByPlanoLessThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePJ> getClienteById(@PathVariable long id) {
        ClientePJ cliente = clientePJServices.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClientePJ> getClienteByName(@PathVariable String name) {
        ClientePJ cliente = clientePJServices.findByName(name);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientePJ> getClienteByCPJ(@PathVariable String cpf) {
        ClientePJ cliente = clientePJServices.findByCNPJ(cpf);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/rg/{rg}")
    public ResponseEntity<ClientePJ> getClienteByRG(@PathVariable String rg) {
        ClientePJ cliente = clientePJServices.findByInscricao(rg);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClientePJ> registerCliente(@Valid @RequestBody ClientePJ cliente) {
        ClientePJ registeredCliente = clientePJServices.register(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCliente);
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<ClientePJ> updateCliente(@Valid @RequestBody UpdatePJDTO data) {
        ClientePJ updatedCliente = clientePJServices.update(data);
        return ResponseEntity.ok(updatedCliente);
    }

    @Transactional
    @PutMapping("/livros/add/{idLivro}")
    public ResponseEntity<Set<Livro>> adicionarLivro (@PathVariable Long idLivro, @RequestBody Long idCliente){
        return ResponseEntity.ok(clientePJServices.adicionar(idLivro,idCliente));
    }

    @Transactional
    @PutMapping("/livros/remove/{idLivro}")
    public ResponseEntity<Set<Livro>> removerLivro (@PathVariable Long idLivro,@RequestBody Long idCliente){
        return ResponseEntity.ok(clientePJServices.remover(idLivro,idCliente));
    }

    @Transactional
    @PutMapping("/upgrade-plano/{id}")
    public ResponseEntity<ClientePJ> upgradeClientePlano(@PathVariable long id) {
        ClientePJ upgradedCliente = clientePJServices.upgradePlano(id);
        return ResponseEntity.ok(upgradedCliente);
    }

    @Transactional
    @PutMapping("/downgrade-plano/{id}")
    public ResponseEntity<ClientePJ> downgradeClientePlano(@PathVariable long id) {
        ClientePJ downgradedCliente = clientePJServices.downgradePlano(id);
        return ResponseEntity.ok(downgradedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable long id) {
        clientePJServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
