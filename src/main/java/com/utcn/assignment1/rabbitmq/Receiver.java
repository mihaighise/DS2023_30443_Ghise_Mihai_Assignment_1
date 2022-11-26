package com.utcn.assignment1.rabbitmq;

import com.google.gson.Gson;
import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.Timestamp;
import com.utcn.assignment1.repository.DeviceRepository;
import com.utcn.assignment1.service.TimestampService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Receiver {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    private final TimestampService timestampService;

    ArrayList<CustomMessage> receivedMessages = new ArrayList<>();

    Float currentHourlyConsumption = 0F;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @RabbitListener(queues = "hello")
    public void receiveMessage(final String message) {
        CustomMessage customMessage = new Gson().fromJson(message, CustomMessage.class);
        receivedMessages.add(customMessage);
        if(receivedMessages.size() >= 2) {
            CustomMessage messageToSave = receivedMessages.get(receivedMessages.size() - 2);
            if(LocalDateTime.parse(receivedMessages.get(receivedMessages.size() - 1).getTimestamp()).getMinute() !=
                    LocalDateTime.parse(receivedMessages.get(receivedMessages.size() - 2).getTimestamp()).getMinute()) {
                //TODO sent to database the currentHourlyConsumption and reset it
                Optional<Device> device = deviceRepository.findById(messageToSave.id_device);
                System.out.println(device);
                LocalDateTime parsedDate = LocalDateTime.parse(messageToSave.getTimestamp(), formatter);
                System.out.println(parsedDate);
                Timestamp timestamp = new Timestamp(null, device.get(), parsedDate, messageToSave.getValue());
                System.out.println(timestamp);
                timestampService.saveTimestamp(timestamp);
                System.out.println("saved");
            }
        }
        System.out.println("Received message and deserialized to 'CustomMessage': {}" + customMessage.toString());
    }
}
