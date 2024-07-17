package br.com.trajy.graphql.service;

import br.com.trajy.graphql.model.entity.SolutionEntity;
import br.com.trajy.graphql.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository repository;

    public List<SolutionEntity> findByKeyword(String keyword) {
        return repository.findByKeyword("%".concat(keyword).concat("%"));
    }

}
