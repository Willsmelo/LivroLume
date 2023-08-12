package br.com.fiap.api.LivroLume.controllers;

import br.com.fiap.api.LivroLume.dto.UpdatePFDTO;
import br.com.fiap.api.LivroLume.entities.ClientePF;
import br.com.fiap.api.LivroLume.entities.Livro;
import br.com.fiap.api.LivroLume.enums.Plano;
import br.com.fiap.api.LivroLume.services.ClientePFServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cliente/pf")
public class ClientePFController {

    private final ClientePFServices clientePFServices;

    @Autowired
    public ClientePFController(ClientePFServices clientePFServices) {
        this.clientePFServices = clientePFServices;
    }

    @GetMapping
    public ResponseEntity<List<ClientePF>> getAllClientes() {
        List<ClientePF> clientes = clientePFServices.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/plano/greater/{plano}")
    public ResponseEntity<List<ClientePF>> getClientesByPlanoGreaterThanOrEqual(@PathVariable Plano plano) {
        List<ClientePF> clientes = clientePFServices.findByPlanoisGreaterThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/plano/less/{plano}")
    public ResponseEntity<List<ClientePF>> getClientesByPlanoLessThanOrEqual(@PathVariable Plano plano) {
        List<ClientePF> clientes = clientePFServices.findByPlanoLessThanEqual(plano);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePF> getClienteById(@PathVariable long id) {
        ClientePF cliente = clientePFServices.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClientePF> getClienteByName(@PathVariable String name) {
        ClientePF cliente = clientePFServices.findByName(name);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClientePF> getClienteByCPF(@PathVariable String cpf) {
        ClientePF cliente = clientePFServices.findByCPF(cpf);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/rg/{rg}")
    public ResponseEntity<ClientePF> getClienteByRG(@PathVariable String rg) {
        ClientePF cliente = clientePFServices.findByRG(rg);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClientePF> registerCliente(@Valid @RequestBody ClientePF cliente) {
        ClientePF registeredCliente = clientePFServices.register(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCliente);
    }

    @Transactional
    @PutMapping("/update")
    public ResponseEntity<ClientePF> updateCliente(@Valid @RequestBody UpdatePFDTO data) {
        ClientePF updatedCliente = clientePFServices.update(data);
        return ResponseEntity.ok(updatedCliente);
    }

    @Transactional
    @PutMapping("/upgrade-plano/{id}")
    public ResponseEntity<ClientePF> upgradeClientePlano(@PathVariable long id) {
        ClientePF upgradedCliente = clientePFServices.upgradePlano(id);
        return ResponseEntity.ok(upgradedCliente);
    }

    @Transactional
    @PutMapping("/livros/add/{idLivro}")
    public ResponseEntity<Set<Livro>> adicionarLivro (@PathVariable Long idLivro,@RequestBody Long idCliente){
        return ResponseEntity.ok(clientePFServices.adicionar(idLivro,idCliente));
    }

    @Transactional
    @PutMapping("/livros/remove/{idLivro}")
    public ResponseEntity<Set<Livro>> removerLivro (@PathVariable Long idLivro,@RequestBody Long idCliente){
        return ResponseEntity.ok(clientePFServices.remover(idLivro,idCliente));
    }

    @Transactional
    @PutMapping("/downgrade-plano/{id}")
    public ResponseEntity<ClientePF> downgradeClientePlano(@PathVariable long id) {
        ClientePF downgradedCliente = clientePFServices.downgradePlano(id);
        return ResponseEntity.ok(downgradedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable long id) {
        clientePFServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
