package com.utcn.assignment1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
@EqualsAndHashCode(exclude = {"user", "timestamps"})
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

    @OneToMany
    @JsonManagedReference
    private List<Timestamp> timestamps;
}
