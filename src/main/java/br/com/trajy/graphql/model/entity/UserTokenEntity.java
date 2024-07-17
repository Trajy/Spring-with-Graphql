package br.com.trajy.graphql.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "userz_token")
@NoArgsConstructor
@Getter
@Setter
@Data
@SuperBuilder
public class UserTokenEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "auth_token", nullable = false)
    private String authToken;

    @Column(name = "creation_timestamp")
    private OffsetDateTime creationTimestamp;

    @Column(name = "expiry_timestamp")
    private OffsetDateTime expiryTimestamp;

}
