package com.utcn.assignment1.repository;

import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimestampRepository extends JpaRepository<Timestamp, Long> {
    List<Timestamp> findByDevice(Device device);
}
