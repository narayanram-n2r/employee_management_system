package com.rps.capestone.batch11.services;


import com.rps.capestone.batch11.model.Role;
import com.rps.capestone.batch11.model.User;
import com.rps.capestone.batch11.repository.RoleRepository;
import com.rps.capestone.batch11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Lazy
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

        public void saveUser( User user,String role) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            Role userRole = roleRepository.findByRole("ADMIN");
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);
        }



        private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
            Set<GrantedAuthority> roles = new HashSet<>();
            userRoles.forEach((role) -> {
                roles.add(new SimpleGrantedAuthority(role.getRole()));
            });

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
            return  grantedAuthorities;
        }
        private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }}
}



