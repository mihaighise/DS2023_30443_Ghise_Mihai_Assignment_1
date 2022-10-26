package com.utcn.assignment1.model.dto;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.User;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class DeviceDTO {

    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    private String address;

    private Float maximumEnergy;
}
