package com.utcn.assignment1.model.dto;

import lombok.*;

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
