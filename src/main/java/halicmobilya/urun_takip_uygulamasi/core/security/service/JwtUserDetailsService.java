package halicmobilya.urun_takip_uygulamasi.core.security.service;

import halicmobilya.urun_takip_uygulamasi.core.dataAccess.abstracts.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public JwtUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User user = userDao.getByEmail(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        String role = user.isAdmin() ? "Admin" : "User";
        authorityList.add(new SimpleGrantedAuthority(role));
        return new User(user.getEmail(), user.getPassword(), authorityList);
    }
}
