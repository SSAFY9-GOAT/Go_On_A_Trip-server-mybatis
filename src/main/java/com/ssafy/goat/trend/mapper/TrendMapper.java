package com.ssafy.goat.trend.mapper;

import com.ssafy.goat.trend.Trend;
import com.ssafy.goat.trend.dto.TrendViewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface TrendMapper {

    int save(int contentId);

    Optional<Trend> findByContentId(int contentId);

    TrendViewDto findPopularByTeenage();

    TrendViewDto findPopularByTwenty();

    TrendViewDto findPopularByThirty();

    TrendViewDto findPopularByMale();

    TrendViewDto findPopularByFemale();

    int update(Trend trend);
}
