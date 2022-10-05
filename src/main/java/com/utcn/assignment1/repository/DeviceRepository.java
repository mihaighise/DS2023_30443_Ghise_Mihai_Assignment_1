package com.utcn.assignment1.repository;

import com.utcn.assignment1.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
