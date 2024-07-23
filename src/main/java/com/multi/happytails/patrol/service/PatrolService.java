package com.multi.happytails.patrol.service;

import com.multi.happytails.patrol.model.dao.PatrolDAO;
import com.multi.happytails.patrol.model.dto.PatrolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatrolService {

    @Autowired
    PatrolDAO patrolDAO;


    public int patrolInsert(PatrolDTO patrolDTO){
        return patrolDAO.patrolInsert(patrolDTO);
    }

    public List<PatrolDTO> findAllPatrol() {
        return patrolDAO.findAllPatrol();
    }

    public PatrolDTO findOnePatrol(int userNo) {
        return patrolDAO.findOnePatrol(userNo);
    }

    public int patrolUpdate(PatrolDTO patrolDTO) {
        return patrolDAO.patrolUpdate(patrolDTO);
    }

    public int patrolDelete(int pnum) {
        return patrolDAO.patrolDelete(pnum);
    }
}
