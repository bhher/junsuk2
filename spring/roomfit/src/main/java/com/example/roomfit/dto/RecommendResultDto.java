package com.example.roomfit.dto;

import com.example.roomfit.domain.InteriorStyle;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// RecommendEngine 이 계산한 결과를 컨트롤러 -> 타임리프로 넘기는 한 덩어리 응답모델
public class RecommendResultDto {

    private final List<ScoredPostDto> posts;
    private final List<String> colorPalette; //(hex 색 4개 - 스타일별 )
    private final String layoutAdvice; //배치및 생활패턴 문구
    private final InteriorStyle preferredStyle; //내선호 스타일
}