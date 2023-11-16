package com.management.managementsystem.services;


import com.management.managementsystem.entities.RefreshToken;
import com.management.managementsystem.entities.User;
import com.management.managementsystem.repositories.RefreshTokenRepository;
import com.management.managementsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    public long refreshTokenValidity=5*60*60*1000;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    //generating refresh token
    public RefreshToken createRefreshToken(String userName){

        User user = userRepository.findByEmail(userName).get();
        RefreshToken refreshToken1 = user.getRefreshToken();

        if (refreshToken1==null) {
             refreshToken1 = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiry(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
        } else {
            refreshToken1.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        user.setRefreshToken(refreshToken1);


        // saving refresh token to db
        refreshTokenRepository.save(refreshToken1);


        return refreshToken1;
    }

    //checking if token is expired or not
    public RefreshToken verifyRefreshToken(String refreshToken){
        RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new RuntimeException("Given Token does not exists"));

        if(refreshTokenOb.getExpiry().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refreshTokenOb);
            throw new RuntimeException("Refresh Token Expired !!");
        }
        return refreshTokenOb;
    }
}
