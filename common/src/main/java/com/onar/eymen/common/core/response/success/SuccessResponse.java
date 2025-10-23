package com.onar.eymen.common.core.response.success;

import static com.onar.eymen.common.core.constant.Types.Response.SUCCESS;

import com.onar.eymen.common.core.response.common.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> extends Response {
  private int size;
  private T data;

  public SuccessResponse(String code, String message, int size, T data) {
    super(SUCCESS, code, message, true);
    this.size = size;
    this.data = data;
  }
}
