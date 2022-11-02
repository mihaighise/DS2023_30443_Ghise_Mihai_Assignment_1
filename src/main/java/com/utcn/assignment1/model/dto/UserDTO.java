package com.utcn.assignment1.model.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class UserDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String username;
    private String userRole;
    private Set<DeviceDTO> devices;
}
