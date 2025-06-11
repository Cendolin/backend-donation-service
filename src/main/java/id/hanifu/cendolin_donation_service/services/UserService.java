package id.hanifu.cendolin_donation_service.services;

import id.hanifu.cendolin_donation_service.dtos.UserDto;
import id.hanifu.cendolin_donation_service.entities.UserEntity;
import id.hanifu.cendolin_donation_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Validated
    public UserEntity create(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(userDto.getUserId());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setUsername(userDto.getUsername());

        return this.userRepository.save(userEntity);
    }

    public boolean exists(String userId) {
        return this.userRepository.existsByUserId(userId);
    }
    public Optional<UserEntity> get(String userId) {
        return this.userRepository.findByUserId(userId);
    }
}
