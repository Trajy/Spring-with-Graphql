package br.com.trajy.graphql.service;

import br.com.trajy.graphql.model.entity.ProblemEntity;
import br.com.trajy.graphql.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository repository;

    private final UserService userService;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ProblemEntity> findLatest() {
        return repository.findAllByOrderByCreationTimestampDesc();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ProblemEntity findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ProblemEntity> findByKeyword(String keyword) {
        return repository.findByKeyword("%".concat(keyword).concat("%"));
    }

    @Transactional(rollbackFor = Exception.class)
    public ProblemEntity save(ProblemEntity entity, String authToken) {
        entity.setAuthor(userService.findMeByToken(authToken));
        return repository.save(entity);
    }

}
