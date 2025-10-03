package silveira.organizador_medicamentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silveira.organizador_medicamentos.model.Medicamento;
import silveira.organizador_medicamentos.services.AgendamentoDoseService;
import silveira.organizador_medicamentos.services.MedicamentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "https://localhost:5173")
public class MedicamentoController {

    @Autowired
    private MedicamentoService  medicamentoService;

    @Autowired
    private AgendamentoDoseService agendamentoDoseService;

    @PostMapping
    public ResponseEntity<Medicamento> criarMedicamento(@RequestBody Medicamento medicamento){
        Medicamento novoMedicamento = medicamentoService.salvar(medicamento);
        agendamentoDoseService.gerarAgendamentosIniciais(novoMedicamento);
        return new ResponseEntity<>(novoMedicamento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> listarMedicamentos(){
        List<Medicamento> medicamentos = medicamentoService.buscarTodos();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarMedicamento(@PathVariable Integer id){
        Optional<Medicamento> medicamento = medicamentoService.buscarPorId(id);

        return medicamento.map(ResponseEntity::ok)
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> atualizarMedicamento(@PathVariable Integer id, @RequestBody Medicamento medicamento){
        return medicamentoService.buscarPorId(id)
                .map(medicamentoExistente -> {
                    medicamentoExistente.setNome(medicamento.getNome());
                    medicamentoExistente.setDosagem(medicamento.getDosagem());
                    medicamentoExistente.setFrequenciaDiaria(medicamento.getFrequenciaDiaria());
                    medicamentoExistente.setHorariosPadrao(medicamento.getHorariosPadrao());

                    Medicamento atualizado = medicamentoService.salvar(medicamentoExistente);

                    agendamentoDoseService.excluirAgendamentosFuturos(id);
                    agendamentoDoseService.gerarAgendamentosIniciais(atualizado);

                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Integer id){
        if (medicamentoService.buscarPorId(id).isPresent()) {
            agendamentoDoseService.excluirAgendamentosFuturos(id);
            medicamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
