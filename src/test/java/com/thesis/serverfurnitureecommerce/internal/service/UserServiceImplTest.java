package com.thesis.serverfurnitureecommerce.internal.service;

import com.thesis.serverfurnitureecommerce.internal.repositories.IUserRepository;
import com.thesis.serverfurnitureecommerce.internal.services.user.UserServiceImpl;
import com.thesis.serverfurnitureecommerce.model.dto.UserDTO;
import com.thesis.serverfurnitureecommerce.model.entity.UserEntity;
import com.thesis.serverfurnitureecommerce.pkg.exception.AppException;
import com.thesis.serverfurnitureecommerce.pkg.mapper.IUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private IUserMapper userMapper;

    @Test
    public void testGetUserInformation_UserExists() {
        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setId(7L);
        mockUserEntity.setFullName("John Doe");

        UserDTO mockUserDTO = new UserDTO();
        mockUserDTO.setId(7L);
        mockUserDTO.setFullName("John Doe");

        Mockito.when(userRepository.findById(7L)).thenReturn(Optional.of(mockUserEntity));
        Mockito.when(userMapper.toDTO(mockUserEntity)).thenReturn(mockUserDTO);

        UserDTO userDTO = userService.getUserInformation(7L);

        assertNotNull(userDTO);
        assertEquals(7L, userDTO.getId());
        assertEquals("John Doe", userDTO.getFullName());

        Mockito.verify(userRepository).findById(7L);
    }

    @Test
    public void testGetUserInformation_UserNotFound() {
        Mockito.when(userRepository.findById(7L)).thenReturn(Optional.empty());

        assertThrows(AppException.class, () -> {
            userService.getUserInformation(7L);
        });
    }
}
