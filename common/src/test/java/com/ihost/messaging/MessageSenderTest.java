package com.ihost.messaging;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSenderTest {

    @Autowired
    private MessageSender sender;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void onlyMessageSenderIsLoaded(){
        assertThat(sender).isNotNull();
    }

    @Test
    public void whenSendMessage_thenDoNothing() {
        doNothing()
                .when(rabbitTemplate)
                .convertAndSend(eq("test"),any(Object.class));
        sender.sendMessage("test",new Object());
        verify(rabbitTemplate, times(1)).convertAndSend(eq("test"),any(Object.class));
    }

}
