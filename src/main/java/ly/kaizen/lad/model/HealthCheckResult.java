package ly.kaizen.lad.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "health_check_results")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class HealthCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String checkName; // Name of the health check

    @Column(nullable = false)
    private String checkLabel; // Label or description of the health check

    @Column(nullable = false)
    private String status; // Status of the health check (e.g., PASS, FAIL)

    @Column(columnDefinition = "TEXT")
    private String notificationMessage; // Detailed message for notifications

    @Column(nullable = true)
    private String shortSummary; // Short summary of the health check

    @Column(nullable = false, columnDefinition = "TEXT")
    private String meta; // Additional metadata or information about the check

    @Column(nullable = false)
    private LocalDateTime endedAt; // Timestamp when the check ended

    @Column(nullable = false)
    private String batch; // Batch identifier for the health check

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
        HealthCheckResult that = (HealthCheckResult) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
