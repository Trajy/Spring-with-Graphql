package br.com.trajy.graphql.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "problemz")
public class ProblemEntity {

    @Id
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
