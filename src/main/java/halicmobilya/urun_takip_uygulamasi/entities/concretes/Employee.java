package halicmobilya.urun_takip_uygulamasi.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    @NotNull(message = "İsim alanı gereklidir.")
    @NotBlank(message = "İsim alanı gereklidir.")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Soyisim alanı gereklidir.")
    @NotBlank(message = "Soyisim alanı gereklidir.")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Email yanlış bir formata sahiptir.")
    private String email;

    @Column(name = "phone_number")
    @NotNull(message = "Telefon Numarası alanı gereklidir.")
    @NotBlank(message = "Telefon Numarası alanı gereklidir.")
    private String phoneNumber;

    @Column(name = "department_name")
    @NotNull(message = "Çalışma birimi isim alanı gereklidir.")
    @NotBlank(message = "Çalışma birimi isim alanı gereklidir.")
    private String departmentName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
