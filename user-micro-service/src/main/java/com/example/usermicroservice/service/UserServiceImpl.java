package com.example.usermicroservice.service;

import com.example.usermicroservice.client.OrderServiceClient;
import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.entity.UserEntity;
import com.example.usermicroservice.repository.UserRepository;
import com.example.usermicroservice.vo.ResponseOrder;
import com.example.usermicroservice.vo.ResponseUser;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final Environment environment;
    private final OrderServiceClient orderServiceClient;


    @Override
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = userDto.toEntity();
        userEntity.setEncryptedPasswd(passwordEncoder.encode(userDto.getPasswd()));

        UserEntity createdUser = userRepository.save(userEntity);
        return userDto.toResponse(createdUser);
    }

    @Override
    public ResponseUser getUserByUserId(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not Found")
        );
        UserDto userDto = new UserDto();

        /*Using as Feign Error Decoder*/
        List<ResponseOrder> orderList = orderServiceClient.getOrders(String.valueOf(userId));


        /*Using as Feign client*/
        /*+Feign Exception Handling
        List<ResponseOrder> orderList = null;
        try {
            orderList = orderServiceClient.getOrders_ng(String.valueOf(userId));
        } catch (FeignException e) {
            log.error(e.getMessage());
        }
        */

        /*Using as RestTemplate*/
        /*
        String orderUrl = String.format(environment.getProperty("order_service.url"), userId);
        ResponseEntity<List<ResponseOrder>> orderListResponse =
                restTemplate.exchange(orderUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ResponseOrder>>() {
                        });
        List<ResponseOrder> orderList = orderListResponse.getBody();
        */
        return userDto.toResponse(userEntity, orderList);
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

//    @Override
//    public UserDto getUserDetailsByEmail(String email) {
//        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
//                () -> new UsernameNotFoundException(email));
//        log.info("12321");
//        log.info(userEntity.getEmail());
//        return UserDto.builder().
//                userId(String.valueOf(userEntity.getUserId()))
//                .email(userEntity.getEmail())
//                .name(userEntity.getName())
//                .encryptedPasswd(userEntity.getEncryptedPasswd())
//                .build();
//    }
}
