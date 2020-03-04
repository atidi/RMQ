package com.ihost.controller;


import com.ihost.Application;
import com.ihost.messaging.MessageSender;
import com.ihost.model.RandomIntObject;
import com.ihost.model.Transfer;
import com.ihost.service.RandomIntService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.ihost.messaging.Queues.QUEUE_BACK_SERVICE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class SimpRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpRestController controller;

    @MockBean
    private MessageSender messageSender;

    @MockBean
    private RandomIntService service;

    @Test
    public void onlySimpControllerIsLoaded(){
        assertThat(controller).isNotNull();
    }

    @Test
    public void shouldReturnNoContent() throws Exception {
        when(service.save(any(RandomIntObject.class))).thenReturn(new RandomIntObject());
        this.mockMvc.perform(get("/random")).andDo(print()).andExpect(status().isNoContent());
        verify(service, times(1)).save(any(RandomIntObject.class));
        verify(messageSender, times(1)).sendMessage(eq(QUEUE_BACK_SERVICE),any(Transfer.class));
    }


}