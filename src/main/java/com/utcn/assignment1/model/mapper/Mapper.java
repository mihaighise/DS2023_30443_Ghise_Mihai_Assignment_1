package com.utcn.assignment1.model.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Mapper<Source, Target> {

    Target convertToDTO(Source source);

    Source convertToEntity(Target target);

    default List<Target> convertAllToDTO(List<Source> source) {
        return source.stream()
                .map(this::convertToDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    default List<Source> convertAllToEntity(List<Target> target) {
        return target.stream()
                .map(this::convertToEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
