package com.AtomEdition.HitTheNail.service;

import android.content.Context;
import android.content.ContextWrapper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by FruityDevil on 29.10.2015.
 */

public class AdService extends ContextWrapper {

    private static AdService instance;
    private InterstitialAd interstitialAd;
    private boolean adWatched;

    private AdService(Context context){
        super(context);
        setAd();
    }

    public static AdService getInstance(Context context){
        if (instance == null) {
            instance = new AdService(context);
        }
        return instance;
    }

    public void setAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9550981282535152/8512948227");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                displayInterstitial();
            }
        });
    }

    public void displayInterstitial() {
        if (interstitialAd.isLoaded() && !adWatched) {
            interstitialAd.show();
            adWatched = true;
        }
    }
}
