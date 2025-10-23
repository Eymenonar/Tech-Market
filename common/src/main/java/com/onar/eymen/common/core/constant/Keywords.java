package com.onar.eymen.common.core.constant;

import static com.onar.eymen.common.core.enums.Languages.EN_CREATION;
import static com.onar.eymen.common.core.enums.Languages.TR_CREATION;

import java.util.ArrayList;
import java.util.List;

public final class Keywords {
  private Keywords() {}

  public static final List<String> creationKeywords = new ArrayList<>();

  static {
    creationKeywords.addAll(TR_CREATION.getKeywords());
    creationKeywords.addAll(EN_CREATION.getKeywords());
  }
}
