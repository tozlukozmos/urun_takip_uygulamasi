package halicmobilya.urun_takip_uygulamasi.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "materials")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "processes"})
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int id;

    @Column(name = "reference_number")
    private Long referenceNumber;

    @Column(name = "image_url")
    private String image;

    @Column(name = "material_name")
    @NotNull(message = "Materyal isim alanı gereklidir.")
    @NotBlank(message = "Materyal isim alanı gereklidir.")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "type_id")
    @Valid
    private Type type;

    @ManyToOne()
    @JoinColumn(name = "unit_id")
    @Valid
    private Unit unit;

    @Column(name = "amount")
    @NotNull(message = "Miktar alanı gereklidir.")
    private int amount;

    @ManyToOne()
    @JoinColumn(name = "model_id")
    @Valid
    private Model model;

    @ManyToOne()
    @JoinColumn(name = "color_id")
    @Valid
    private Color color;

    @ManyToOne()
    @JoinColumn(name = "size_id")
    @Valid
    private Size size;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<Process> processes;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

}
