package git.dimitrikvirik.anychat.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "method_permission", schema = "chat")
public class MethodPermission {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "name", unique = true)
    String name;
    @ManyToMany
    Set<Status> statusSet;
    @ManyToMany
    Set<Permission> permissionSet;
}
