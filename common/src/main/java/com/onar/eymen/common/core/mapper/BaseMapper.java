package com.onar.eymen.common.core.mapper;

public interface BaseMapper<RESPONSE, ENTITY> {
  RESPONSE toResponse(ENTITY entity);

  ENTITY toEntity(RESPONSE response);
}
