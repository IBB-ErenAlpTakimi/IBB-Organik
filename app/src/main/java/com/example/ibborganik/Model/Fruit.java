package com.example.ibborganik.Model;

public class Fruit {
    private String UrunAd;
    private String BirimTurId;
    private String KategoriId;
    private String EnDusukFiyat;
    private String EnYuksekFiyat;
    private String GuneAit;
    private String HalTurId;

    public Fruit(String urunAd, String birimTurId, String kategoriId, String enDusukFiyat,
                 String enYuksekFiyat, String guneAit, String halTurId){
        UrunAd = urunAd;
        BirimTurId = birimTurId;
        KategoriId = kategoriId;
        EnDusukFiyat = enDusukFiyat;
        EnYuksekFiyat = enYuksekFiyat;
        GuneAit = guneAit;
        HalTurId = halTurId;
    }

    public String getUrunAd() {
        return UrunAd;
    }

    public String getBirimTurId() {
        return BirimTurId;
    }

    public String getKategoriId() {
        return KategoriId;
    }

    public String getEnDusukFiyat() {
        return EnDusukFiyat;
    }

    public String getEnYuksekFiyat() {
        return EnYuksekFiyat;
    }

    public String getGuneAit() {
        return GuneAit;
    }

    public String getHalTurId() {
        return HalTurId;
    }
}
/*
"TabelaGId": "d85e962d-18dc-4a76-8a30-4bf49b8e88df",
      "UrunAd": "Armut (Deveci)",
      "BirimTurId": 1,
      "KategoriId": 5,
      "EnDusukFiyat": 10,
      "EnYuksekFiyat": 15,
      "GuneAit": "2022-05-11T00:00:00",
      "HalTurId": 2
*/