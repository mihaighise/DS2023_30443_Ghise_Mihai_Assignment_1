package com.utcn.assignment1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timestamp")
@EqualsAndHashCode(exclude = "device")
public class Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_device")
    @JsonBackReference
    private Device device;

    @JsonFormat(pattern = "yyyy MM dd HH:mm")
    private LocalDateTime time;

    private float consumption;
}
