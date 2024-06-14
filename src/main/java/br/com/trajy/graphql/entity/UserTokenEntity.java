package br.com.trajy.graphql.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
@Table(name = "userz_token")
public class UserTokenEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "auth_token", nullable = false)
    private String authToken;

    @Column(name = "creation_timestamp")
    private LocalDateTime creationTimestamp;

    @Column(name = "expiry_timestamp")
    private LocalDateTime expiryTimestamp;

}
