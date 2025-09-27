package silveira.organizador_medicamentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "agendamentos_doses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;

    @Column(nullable = false)
    private LocalDate dataDose;

    @Column(nullable = false)
    private LocalTime horarioPrevisto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDose statusDose = StatusDose.PENDENTE;

    private LocalDateTime horarioReal;
}
