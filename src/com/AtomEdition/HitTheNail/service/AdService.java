package com.AtomEdition.HitTheNail.service;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import com.AtomEdition.HitTheNail.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by FruityDevil on 29.10.2015.
 */

public class AdService {

    private static AdService instance;
    private InterstitialAd interstitialAd;
    private boolean adWatched;

    private AdService(){
    }

    public static AdService getInstance(){
        if (instance == null) {
            instance = new AdService();
        }
        return instance;
    }

    public void showBanner(Activity activity){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            AdView adView = (AdView) activity.findViewById(R.id.adView);
            adView.loadAd(new AdRequest.Builder().build());
        }
    }

    public void setAd(Context context) {
        interstitialAd = new InterstitialAd(context);
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
