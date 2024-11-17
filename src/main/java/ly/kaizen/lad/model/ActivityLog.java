package ly.kaizen.lad.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "activity_logs")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logName; // Name of the log or action

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description; // Detailed description of the activity

    @Column(nullable = true)
    private String subjectType; // Type of subject (e.g., Customer, Merchant)

    @Column(nullable = true)
    private Long subjectId; // ID of the subject involved in the activity

    @Column(nullable = true)
    private String causerType; // Type of entity causing the activity

    @Column(nullable = true)
    private Long causerId; // ID of the entity causing the activity

    @Column(nullable = true, columnDefinition = "TEXT")
    private String properties; // Additional metadata about the activity

    @Column(nullable = false)
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
        ActivityLog that = (ActivityLog) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
