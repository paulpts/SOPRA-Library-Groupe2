package library_boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import library_boot.dao.IDAOUser;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOUser dao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {


        return this.dao.findByLogin(login)
            .map(user -> User
                    .withUsername(login)
                    .password(user.getPassword())
                    .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        ;
    }
}
