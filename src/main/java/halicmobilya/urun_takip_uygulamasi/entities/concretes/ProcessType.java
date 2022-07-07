package halicmobilya.urun_takip_uygulamasi.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "process_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "processes"})
public class ProcessType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_type_id")
    private int processTypeId;

    @Column(name = "process_type_name")
    @NotNull(message = "İşlem türü isim alanı gereklidir.")
    @NotBlank(message = "İşlem türü isim alanı gereklidir.")
    private String processTypeName;

    @OneToMany(mappedBy = "processType")
    private List<Process> processes;
}
