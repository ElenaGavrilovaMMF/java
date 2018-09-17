package library.service;

import library.dao.UserDao;
import library.dto.user.CreateNewUserDto;
import library.dto.user.LoginUserDto;
import library.dto.user.ViewUserBasicInfoDto;
import library.entity.Role;
import library.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public List<ViewUserBasicInfoDto> findAll() {
        return UserDao.getInstance().findAll().stream()
                .map(it -> new ViewUserBasicInfoDto(it.getId(), it.getName()))
                .collect(Collectors.toList());
    }

    public LoginUserDto findByIdUser(Long userInt) {
        return UserDao.getInstance().findById(userInt)
                .map(it -> LoginUserDto.builder()
                        .id(it.getId())
                        .login(it.getLogin())
                        .role(it.getRole().getName())
                        .build())
                .orElse(null);
    }

    public ViewUserBasicInfoDto findById(Long userId) {
        return UserDao.getInstance().findById(userId)
                .map(it -> ViewUserBasicInfoDto.builder()
                        .id(it.getId())
                        .name(it.getName())
                        .build())
                .orElse(null);
    }

    public User findByLogin(String login) {

        return UserDao.getInstance().findByLogin(login).orElse(null);
    }


    public ViewUserBasicInfoDto save(CreateNewUserDto dto) {
        List<Role> roles = RoleService.getInstance().findAll();
        User savedUser = UserDao.getInstance().save(
                User.builder()
                        .login(dto.getLogin())
                        .password(dto.getPassword())
                        .name(dto.getName())
                        .role(roles.get(1))
                        .build());

        return new ViewUserBasicInfoDto(savedUser.getId(), savedUser.getName());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
