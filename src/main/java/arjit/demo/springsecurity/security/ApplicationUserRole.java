package arjit.demo.springsecurity.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static arjit.demo.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));

    @Getter
    private final Set<ApplicationUserPermission> userPermissionSet;

    ApplicationUserRole(Set<ApplicationUserPermission> userPermissionSet) {
        this.userPermissionSet = userPermissionSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getUserPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        System.out.println(permissions);
         permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
         return permissions;
    }

}
