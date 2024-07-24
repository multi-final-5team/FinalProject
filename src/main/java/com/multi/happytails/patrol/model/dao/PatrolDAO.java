package com.multi.happytails.patrol.model.dao;

import com.multi.happytails.patrol.model.dto.PatrolDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PatrolDAO {

    int patrolInsert(PatrolDTO patrolDTO);

    List<PatrolDTO> findAllPatrol();

    PatrolDTO findOnePatrol(@Param("userNo") int userNo,@Param("name") String name);

    PatrolDTO findOnePatrolByPatrolNo(int parolNo);

    int patrolUpdate(PatrolDTO patrolDTO);

    int patrolDelete(int pnum);
}
