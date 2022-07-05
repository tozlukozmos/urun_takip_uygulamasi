package halicmobilya.urun_takip_uygulamasi.entities.concretes;

import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "processes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id")
    private int processId;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "amount")
    @NotNull(message = "Miktar alanı gereklidir.")
    @NotBlank(message = "Miktar alanı gereklidir.")
    private int amount;

    @ManyToOne()
    @JoinColumn(name = "process_type_id")
    private ProcessType processType;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

}
