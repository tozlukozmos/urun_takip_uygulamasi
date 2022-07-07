package halicmobilya.urun_takip_uygulamasi.core.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "department_name")
    @NotNull(message = "Çalışma birimi isim alanı gereklidir.")
    @NotBlank(message = "Çalışma birimi isim alanı gereklidir.")
    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<User> users;
}
