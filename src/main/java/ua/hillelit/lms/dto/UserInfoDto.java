package ua.hillelit.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.hillelit.lms.model.UserRole;

/**
 * {@link UserInfoDto}
 *
 * @author Dmytro Trotsenko on 2/10/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    private Integer id;

    private String name;

    private String password;

    private UserRole role;

}
