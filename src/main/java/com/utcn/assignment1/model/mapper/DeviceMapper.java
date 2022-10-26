package com.utcn.assignment1.model.mapper;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.dto.DeviceDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper implements Mapper<Device, DeviceDTO> {
    @Override
    public DeviceDTO convertToDTO(Device device) {
        if(device != null) {
            return DeviceDTO.builder()
                    .id(device.getId())
                    .description(device.getDescription())
                    .address(device.getAddress())
                    .maximumEnergy(device.getMaximumEnergy())
                    .build();
        }
        return null;
    }

    @Override
    public Device convertToEntity(DeviceDTO deviceDTO) {
        if(deviceDTO != null) {
            return Device.builder()
                    .id(deviceDTO.getId())
                    .description(deviceDTO.getDescription())
                    .address(deviceDTO.getAddress())
                    .maximumEnergy(deviceDTO.getMaximumEnergy())
                    .build();
        }
        return null;
    }
}
