package org.runebase.wallet.ui.fragment.pin_fragment;

import android.content.Context;

import org.runebase.wallet.R;
import org.runebase.wallet.utils.CryptoUtils;
import org.runebase.wallet.utils.CryptoUtilsCompat;
import org.runebase.wallet.utils.crypto.AESUtil;
import org.runebase.wallet.utils.crypto.KeyStoreHelper;
import org.runebase.wallet.datastorage.KeyStorage;
import org.runebase.wallet.datastorage.RunebaseSharedPreference;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;

import rx.Observable;

class PinInteractorImpl implements PinInteractor {

    private Context mContext;
    private final String RUNEBASE_PIN_ALIAS = "runebase_alias";

    PinInteractorImpl(Context context) {
        mContext = context;
        try {
            KeyStoreHelper.createKeys(mContext, RUNEBASE_PIN_ALIAS);
        } catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPassword() {
        String sixDigitPassword = getSixDigitPassword();
        if (!getSixDigitPassword().isEmpty()) {
            return sixDigitPassword;
        } else {
            return getFourDigitPassword();
        }
    }

    @Override
    public void savePassword(String password) {
        saveSixDigitPassword(password);
    }


    private String getFourDigitPassword() {
        String encryptedPinHash = RunebaseSharedPreference.getInstance().getPassword(mContext);
        return KeyStoreHelper.decrypt(RUNEBASE_PIN_ALIAS, encryptedPinHash);
    }

    private void saveFourDigitPassword(String password) {
        String encryptedPinHash = KeyStoreHelper.encrypt(RUNEBASE_PIN_ALIAS, password);
        RunebaseSharedPreference.getInstance().savePassword(mContext, encryptedPinHash);
    }

    @Override
    public String getSixDigitPassword() {
        String encryptedPinHash = RunebaseSharedPreference.getInstance().getSixDigitPassword(mContext);
        if (encryptedPinHash.isEmpty()) {
            return encryptedPinHash;
        }
        return KeyStoreHelper.decrypt(RUNEBASE_PIN_ALIAS, encryptedPinHash);
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
        String encryptedPinHash = KeyStoreHelper.encrypt(RUNEBASE_PIN_ALIAS, password);
        RunebaseSharedPreference.getInstance().saveSixDigitPassword(mContext, encryptedPinHash);
    }

    @Override
    public void savePassphraseSaltWithPin(String pin, String passphrase) {
        byte[] saltPassphrase = AESUtil.encryptToBytes(pin, passphrase);
        String encryptedSaltPassphrase = KeyStoreHelper.encryptBytes(RUNEBASE_PIN_ALIAS, saltPassphrase);
        RunebaseSharedPreference.getInstance().saveSeed(mContext, encryptedSaltPassphrase);
    }

    @Override
    public byte[] getSaltPassphrase() {
        String encryptedSaltPassphrase = RunebaseSharedPreference.getInstance().getSeed(mContext);
        return KeyStoreHelper.decryptToBytes(RUNEBASE_PIN_ALIAS, encryptedSaltPassphrase);
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
    public Observable<String> loadWallet(String code) {
        return KeyStorage.getInstance().createWallet(KeyStoreHelper.getSeed(mContext, code));
    }
}