package br.com.simplecrud.api.model;

import static br.com.simplecrud.api.util.Constant.ROLE_UNDERLINE;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import br.com.simplecrud.api.model.interfaces.IModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements IModel<Long>, UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
            @JoinColumn(name = "id_permission") })
    private List<Permission> authorities;

    public List<String> getRoles() {
        return CollectionUtils.isEmpty(authorities) ? new LinkedList<>()
                : authorities.stream()
                        .map(a -> new StringBuilder(ROLE_UNDERLINE).append(a.getAuthority()).toString())
                        .toList();
    }
}
