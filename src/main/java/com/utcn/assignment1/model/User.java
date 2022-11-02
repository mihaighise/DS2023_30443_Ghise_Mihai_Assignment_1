package com.utcn.assignment1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = "devices")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    
    private String username;

    private String password;

    private String userRole;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Device> devices;

    public void addDevice(Device device) {
        if(device != null) {
            this.devices.add(device);
        }
    }
}
