package br.com.trajy.graphql.service;

import br.com.trajy.graphql.entity.ProblemEntity;
import br.com.trajy.graphql.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

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
