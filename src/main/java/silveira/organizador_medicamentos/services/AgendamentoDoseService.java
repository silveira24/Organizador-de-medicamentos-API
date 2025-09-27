package silveira.organizador_medicamentos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import silveira.organizador_medicamentos.model.AgendamentoDose;
import silveira.organizador_medicamentos.model.Medicamento;
import silveira.organizador_medicamentos.model.StatusDose;
import silveira.organizador_medicamentos.repositories.AgendamentoDoseRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoDoseService {
    @Autowired
    private AgendamentoDoseRepository agendamentoDoseRepository;

    public void gerarAgendamentosIniciais(Medicamento medicamento) {
        final int DIAS_INICIAIS = 7;

        List<LocalTime> horarios = extrairHorarios(medicamento.getHorariosPadrao());

        LocalDate dataAtual = LocalDate.now();

        for (int i = 0; i < DIAS_INICIAIS; i++) {
            LocalDate dataDose = dataAtual.plusDays(i);
            for (LocalTime horario : horarios) {
                AgendamentoDose agendamento = new AgendamentoDose();
                agendamento.setMedicamento(medicamento);
                agendamento.setDataDose(dataDose);
                agendamento.setHorarioPrevisto(horario);
                agendamento.setStatusDose(StatusDose.PENDENTE);

                agendamentoDoseRepository.save(agendamento);
            }
        }
    }

    private List<LocalTime> extrairHorarios(String horariosPadrao) {
        List<LocalTime> horarios = new ArrayList<>();
        String[] horarioStrings = horariosPadrao.split(",");

        for (String hs : horarioStrings) {
            try {
                horarios.add(LocalTime.parse(hs.trim()));
            } catch (Exception e) {
                System.err.println("Erro ao fazer parsing do horário: " + hs + " - " + e.getMessage());
            }
        }

        return horarios;
    }

    public Optional<AgendamentoDose> marcarDoseTomada(Integer id) {
        Optional<AgendamentoDose> agendamento = agendamentoDoseRepository.findById(id);

        if (agendamento.isPresent()) {
            AgendamentoDose dose = agendamento.get();

            if (dose.getStatusDose() != StatusDose.TOMADA) {
                dose.setStatusDose(StatusDose.PENDENTE);
                dose.setHorarioReal(LocalDateTime.now());
                return Optional.of(agendamentoDoseRepository.save(dose));
            }
        }
        return Optional.empty();
    }

    public List<AgendamentoDose> buscarPorData(LocalDate data) {
        return agendamentoDoseRepository.findByDataDoseOrderByHorarioPrevistoAsc(data);
    }


}
