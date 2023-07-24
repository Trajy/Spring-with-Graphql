package br.com.trajy.graphql.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "solutionz")
public class SolutionEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "creation_timestamp")
    private LocalDateTime creationTimestamp;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "cotegory")
    private String category;

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
