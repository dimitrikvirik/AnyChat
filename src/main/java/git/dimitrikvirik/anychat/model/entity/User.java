package git.dimitrikvirik.anychat.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER", schema = "chat")
@ToString
public class User {
    @Id
    @Column(name="U_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long U_id;
    @Column(name = "K_id", unique = true)
    private String K_id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "phone")
    private String phone;

}
