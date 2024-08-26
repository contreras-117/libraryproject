package com.libraryproject.springbootlibrary.controller;

import com.libraryproject.springbootlibrary.controller.model.AdminQuestionRequest;
import com.libraryproject.springbootlibrary.entity.Message;
import com.libraryproject.springbootlibrary.service.MessagesService;
import com.libraryproject.springbootlibrary.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

    private MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token,
                            @RequestBody Message messageRequest) {
        String userEmail = ExtractJWT.payloadExtraction(token, "\"sub\"");
        messagesService.postMessage(messageRequest, userEmail);
    }

    @PutMapping("/secure/admin/messages")
    public void putMessages(@RequestHeader(value = "Authorization") String token,
                            @RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
        String userEmail = ExtractJWT.payloadExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadExtraction(token, "\"userType\"");

        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administration page only.");
        }

        messagesService.putMessage(adminQuestionRequest, userEmail);
    }
}
