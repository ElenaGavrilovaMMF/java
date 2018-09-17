package library.service;

import library.dao.BookDao;
import library.dao.RoleDao;
import library.dto.book.ViewBookFullInfoDto;
import library.dto.user.RoleUserDto;
import library.entity.*;
import library.formatter.LocalDateFormat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleService {
    private static final RoleService INSTANCE = new RoleService();

    public RoleUserDto save(RoleUserDto dto) {
        User save = RoleDao.getInstance().save(dto.getId(), dto.getRole());
        return new RoleUserDto(save.getId(),save.getRole().getId());
    }



    public Role findById(int roleId) {
        return RoleDao.getInstance().findById(roleId)
                .map(it -> Role.builder()
                        .id(it.getId())
                        .name(it.getName())
                        .build())
                .orElse(null);
    }



    public List<Role> findAll() {
        return RoleDao.getInstance().findAll();
    }

    public static RoleService getInstance() {
        return INSTANCE;
    }
}
