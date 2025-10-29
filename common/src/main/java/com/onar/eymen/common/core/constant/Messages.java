package com.onar.eymen.common.core.constant;

public final class Messages {
  private Messages() {}

  public static final class Product {
    private Product() {}

    public static final String PRODUCT_ALREADY_EXIST = "Böyle bir ürün bulunmaktadır.";
    public static final String SAVED = "Ürün başarıyla kaydedildi.";
    public static final String UPDATED = "Ürün başarıyla güncellendi";
    public static final String PRODUCT_NOT_FOUND = "Ürün bulunamadı";
    public static final String PRODUCT_FOUND = "Ürün bulundu";
  }

  public static final class User {
    private User() {}

    public static final String NOT_FOUND = "Herhangi bir kullanıcı bulunamadı.";
    public static final String FOUND = "Kullanıcı bulundu.";
    public static final String SAVED = "Kullanıcı başarıyla kaydedildi.";
    public static final String UPDATED = "Kullanıcı başarıyla güncellendi.";
    public static final String DELETED = "Kullanıcı başarıyla silindi.";
    public static final String PASSWORD_REQUIREMENTS =
        "Şifre büyük/küçük harf, rakam ve özel karakter içermelidir.";
    public static final String BREACHED_PASSWORD =
        "Şifre güvenli değil, lütfen daha güçlü bir şifre kullanın.";
    public static final String OLD_PASSWORD_MISMATCH = "Eski şifre hatalı.";
    public static final String EMAIL_ALREADY_EXISTS = "Bu email adresi zaten kayıtlı.";
  }

  public static final class Role {
    private Role() {}

    public static final String NOT_FOUND = "Rol bulunamadı.";
  }

  public static final class Error {
    public static final String UNEXPECTED =
        "Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.";
    public static final String INVALID_ARGUMENT =
        "Geçersiz argüman. Lütfen doğru bilgileri giriniz.";
    public static final String NO_SUCH_ELEMENT = "Herhangi bir eleman bulunamadı.";
    public static final String UNSUPPORTED_OPERATION = "Desteklenmeyen işlem.";

    private Error() {}
  }
}
