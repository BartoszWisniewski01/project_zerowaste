package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Entities.User;
import com.example.project_zerowaste.Entities.Seller;
import com.example.project_zerowaste.Entities.User_Seller;
import com.example.project_zerowaste.Repositories.SellerRepository;
import com.example.project_zerowaste.Repositories.UserRepository;
import com.example.project_zerowaste.Repositories.User_SellerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    SellerRepository sellerRepository;
    User_SellerRepository user_sellerRepository;

    @Builder
    public void save(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username with name exists: " + user.getUsername());
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAccount_status("ACTIVE");
        if (Objects.equals(user.getRole(), "ROLE_SELLER")){
            Seller seller = Seller.builder()
                    .address(user.getHome_address())
                    .name(user.getName())
                    .login(user.getUsername())
                    .password(encodedPassword)
                    .build();
            sellerRepository.save(seller);

            userRepository.save(user);
            User_Seller user_seller = User_Seller.builder()
                    .user(user)
                    .seller(seller)
                    .build();
            user_sellerRepository.save(user_seller);
        }
        else
            userRepository.save(user);
    }

    public User findByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
    }

    public void editUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        user.setRole(updatedUser.getRole());
        user.setAccount_status(updatedUser.getAccount_status());
        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}

