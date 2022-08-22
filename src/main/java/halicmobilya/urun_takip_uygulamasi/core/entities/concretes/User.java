package halicmobilya.urun_takip_uygulamasi.core.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import halicmobilya.urun_takip_uygulamasi.entities.concretes.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private int userId;

    @Column(name = "username")
    @NotNull(message = "Kullanıcı Adı gereklidir.")
    @NotBlank(message = "Kullanıcı Adı gereklidir.")
    private String username;

    @Column(name = "first_name")
    @NotNull(message = "İsim alanı gereklidir.")
    @NotBlank(message = "İsim alanı gereklidir.")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Soyisim alanı gereklidir.")
    @NotBlank(message = "Soyisim alanı gereklidir.")
    private String lastName;

    @Column(name = "email")
    // @Email(message = "Email yanlış bir formata sahiptir.")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Şifre alanı gereklidir.")
    @NotBlank(message = "Şifre alanı gereklidir.")
    @Size(min = 6, max = 18, message = "Şifre 6 ile 18 karakter olmalıdır.")
    private String password;

    @Column(name = "phone_number")
    @NotNull(message = "Telefon Numarası alanı gereklidir.")
    @NotBlank(message = "Telefon Numarası alanı gereklidir.")
    private String phoneNumber;

    @Column(name = "department_name")
    @NotNull(message = "Çalışma birimi isim alanı gereklidir.")
    @NotBlank(message = "Çalışma birimi isim alanı gereklidir.")
    private String departmentName;

    @OneToMany(mappedBy = "user")
    private List<Process> processes;

    /*@Column(name = "image_name")
    private String imageName;

    @Column(name = "image_data")
    private byte[] imageData;*/

    @Column(name = "image_url")
    private String imageUrl;

    // @Lob
    // @Column(name = "image_data")
    // private byte[] imageData;

    @Column(name = "is_admin")
    @NotNull(message = "Admin yetki bilgisi alanı gereklidir.")
    private boolean isAdmin;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    /*@Transient
    public String getUserImageUrlPath() {
        if(imageUrl == null) return null;

        return "/images/users/" + userId + "/" + imageUrl;
    }*/
}
