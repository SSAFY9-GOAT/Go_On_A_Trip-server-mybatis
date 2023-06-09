package com.ssafy.goat.attraction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Sido {

    private Integer code;
    private String name;

    @Builder
    public Sido(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
