package silveira.organizador_medicamentos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import silveira.organizador_medicamentos.model.Medicamento;
import silveira.organizador_medicamentos.repositories.MedicamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public Medicamento salvar(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    public Optional<Medicamento> buscarPorId(Integer id) {
        return medicamentoRepository.findById(id);
    }

    public List<Medicamento> buscarTodos() {
        return medicamentoRepository.findAll();
    }

    public void deletar(Integer id) {
        medicamentoRepository.deleteById(id);
    }

}
