package halicmobilya.urun_takip_uygulamasi.core.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "processes"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name")
    @NotNull(message = "İsim alanı gereklidir.")
    @NotBlank(message = "İsim alanı gereklidir.")
    private String firstname;

    @Column(name = "last_name")
    @NotNull(message = "Soyisim alanı gereklidir.")
    @NotBlank(message = "Soyisim alanı gereklidir.")
    private String lastname;

    @Column(name = "email")
    @Email(message = "Email yanlış bir formata sahiptir.")
    @NotNull(message = "Email alanı gereklidir.")
    @NotBlank(message = "Email alanı gereklidir.")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Şifre alanı gereklidir.")
    @NotBlank(message = "Şifre alanı gereklidir.")
    @Size(min = 6, max = 12, message = "Şifre 6 ile 12 karakter arası olmalıdır.")
    private String password;

    @Column(name = "phone_number")
    @NotNull(message = "Telefon numarası alanı gereklidir.")
    @NotBlank(message = "Telefon numarası alanı gereklidir.")
    private String phoneNumber;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    @Valid
    private Department department;

    @OneToMany(mappedBy = "user")
    private List<Process> processes;

    @Column(name = "image_url")
    private String image;

    @Column(name = "is_admin")
    @NotNull(message = "Admin yetki bilgisi alanı gereklidir.")
    private boolean isAdmin;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;
}
