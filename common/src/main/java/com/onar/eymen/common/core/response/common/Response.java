package com.onar.eymen.common.core.response.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onar.eymen.common.core.util.TraceIdGenerator;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Response {
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  protected LocalDateTime timestamp;

  protected String traceId;
  protected String type;
  protected String code;
  protected Object message;
  protected boolean success;

  protected Response(String type, String code, Object message, boolean success) {
    this.timestamp = LocalDateTime.now();
    this.traceId = TraceIdGenerator.generate();
    this.type = type;
    this.code = code;
    this.message = message;
    this.success = success;
  }

  protected Response(String type, String code, Object message, boolean success, String traceId) {
    this.timestamp = LocalDateTime.now();
    this.traceId = traceId;
    this.type = type;
    this.code = code;
    this.message = message;
    this.success = success;
  }
}
