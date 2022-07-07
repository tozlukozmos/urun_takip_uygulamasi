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
@Table(name = "sizes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "materials"})
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private int sizeId;

    @Column(name = "size_name")
    @NotNull(message = "Boyut isim alanı gereklidir.")
    @NotBlank(message = "Boyut isim alanı gereklidir.")
    private String sizeName;

    @OneToMany(mappedBy = "size")
    private List<Material> materials;
}
