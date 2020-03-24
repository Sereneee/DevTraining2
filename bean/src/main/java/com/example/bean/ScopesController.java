package com.example.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ScopesController {

    public static final Logger LOG = LoggerFactory.getLogger(ScopesController.class);

    // for request scope
    @Resource(name = "requestScopedBean")
    HelloMessageGenerator requestScopedBean;

    @RequestMapping("/scopes/request")
    public String getRequestScopeMessage(final Model model) {
        String str = java.time.LocalDateTime.now() + "<br>";

        model.addAttribute("previousMessage", requestScopedBean.getMessage());
        str += requestScopedBean.getMessage() + "<br>";

        requestScopedBean.setMessage("Good morning!");

        model.addAttribute("currentMessage", requestScopedBean.getMessage());
        str += requestScopedBean.getMessage();

        return str;
    }

    // for session scope
    @Resource(name = "sessionScopedBean")
    HelloMessageGenerator sessionScopedBean;

    @RequestMapping("/scopes/session")
    public String getSessionScopeMessage(final Model model) {
        String str = java.time.LocalDateTime.now() + "<br>";

        model.addAttribute("previousMessage", sessionScopedBean.getMessage());
        str += sessionScopedBean.getMessage() + "<br>";

        sessionScopedBean.setMessage("Good afternoon!");

        model.addAttribute("currentMessage", sessionScopedBean.getMessage());
        str += sessionScopedBean.getMessage() + "<br>";

        return str;
    }

    // for application scope
    @Resource(name = "applicationScopedBean")
    HelloMessageGenerator applicationScopedBean;

    @RequestMapping("/scopes/application")
    public String getApplicationScopeMessage(final Model model) {
        String str = java.time.LocalDateTime.now() + "<br>";

        model.addAttribute("previousMessage", applicationScopedBean.getMessage());
        str += applicationScopedBean.getMessage() + "<br>";

        applicationScopedBean.setMessage("Good night!");

        model.addAttribute("currentMessage", applicationScopedBean.getMessage());
        str += applicationScopedBean.getMessage() + "<br>";

        return str;
    }

    // for websocket scope
    @Resource(name = "websocketScopedBean")
    HelloMessageGenerator websocketScopedBean;

    @RequestMapping("/scopes/websocket")
    public String getWebSocketScopeMessage(final Model model) {
        String str = java.time.LocalDateTime.now() + "<br>";

        model.addAttribute("previousMessage", websocketScopedBean.getMessage());
        str += websocketScopedBean.getMessage() + "<br>";

        websocketScopedBean.setMessage("Good day to you!");

        model.addAttribute("currentMessage", websocketScopedBean.getMessage());
        str += websocketScopedBean.getMessage() + "<br>";

        return str;
    }

}
