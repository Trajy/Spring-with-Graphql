package br.com.trajy.graphql.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "userz")
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "avatar")
    private URL avatarUrl;

    @CreationTimestamp
    @Column(name = "creation_timestamp")
    private OffsetDateTime creationTimestamp;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "author")
    private List<ProblemEntity> problemz;

    @OneToMany(mappedBy = "author")
    private List<SolutionEntity> solutionz;

}
