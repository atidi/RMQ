package com.ihost.controller;

import com.ihost.messaging.MessageSender;
import com.ihost.model.RandomIntObject;
import com.ihost.model.Transfer;
import com.ihost.service.RandomIntService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ihost.messaging.Queues.QUEUE_BACK_SERVICE;
import static com.ihost.utils.Utils.generateRandomInt;

@RestController
public class SimpRestController {

    private static final Logger LOG = LoggerFactory.getLogger(SimpRestController.class);

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RandomIntService service;

    @GetMapping("/random")
    public ResponseEntity<Void> random() {
        LOG.info("Generating random Integers");
        RandomIntObject obj = new RandomIntObject(generateRandomInt());
        service.save(obj);
        messageSender.sendMessage(QUEUE_BACK_SERVICE, new Transfer(obj.getId()));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
