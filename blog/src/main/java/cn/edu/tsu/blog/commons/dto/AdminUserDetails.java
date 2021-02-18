package cn.edu.tsu.blog.commons.dto;


import cn.edu.tsu.blog.model.Admin;
import cn.edu.tsu.blog.model.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

    /**
     * SpringSecurity需要的用户详情
     * Created by macro on 2018/4/26.
     */
    public class AdminUserDetails implements UserDetails {
        private Admin umsAdmin;
        private List<Permission> permissionList;
        public AdminUserDetails(Admin umsAdmin, List<Permission> permissionList) {
            this.umsAdmin = umsAdmin;
            this.permissionList = permissionList;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            //返回当前用户的权限
            return permissionList.stream()
                    .filter(permission -> permission.getValue()!=null)
                    .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                    .collect(Collectors.toList());

        }

        @Override
        public String getPassword() {
            return umsAdmin.getPassword();
        }

        @Override
        public String getUsername() {
            return umsAdmin.getCode();
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
    }



