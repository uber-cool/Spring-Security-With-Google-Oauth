package com.throughyears.controller;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    private TokenStore tokenStore;


    @GetMapping("/home")
    public String dashboard(HttpServletRequest request, Model model, OAuth2Authentication principal) {
        model.addAttribute("principal", principal);
        log.debug("User details: {}", principal.getUserAuthentication().getDetails());
        return "dashboard";
    }


    @PostMapping("/goway")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            try {
                String tokenValue = authHeader.replace("Bearer", "").trim();
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                tokenStore.removeAccessToken(accessToken);
            } catch (Exception e) {
                log.error("Exception during logout: ", e);
                return new ResponseEntity<>("Logout failed: "+e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>("Logout success", HttpStatus.OK);
    }

}