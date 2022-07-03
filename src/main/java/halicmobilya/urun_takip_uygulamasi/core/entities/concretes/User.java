package halicmobilya.urun_takip_uygulamasi.core.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    // @NotNull(message = "Phone Number is required")
    // @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    @NotNull(message = "Birim alanı gereklidir.")
    @NotBlank(message = "Birim alanı gereklidir.")
    private Department department;

    @OneToMany(mappedBy = "user")
    private List<Process> processes;

    @Column(name = "image_url")
    private String image;

    @Column(name = "is_admin")
    // @NotNull(message = "Department is required")
    // @NotBlank(message = "Department is required")
    private boolean isAdmin;

    @Column(name = "created_at")
    private Date createdAt;
}
