package br.com.trajy.graphql.service;

import static br.com.trajy.graphql.util.TrainWreckUtil.nullIfWreck;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import br.com.trajy.graphql.model.entity.SolutionEntity;
import br.com.trajy.graphql.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository repository;

    private final ProblemService problemService;

    private final UserService userService;

    @Transactional(readOnly = true)
    public SolutionEntity findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<SolutionEntity> findByKeyword(String keyword) {
        return repository.findByKeyword("%".concat(keyword).concat("%"));
    }

    @Transactional
    public SolutionEntity save(SolutionEntity entity, String authToken) {
        entity.setAuthor(userService.findMeByToken(authToken));
        entity.setProblem(problemService.findById(nullIfWreck(() -> entity.getProblem().getId())));
        entity.setVoteAsBad(INTEGER_ZERO);
        entity.setVoteAsGood(INTEGER_ZERO);
        return repository.save(entity);
    }

    @Transactional
    public void vote(Long id, boolean asGood) {
        if(asGood) {
            repository.incrementGoodVote(id);
            return;
        }
        repository.incrementBadVote(id);
    }

}
