package br.com.trajy.graphql.service;

import br.com.trajy.graphql.model.entity.ProblemEntity;
import br.com.trajy.graphql.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository repository;

    public List<ProblemEntity> findLatest() {
        return repository.findAllByOrderByCreationTimestampDesc();
    }

    public ProblemEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ProblemEntity> findByKeyword(String keyword) {
        return repository.findByKeyword("%".concat(keyword).concat("%"));
    }

}
