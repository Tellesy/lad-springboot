package ly.kaizen.lad.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "personal_access_tokens")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PersonalAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tokenableType; // Type of the token owner (e.g., Customer, Merchant)

    @Column(nullable = false)
    private Long tokenableId; // ID of the token owner

    @Column(nullable = false)
    private String name; // Name or purpose of the token

    @Column(unique = true, nullable = false)
    private String token; // Unique token value

    @Column(columnDefinition = "TEXT")
    private String abilities; // Permissions associated with the token

    @Column
    private LocalDateTime lastUsedAt; // Timestamp of the last use

    @Column
    private LocalDateTime expiresAt; // Expiration timestamp of the token

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PersonalAccessToken that = (PersonalAccessToken) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
