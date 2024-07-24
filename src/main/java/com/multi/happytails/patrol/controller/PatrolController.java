package com.multi.happytails.patrol.controller;


import com.multi.happytails.patrol.model.dto.PatrolDTO;
import com.multi.happytails.patrol.service.PatrolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patrol")
public class PatrolController {

    @Autowired
    PatrolService patrolService;

    @RequestMapping("patrol")
    public void patrol(){

    }

    @RequestMapping("patrolinsert")
    public void patrolinsert(){

    }

    @RequestMapping("patrolview")
    public void patrolview(){

    }

    @PostMapping("makepatrol")
    public String makepatrol(PatrolDTO patrolDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        patrolService.patrolInsert(patrolDTO);

        return "patrol/patrol";
    }

    @GetMapping(value="findAllPatrol", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PatrolDTO> findAllPatrol(){

        List<PatrolDTO> list = patrolService.findAllPatrol();
        return list;
    }

    @GetMapping(value="findOnePatrol", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public PatrolDTO findOnePatrol(){
        int userNo = 0;

        PatrolDTO patrolDTO = patrolService.findOnePatrol(userNo);

        return patrolDTO;
    }

    @PostMapping("patrolUpdate")
    public String patrolUpdate(PatrolDTO patrolDTO){

        int result = patrolService.patrolUpdate(patrolDTO);

        return "patrol/patrol";
    }

    @PostMapping("patrolDelete")
    public String patrolDelete(PatrolDTO patrolDTO){
        int pnum = patrolDTO.getPatrolNo();

        int result = patrolService.patrolDelete(pnum);

        return "patrol/patrol";
    }
}
