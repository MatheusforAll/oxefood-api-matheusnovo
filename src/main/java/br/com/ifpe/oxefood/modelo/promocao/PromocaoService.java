package br.com.ifpe.oxefood.modelo.promocao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PromocaoService {

    private static final Logger logger = LoggerFactory.getLogger(PromocaoService.class);

    @Autowired
    private PromocaoRepository repository;

    @Transactional
    public Promocao save(Promocao promocao) {
        promocao.setHabilitado(Boolean.TRUE);
        promocao.setVersao(1L);
        promocao.setDataCriacao(LocalDate.now());
        Promocao savedPromocao = repository.save(promocao);
        logger.info("Promoção salva com sucesso: {}", savedPromocao);
        return savedPromocao;
    }

    public List<Promocao> listarTodos() {
        List<Promocao> promocoes = repository.findAll();
        logger.info("Lista de promoções carregada: {}", promocoes);
        return promocoes;
    }

    public Promocao obterPorID(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Promoção não encontrada para o ID: " + id));
    }

    @Transactional
    public void update(Long id, Promocao promocaoAlterado) {
        Promocao promocao = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Promoção não encontrada para o ID: " + id));
        promocao.setTitulo(promocaoAlterado.getTitulo());
        promocao.setDataInicio(promocaoAlterado.getDataInicio());
        promocao.setDataFim(promocaoAlterado.getDataFim());
        promocao.setRegra(promocaoAlterado.getRegra());
        promocao.setValorDesconto(promocaoAlterado.getValorDesconto());
        promocao.setVersao(promocao.getVersao() + 1);
        repository.save(promocao);
        logger.info("Promoção atualizada com sucesso: {}", promocao);
    }

    @Transactional
    public void delete(Long id) {
        Promocao promocao = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Promoção não encontrada para o ID: " + id));
        promocao.setHabilitado(Boolean.FALSE);
        promocao.setVersao(promocao.getVersao() + 1);
        repository.save(promocao);
        logger.info("Promoção desabilitada com sucesso: {}", promocao);
    }
}
