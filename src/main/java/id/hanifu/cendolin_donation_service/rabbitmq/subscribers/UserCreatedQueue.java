package id.hanifu.cendolin_donation_service.rabbitmq.subscribers;

import id.hanifu.cendolin_donation_service.dtos.UserDto;
import id.hanifu.cendolin_donation_service.entities.UserEntity;
import id.hanifu.cendolin_donation_service.objects.UserCreatedObject;
import id.hanifu.cendolin_donation_service.services.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log
public class UserCreatedQueue {

    private final UserService userService;

    @Autowired
    public  UserCreatedQueue(UserService userService) {
        this.userService = userService;
    }

    public void receiveUserCreatedMessage(UserCreatedObject userCreatedObject) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userCreatedObject.getEmail());
        userDto.setCreatedAt(userCreatedObject.getCreatedAt());
        userDto.setUserId(userCreatedObject.getId());
        userDto.setUsername(userCreatedObject.getUsername());
        userDto.setCreatedAt(userCreatedObject.getCreatedAt());
        userDto.setUpdatedAt(userCreatedObject.getUpdatedAt());

        UserEntity userEntity = this.userService.create(userDto);
        log.info(String.format("New user %s created at %s", userEntity.getUserId(), userEntity.getCreatedAt()));
    }
}
