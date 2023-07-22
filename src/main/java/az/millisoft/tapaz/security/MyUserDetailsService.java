package az.millisoft.tapaz.security;

import az.millisoft.tapaz.entity.User;
import az.millisoft.tapaz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import az.millisoft.tapaz.exception.UserNotFound;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                .findUserByEmail(username)
                .orElseThrow(() -> new UserNotFound("Istifadeci tapilmadi"));
        return new MyUserDetails(user);
    }
}
