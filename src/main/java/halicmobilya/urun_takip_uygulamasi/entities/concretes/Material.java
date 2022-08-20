package halicmobilya.urun_takip_uygulamasi.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.IOException;
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
    private int materialId;

    @Column(name = "reference_number")
    private Long referenceNumber;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "material_name")
    @NotNull(message = "Materyal isim alanı gereklidir.")
    @NotBlank(message = "Materyal isim alanı gereklidir.")
    private String materialName;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "amount")
    @NotNull(message = "Miktar alanı gereklidir.")
    private int amount;

    @Column(name = "unit_name")
    @NotNull(message = "Miktar birim alanı gereklidir.")
    @NotBlank(message = "Miktar birim alanı gereklidir.")
    private String unitName;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "size_name")
    private String sizeName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<Process> processes;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Transient
    public String getMaterialImageUrlPath() {
        if(imageUrl == null) return null;

        return "/images/materials/" + materialId + "/" + imageUrl;
    }

}
