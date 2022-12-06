package com.utcn.assignment1.rabbitmq;

import com.google.gson.Gson;
import com.utcn.assignment1.model.Device;
import com.utcn.assignment1.model.Timestamp;
import com.utcn.assignment1.model.dto.DeviceDTO;
import com.utcn.assignment1.model.mapper.DeviceMapper;
import com.utcn.assignment1.repository.DeviceRepository;
import com.utcn.assignment1.service.DeviceService;
import com.utcn.assignment1.service.TimestampService;
import com.utcn.assignment1.websockets.WebSocketMessage;
import com.utcn.assignment1.websockets.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Receiver {

    @Autowired
    private final DeviceMapper deviceMapper;

    @Autowired
    private final DeviceService deviceService;

    @Autowired
    private final TimestampService timestampService;

    @Autowired
    private final WebSocketService webSocketService;

    Float currentHourlyConsumption = 0F;
    Float previousMaxConsumption = 0F;
    ArrayList<CustomMessage> receivedMessages = new ArrayList<>();

    HashMap<Long, Float> currentConsumptions = new HashMap<>();
    HashMap<Long, Float> previousConsumptions = new HashMap<>();
    HashMap<Long, ArrayList<CustomMessage>> allReceivedMessages = new HashMap<>();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @RabbitListener(queues = "hello")
    public void receiveMessage(final String message) {
        CustomMessage customMessage = new Gson().fromJson(message, CustomMessage.class);
        receivedMessages.add(customMessage);

        if(receivedMessages.size() >= 2) {
            CustomMessage messageToSave = receivedMessages.get(receivedMessages.size() - 2);
            if(LocalDateTime.parse(receivedMessages.get(receivedMessages.size() - 1).getTimestamp()).getMinute() !=
                    LocalDateTime.parse(receivedMessages.get(receivedMessages.size() - 2).getTimestamp()).getMinute()) {
                DeviceDTO device = deviceService.findById(messageToSave.id_device);
                LocalDateTime parsedDate = LocalDateTime.parse(messageToSave.getTimestamp());
                Timestamp timestamp = new Timestamp(null, deviceMapper.convertToEntity(device), parsedDate, currentHourlyConsumption);
                timestampService.saveTimestamp(timestamp);
                if(currentHourlyConsumption - previousMaxConsumption > device.getMaximumEnergy()) {
                    webSocketService.sendNotification(1L, new WebSocketMessage("You have surpassed the maximum energy consumption for device." +
                            device.getId() +
                            "Value registered is: " + (currentHourlyConsumption - previousMaxConsumption) + ", while maximum energy for this device is: " +
                            device.getMaximumEnergy().toString()));
                }
                previousMaxConsumption = currentHourlyConsumption;
            }
        }
        currentHourlyConsumption = customMessage.getValue();
        System.out.println("Received message and deserialized to 'CustomMessage': {}" + customMessage.toString());
    }
}
