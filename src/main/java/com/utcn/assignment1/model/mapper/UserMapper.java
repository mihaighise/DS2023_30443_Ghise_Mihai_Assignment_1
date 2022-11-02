package com.utcn.assignment1.model.mapper;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import com.utcn.assignment1.model.dto.DeviceDTO;
import com.utcn.assignment1.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDTO> {

    private final DeviceMapper deviceMapper;

    @Override
    public UserDTO convertToDTO(User user) {
        if(user != null) {
            Set<DeviceDTO> devicesDTO = new HashSet<>();
            if(user.getDevices() != null) {
                devicesDTO = new HashSet<DeviceDTO>(deviceMapper.convertAllToDTO(user.getDevices().stream().toList()));
            }
            return UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .userRole(user.getUserRole())
                    .devices(devicesDTO)
                    .build();
        }
        return null;
    }

    @Override
    public User convertToEntity(UserDTO userDTO) {
        if(userDTO != null) {
            Set<Device> devices = new HashSet<Device>(deviceMapper.convertAllToEntity(userDTO.getDevices().stream().toList()));
            return User.builder()
                    .id(userDTO.getId())
                    .username(userDTO.getUsername())
                    .userRole(userDTO.getUserRole())
                    .devices(devices)
                    .build();
        }
        return null;
    }
}
