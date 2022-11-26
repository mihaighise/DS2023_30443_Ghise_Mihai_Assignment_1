package com.utcn.assignment1.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessage {

    String timestamp;

    Long id_device;

    Float value;
}
