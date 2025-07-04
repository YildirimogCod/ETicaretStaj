package com.yildirimog.eticaretstaj.auth.service;

import com.yildirimog.eticaretstaj.auth.dto.AuthResponse;
import com.yildirimog.eticaretstaj.auth.dto.LoginRequest;
import com.yildirimog.eticaretstaj.auth.dto.RegisterRequest;
import com.yildirimog.eticaretstaj.user.entity.User;
import com.yildirimog.eticaretstaj.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
       this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
   }
   @Transactional
    public AuthResponse register(RegisterRequest registerRequest){
       //Kullanıcı daha önce kaydolmuş mu kontrol et
       if(userRepository.findByEmail(registerRequest.email()).isPresent()){
              throw new IllegalStateException("Email already taken");
       }
       //Kullanıcıyı kaydet
       User user = User.builder()
               .username(registerRequest.username())
               .password(passwordEncoder.encode(registerRequest.password()))
               .email(registerRequest.email())
               .build();
       userRepository.save(user);
         //Kullanıcıyı AuthResponse olarak döndür
       return new AuthResponse(
              null,
               user.getUsername(),
               user.getEmail()
       );
   }
    public AuthResponse login(LoginRequest loginRequest){
       User user = userRepository.findByUsername(loginRequest.username())
               .orElseThrow(() -> new IllegalStateException("User not found"));
         if(!passwordEncoder.matches(loginRequest.password(), user.getPassword())){
             throw new IllegalStateException("Invalid password");
         }
         //Kullanıcıyı AuthResponse olarak döndür
         return new AuthResponse(
                null,
                user.getUsername(),
                user.getEmail());
   }

}
