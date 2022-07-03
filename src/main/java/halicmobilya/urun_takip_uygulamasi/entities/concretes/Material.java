package halicmobilya.urun_takip_uygulamasi.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @NotNull(message = "İsim alanı gereklidir.")
    @NotBlank(message = "İsim alanı gereklidir.")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name = "unit_of_amount")
    @NotNull(message = "Miktar birimi alanı gereklidir.")
    @NotBlank(message = "Miktar birimi alanı gereklidir.")
    private String unitOfAmount;

    @Column(name = "amount")
    @NotNull(message = "Miktar alanı gereklidir.")
    @NotBlank(message = "Miktar alanı gereklidir.")
    private int amount;

    @ManyToOne()
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne()
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "size")
    private String size;

    // @Column(name = "quantity_in_stock")
    // private int quantityInStock;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "material")
    private List<Process> processes;

    @Column(name = "created_at")
    private Date createdAt;

}
