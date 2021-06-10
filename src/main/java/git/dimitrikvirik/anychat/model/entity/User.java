package git.dimitrikvirik.anychat.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private long id;
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
    @ManyToOne(targetEntity = Role.class)
    private Role role;
    @ManyToOne(targetEntity = Status.class)
    private Status status;
    @Column(name = "reg_date")
    private Date regDate = new Date();
    @Column(name = "edit_date")
    private Date editDate = new Date();


}
