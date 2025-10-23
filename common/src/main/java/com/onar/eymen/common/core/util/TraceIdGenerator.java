package com.onar.eymen.common.core.util;

import static com.onar.eymen.common.core.constant.Messages.Error.UNSUPPORTED_OPERATION;

import java.util.UUID;
import org.slf4j.MDC;

public final class TraceIdGenerator {
  private static final String TRACE_ID_KEY = "traceId";

  private TraceIdGenerator() {
    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION);
  }

  public static String generate() {
    String traceId = UUID.randomUUID().toString();
    MDC.put(TRACE_ID_KEY, traceId);

    return traceId;
  }

  public static String get() {
    return MDC.get(TRACE_ID_KEY);
  }
}
