package com.productShop.security;

import com.productShop.model.UserInfo;
import com.productShop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link UserDetailsServiceImpl}
 *
 * @author Dmytro Trotsenko on 2/9/23
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Load user by username in database
     *
     * @param username username
     * @return new User {@link User}
     * @throws UsernameNotFoundException User with username wasn't found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Start load User by username");
        log.info("Start find user by name");
        UserInfo userInfo = userRepository
                .findFirstByName(username)
                .orElseThrow(() -> {
                    log.error("User with name={} wasn't found", username);
                    return new EntityNotFoundException("User with name=" + username + " wasn't found");
                });
        log.info("User is funded");

        log.info("Start add role");
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userInfo.getRole().name()));
        log.info("Role: {} is added", userInfo.getRole().name());

        log.info("User is loaded");
        return new User(userInfo.getName(), userInfo.getPassword(), roles);
    }
}
