package recipes.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.persistence.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceIml implements UserDetailsService {
    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findUserByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Not found: " + username);
        }

        return new UserDetailsImpl(optionalUser.get());
    }
}

