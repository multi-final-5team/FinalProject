package com.multi.happytails.patrol.model.dao;

import com.multi.happytails.patrol.model.dto.PatrolDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatrolDAO {

    public int patrolInsert(PatrolDTO patrolDTO);

    List<PatrolDTO> findAllPatrol();

    PatrolDTO findOnePatrol(int userNo);

    int patrolUpdate(PatrolDTO patrolDTO);

    int patrolDelete(int pnum);
}
