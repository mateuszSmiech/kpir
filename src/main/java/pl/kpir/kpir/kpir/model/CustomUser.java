package pl.kpir.kpir.kpir.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class CustomUser extends User {

    private UserEntity userEntity;
    List<GrantedAuthority> authorities = Collections.EMPTY_LIST;


    public CustomUser(UserEntity userEntity ,Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getEmail() , userEntity.getPassword(), authorities);
        this.userEntity = userEntity;
    }

    public CustomUser(UserEntity userEntity, boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getEmail() , userEntity.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userEntity = userEntity;
    }


}
