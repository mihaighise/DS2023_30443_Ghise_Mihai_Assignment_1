package com.utcn.assignment1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
@EqualsAndHashCode(exclude = "user")
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private String address;

    private Float maximumEnergy;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;
}
