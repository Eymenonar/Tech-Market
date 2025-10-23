package com.onar.eymen.common.core.enums;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Languages {
  TR_CREATION(
      Arrays.asList(
          "kaydedildi",
          "kayıt",
          "kaydet",
          "oluşturuldu",
          "oluşturmak",
          "ekledi",
          "ekleme",
          "yeni ekleme",
          "yeni kayıt",
          "yeni oluşturma",
          "eklendi",
          "ekle",
          "ekleme işlemi")),
  EN_CREATION(
      Arrays.asList(
          "saved",
          "save",
          "insert",
          "created",
          "create",
          "added",
          "add",
          "adding",
          "new add",
          "new record")),
  FR_CREATION(
      Arrays.asList(
          "enregistré",
          "enregistrer",
          "insérer",
          "créé",
          "créer",
          "ajouté",
          "ajouter",
          "ajout",
          "nouvel ajout",
          "nouvel enregistrement")),
  TR_MODIFICATION(
      Arrays.asList(
          "güncellendi",
          "güncelle",
          "güncelleme",
          "değiştirildi",
          "değiştirme",
          "değiştir",
          "yenilendi",
          "yenileme",
          "yenile"));

  private final List<String> keywords;
}
