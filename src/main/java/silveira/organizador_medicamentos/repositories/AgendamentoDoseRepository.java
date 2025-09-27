package silveira.organizador_medicamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import silveira.organizador_medicamentos.model.AgendamentoDose;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoDoseRepository extends JpaRepository<AgendamentoDose, Integer> {
    List<AgendamentoDose> findByMedicamentoIdAndDataDoseOrderByHorarioPrevistoAsc(Integer medicamentoId, LocalDate dataDose);

    List<AgendamentoDose> findByDataDoseOrderByHorarioPrevistoAsc(LocalDate dataDose);
}
