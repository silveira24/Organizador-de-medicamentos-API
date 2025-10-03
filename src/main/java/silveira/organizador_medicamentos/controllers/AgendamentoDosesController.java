package silveira.organizador_medicamentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silveira.organizador_medicamentos.model.AgendamentoDose;
import silveira.organizador_medicamentos.model.StatusDose;
import silveira.organizador_medicamentos.services.AgendamentoDoseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "https://localhost:5173")
public class AgendamentoDosesController {
    @Autowired
    private AgendamentoDoseService agendamentoDoseService;

    @GetMapping
    public ResponseEntity<List<AgendamentoDose>> buscarAgendamentosPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate data,
            @RequestParam(required = false) StatusDose status) {
        List<AgendamentoDose> agendamentos;

        if (status != null) {
            agendamentos = agendamentoDoseService.buscarPorDataEStatus(data, status);
        } else {
            agendamentos = agendamentoDoseService.buscarPorData(data);
        }
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<List<AgendamentoDose>> buscarAgendamentosFuturosPorMedicamento(@PathVariable Integer medicamentoId) {
        List<AgendamentoDose> agendamentos = agendamentoDoseService.buscarAgendamentosFuturos(medicamentoId);
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping("/{id}/tomada")
    public ResponseEntity<AgendamentoDose> marcarDoseTomada(@PathVariable Integer id) {
        return agendamentoDoseService.marcarDoseTomada(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
