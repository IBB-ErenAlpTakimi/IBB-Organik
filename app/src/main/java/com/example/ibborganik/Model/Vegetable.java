package com.example.ibborganik.Model;

import java.util.Map;

public class Vegetable {
    private String UrunAd;
    private String BirimTurId;
    private String KategoriId;
    private String EnDusukFiyat;
    private String EnYuksekFiyat;
    private String GuneAit;
    private String HalTurId;

    public Vegetable(String urunAd, String birimTurId, String kategoriId, String enDusukFiyat,
                     String enYuksekFiyat, String guneAit, String halTurId) {
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
