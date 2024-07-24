package com.multi.happytails.community.model.dao;

import com.multi.happytails.community.model.dto.DogloveDTO;
import com.multi.happytails.help.model.dto.HelpCategoryDto;
import com.multi.happytails.help.model.dto.InquiryDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * The interface Doglove dao.
 */
@Mapper
public interface DogloveDAO {

    /**
     * methodName : findAll
     * author : Nayoung Yeo
     * description :
     *
     * @param sortOrder order
     * @return list
     */
    List<DogloveDTO> findAll(String sortOrder);

    /**
     * methodName : findById
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveNo no
     * @return doglove dto
     */
    DogloveDTO findById(Long dogloveNo);

    /**
     * methodName : update
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveDTO dto
     */
    void update(DogloveDTO dogloveDTO);

    /**
     * methodName : delete
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveNo no
     */
    void delete(Long dogloveNo);

    /**
     * methodName : dogloveInsert
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveDTO dto
     */
    void dogloveInsert(DogloveDTO dogloveDTO);

    /**
     * Gets current doglove no.
     *
     * @return the current doglove no
     */
    public int getCurrentDogloveNo();

    /**
     * methodName : incrementRecommendCount
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveNo no
     */
    void incrementRecommendCount(Long dogloveNo);

    /**
     * methodName : decrementRecommendCount
     * author : Nayoung Yeo
     * description :
     *
     * @param dogloveNo no
     */
    void decrementRecommendCount(Long dogloveNo);
}
