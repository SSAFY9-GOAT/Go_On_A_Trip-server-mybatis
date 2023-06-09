package com.ssafy.goat.common.validation;

import com.ssafy.goat.common.validation.dto.InvalidResponse;
import com.ssafy.goat.common.validation.dto.NotionRequest;
import com.ssafy.goat.common.validation.validator.NotionValidator;
import com.ssafy.goat.common.validation.validator.notion.ContentValidator;
import com.ssafy.goat.common.validation.validator.notion.TitleValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NotionValidation {

    private final List<NotionValidator> validators;

    public NotionValidation() {
        validators = new ArrayList<>();
        validators.add(new TitleValidator());
        validators.add(new ContentValidator());
    }

    public List<InvalidResponse> validate(NotionRequest request) {
        if (request == null) {
            return Collections.singletonList(new InvalidResponse("request", "유효하지 않은 요청"));
        }

        return validators.stream()
                .map(validator -> validator.validate(request))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
