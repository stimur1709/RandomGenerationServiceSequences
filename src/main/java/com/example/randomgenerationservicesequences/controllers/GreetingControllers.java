package com.example.randomgenerationservicesequences.controllers;

import com.example.randomgenerationservicesequences.models.ResponseMessage;
import com.example.randomgenerationservicesequences.models.RequestMessage;
import com.example.randomgenerationservicesequences.models.SequenceNumber;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingControllers {

    private SequenceNumber sequenceNumber;

    @MessageMapping("/hello")
    @SendTo("/topic/result")
    public ResponseMessage result(RequestMessage requestMessage) {
        sequenceNumber = new SequenceNumber(Integer.parseInt(requestMessage.getSize()));
        return new ResponseMessage(HtmlUtils.htmlEscape("Последовательность сгенерирована"));
    }

    @MessageMapping("/generate")
    @SendTo("/topic/result")
    public ResponseMessage generate() {
        return new ResponseMessage(HtmlUtils.htmlEscape(sequenceNumber.getGeneratedSequenceListOf(6).toString()));
    }
}
