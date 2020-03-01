package com.test.controller;

import com.test.messaging.MessageSender;
import com.test.model.RandomIntObject;
import com.test.model.Transfer;
import com.test.service.RandomIntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.test.messaging.Queues.QUEUE_BACK_SERVICE;
import static com.test.utils.Utils.generateRandomInt;

@RestController
public class SimpRestController {

    private static final Logger LOG = LoggerFactory.getLogger(SimpRestController.class);

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RandomIntService service;

    @GetMapping("/random")
    public void random() {
        LOG.info("Generating random Integers");
        RandomIntObject obj = new RandomIntObject(generateRandomInt());
        service.save(obj);
        messageSender.sendMessage(QUEUE_BACK_SERVICE, new Transfer(obj.getId()));
    }


}
