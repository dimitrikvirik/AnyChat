package git.dimitrikvirik.anychat.model.entity;

import git.dimitrikvirik.anychat.model.Role;
import git.dimitrikvirik.anychat.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Constraint;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER", schema = "chat",
        uniqueConstraints={@UniqueConstraint(columnNames = "username", name = "c_username_unique")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "username")
    private String username;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role =  Role.USER;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    @Column(name = "reg_date")
    private LocalDate regDate = LocalDate.now();
    @Column(name = "edit_date")
    private Date editDate;

}
