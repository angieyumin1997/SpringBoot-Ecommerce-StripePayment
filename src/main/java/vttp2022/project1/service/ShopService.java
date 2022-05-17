package vttp2022.project1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import vttp2022.project1.models.Account;
import vttp2022.project1.repository.ShopRepository;

@Service
public class ShopService implements UserDetailsService{
    
    @Autowired
    private ShopRepository shopRepo;

    public void addNewUser(Account account){
        shopRepo.insertUser(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = shopRepo.authenticateUser(username);


        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }

        String role = account.getRole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);

        grantList.add(authority);

        UserDetails userDetails = (UserDetails) new User(account.getUsername(), //
        account.getPassword(), grantList);
        System.out.println(">>>>>> userDetails: " +userDetails);

        return userDetails;
    }
    
}
