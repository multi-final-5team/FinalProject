package com.multi.happytails.authentication.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface AuthenticationService extends UserDetailsService {

    Map<String, List<String>> getPermitListMap();

}
