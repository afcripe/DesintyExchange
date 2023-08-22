package net.dahliasolutions.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import net.dahliasolutions.models.campus.Campus;
import net.dahliasolutions.models.department.DepartmentCampus;
import net.dahliasolutions.models.position.Position;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER_DETAILS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    transient String fullname;
    private String contactEmail;
    private boolean activated;
    private boolean deleted;

    public String getFullName() {
         return firstName+" "+lastName;
    }

    @ManyToOne
    @JsonIgnore
    private User director;

    @ManyToOne
    private Position position;

    @ManyToOne
    private DepartmentCampus department;

    @ManyToOne
    private Campus campus;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserRoles> userRoles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> roleList = new ArrayList<>();
        for(UserRoles uRole: userRoles){
            roleList.add(new SimpleGrantedAuthority(uRole.getName()));
        }
        return roleList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
