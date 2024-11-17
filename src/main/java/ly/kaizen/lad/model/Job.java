package ly.kaizen.lad.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String queue; // Name of the queue

    @Column(nullable = false, columnDefinition = "TEXT")
    private String payload; // Job payload

    @Column(nullable = false)
    private int attempts; // Number of attempts made

    @Column(nullable = true)
    private LocalDateTime reservedAt; // When the job was reserved

    @Column(nullable = false)
    private LocalDateTime availableAt; // When the job is available for processing

    @Column(nullable = false)
    private LocalDateTime createdAt; // Creation timestamp

    @Column
    private LocalDateTime completedAt; // When the job was successfully completed

    @Column
    private LocalDateTime failedAt; // When the job failed

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Job job = (Job) o;
        return getId() != null && Objects.equals(getId(), job.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
