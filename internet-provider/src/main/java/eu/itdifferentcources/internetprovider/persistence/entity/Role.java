package eu.itdifferentcources.internetprovider.persistence.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleType name;

    @Override
    public String getAuthority() {
        return name.name();
    }

    public Role(RoleType name) {
        this.name = name;
    }

    protected Role(){

    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public enum RoleType{
        ROLE_CUSTOMER, ROLE_ADMIN, ROLE_MODERATOR
    }
}
