package com.mmnet.websocket.controllers;

import com.mmnet.websocket.servcie.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    /**
     * GET  /  -> show the index page.
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @Autowired
    private SimpMessagingTemplate template;

    /**
     * POST  /some-action  -> do an action.
     * <p>
     * After the action is performed will be notified UserA.
     */
    @RequestMapping(value = "/some-action", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> someAction() {
        // Return an http 200 status code
        this.template.convertAndSend("/topic/messages", new Notification("Hello"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

} // class MainController
