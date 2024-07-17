package br.com.trajy.graphql.model.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import br.com.trajy.graphql.codegen.tad.SolutionCategoty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Getter
@Setter
@Data
@Entity
@Table(name = "solutionz")
public class SolutionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_timestamp")
    private OffsetDateTime creationTimestamp;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(STRING)
    @Column(name = "cotegory")
    private SolutionCategoty category;

    @Column(name = "vote_as_good")
    private Integer voteAsGood;

    @Column(name = "vote_as_bad")
    private Integer voteAsBad;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private ProblemEntity problem;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

}
