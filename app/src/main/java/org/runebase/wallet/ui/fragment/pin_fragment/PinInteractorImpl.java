package org.runebase.wallet.ui.fragment.pin_fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import org.runebase.wallet.R;
import org.runebase.wallet.utils.CryptoUtils;
import org.runebase.wallet.utils.CryptoUtilsCompat;
import org.runebase.wallet.utils.crypto.AESUtil;
import org.runebase.wallet.utils.crypto.KeyStoreHelper;
import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;

import java.util.concurrent.Callable;

import javax.crypto.Cipher;

import rx.Observable;

import static org.runebase.wallet.utils.crypto.KeyStoreHelper.trimEndSpaces;

class PinInteractorImpl implements PinInteractor {

    private Context mContext;
    private final String RUNEBASE_PIN_ALIAS = "runebase_alias";

    PinInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public String getPassword() {
        String sixDigitPassword = getSixDigitPassword();
        if (!TextUtils.isEmpty(sixDigitPassword)) {
            return trimEndSpaces(sixDigitPassword);
        } else {
            return trimEndSpaces(getFourDigitPassword());
        }
        //delete 4 spaces because after API 18 android have bug
        //(https://stackoverflow.com/questions/34472004/android-preferences-adding-unwanted-chars)
    }

    @Override
    public void savePassword(String password) {
        saveSixDigitPassword(password);
    }


    private String getFourDigitPassword() {
        String encryptedPinHash = RunebaseSharedPreference.getInstance().getPassword(mContext);
        return KeyStoreHelper.decrypt(RUNEBASE_PIN_ALIAS, encryptedPinHash);
    }

    @Override
    public String getSixDigitPassword() {
        return RunebaseSharedPreference.getInstance().getSixDigitPassword(mContext);
    }

    @Override
    public Integer getFailedAttemptsCount() {
        return RunebaseSharedPreference.getInstance().getFailedAttemptsCount(mContext);
    }

    @Override
    public Long getBanTime() {
        return RunebaseSharedPreference.getInstance().getBanTime(mContext);
    }

    @Override
    public void setFailedAttemptsCount(int failedAttemptsCount) {
        RunebaseSharedPreference.getInstance().setFailedAttemptsCount(mContext, failedAttemptsCount);
    }

    @Override
    public void setBanTime(long banTime) {
        RunebaseSharedPreference.getInstance().setBanTime(mContext, banTime);
    }

    @Override
    public void saveSixDigitPassword(String password) {
        RunebaseSharedPreference.getInstance().saveSixDigitPassword(mContext, password);
    }

    @Override
    public void savePassphraseSaltWithPin(String pin, String passphrase) {
        byte[] saltPassphrase = AESUtil.encryptToBytes(pin, passphrase);
        String encryptedSaltPassphrase = Base64.encodeToString(saltPassphrase, Base64.DEFAULT);
        RunebaseSharedPreference.getInstance().saveSeed(mContext, encryptedSaltPassphrase);
    }

    @Override
    public byte[] getSaltPassphrase() {
        String encryptedSaltPassphrase = RunebaseSharedPreference.getInstance().getSeed(mContext);
        return Base64.decode(encryptedSaltPassphrase, Base64.DEFAULT);
    }

    @Override
    public String getTouchIdPassword() {
        return RunebaseSharedPreference.getInstance().getTouchIdPassword(mContext);
    }

    @Override
    public void saveTouchIdPassword(String password) {
        RunebaseSharedPreference.getInstance().saveTouchIdPassword(mContext, password);
    }

    @Override
    public String getRandomSeed(){
        return KeyStorage.getInstance().getRandomSeed();
    }

    @Override
    public void setKeyGeneratedInstance(boolean isKeyGenerated) {
        RunebaseSharedPreference.getInstance().setKeyGeneratedInstance(mContext, isKeyGenerated);
    }

    @Override
    public String decode(String encoded, Cipher cipher) {
        return CryptoUtils.decode(encoded, cipher);
    }

    @Override
    public Observable<String> encodeInBackground(String pin) {
        return CryptoUtils.encodeInBackground(pin);
    }

    @Override
    public String generateSHA256String(String pin) {
        return CryptoUtilsCompat.generateSHA256String(pin);
    }

    @Override
    public String getUnSaltPassphrase(String oldPin) {
        byte[] oldSaltPassphrase = getSaltPassphrase();
        return AESUtil.decryptBytes(oldPin, oldSaltPassphrase);
    }

    @Override
    public String getBanPinString(int min) {
        return mContext.getString(R.string.sorry_please_try_again_in) + " " + min + " " + mContext.getString(R.string.minutes);
    }

    @Override
    public Observable<String> loadWallet(final String code) {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return KeyStorage.getInstance().loadWallet(KeyStoreHelper.getSeed(mContext, code));
            }
        });
    }

}