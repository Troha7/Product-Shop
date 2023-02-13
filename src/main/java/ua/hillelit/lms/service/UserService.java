package ua.hillelit.lms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.hillelit.lms.dto.UserInfoDto;
import ua.hillelit.lms.model.UserInfo;
import ua.hillelit.lms.repository.UserRepository;

/**
 * {@link UserService}
 *
 * @author Dmytro Trotsenko on 2/10/23
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    /**
     * Create new entity {@link UserInfo} and save row with data in database
     *
     * @param userInfoDto object with data
     * @return userInfoDto
     */
    public UserInfoDto createUser(UserInfoDto userInfoDto) {
        log.info("Start create user");
        if (!hasUserByName(userInfoDto.getName())) {
            UserInfo userInfo = objectMapper.convertValue(userInfoDto, UserInfo.class);
            userInfo.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
            userRepository.save(userInfo);
            userInfoDto.setId(userInfo.getId());
        } else {
            log.warn("User name={} already created", userInfoDto.getName());
        }
        log.info("User is create");
        return userInfoDto;
    }

    /**
     * User by name exist
     *
     * @param name username
     * @return true if exist
     */
    public boolean hasUserByName(String name) {
        return userRepository.existsUserByName(name);
    }

}
