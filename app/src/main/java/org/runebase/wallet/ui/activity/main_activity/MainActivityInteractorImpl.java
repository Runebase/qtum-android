package org.runebase.wallet.ui.activity.main_activity;

import android.content.Context;

import org.runebase.wallet.dataprovider.rest_api.runebase.RunebaseService;
import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSettingSharedPreference;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;
import org.runebase.wallet.datastorage.listeners.LanguageChangeListener;
import org.runebase.wallet.model.gson.DGPInfo;
import org.runebase.wallet.model.gson.FeePerKb;

import java.math.BigDecimal;

import rx.Observable;

class MainActivityInteractorImpl implements MainActivityInteractor {

    private Context mContext;

    private boolean isDGPInfoLoaded = false;
    private boolean isFeePerkbLoaded = false;

    MainActivityInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public boolean getKeyGeneratedInstance() {
        return RunebaseSharedPreference.getInstance().getKeyGeneratedInstance(mContext);
    }

    @Override
    public void clearStatic() {
        KeyStorage.getInstance().clearKeyStorage();
    }

    @Override
    public Observable<DGPInfo> getDGPInfo() {
        return RunebaseService.newInstance().getDGPInfo();
    }

    @Override
    public boolean isDGPInfoLoaded() {
        return isDGPInfoLoaded;
    }

    @Override
    public boolean isFeePerkbLoaded() {
        return isFeePerkbLoaded;
    }

    @Override
    public void addLanguageChangeListener(LanguageChangeListener languageChangeListener) {
        RunebaseSettingSharedPreference.getInstance().addLanguageListener(languageChangeListener);
    }

    @Override
    public void removeLanguageChangeListener(LanguageChangeListener languageChangeListener) {
        RunebaseSettingSharedPreference.getInstance().removeLanguageListener(languageChangeListener);
    }

    @Override
    public Observable<FeePerKb> getFeePerKb() {
        return RunebaseService.newInstance().getEstimateFeePerKb(2);
    }

    @Override
    public void setDGPInfo(DGPInfo dgpInfo) {
        isDGPInfoLoaded = true;
        RunebaseSharedPreference.getInstance().setMinGasPrice(mContext, dgpInfo.getMingasprice());
    }

    @Override
    public void setFeePerKb(FeePerKb feePerKb) {
        isFeePerkbLoaded = true;
        RunebaseSettingSharedPreference runebaseSettingSharedPreference = RunebaseSettingSharedPreference.getInstance();
        runebaseSettingSharedPreference.setFeePerKb(mContext, feePerKb.getFeePerKb().setScale(5, BigDecimal.ROUND_HALF_DOWN).toPlainString());
    }
}
