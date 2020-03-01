package com.test.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.model.RandomIntObject;
import com.test.model.Transfer;
import com.test.service.RandomIntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.test.messaging.Queues.*;

@Component
public class MessageHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MessageHandler.class);


    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RandomIntService service;


    @RabbitListener(queues = QUEUE_BACK_SERVICE)
    public void handleSimpleMessage(@Payload Transfer event) throws JsonProcessingException {
        RandomIntObject obj = service.get(event.getId());
        LOG.info("Get RandomIntObject from id" + event.getId());
        messageSender.sendMessage(QUEUE_REST_SERVICE, obj);
    }
}