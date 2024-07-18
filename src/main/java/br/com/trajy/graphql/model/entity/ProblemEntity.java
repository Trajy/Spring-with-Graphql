package br.com.trajy.graphql.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "problemz")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ProblemEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(name = "creation_timestamp")
    private OffsetDateTime creationTimestamp;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "tags")
    private String tags;

    @OrderBy("creationTimestamp desc")
    @OneToMany(mappedBy = "problem")
    private List<SolutionEntity> solutionz;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

}
