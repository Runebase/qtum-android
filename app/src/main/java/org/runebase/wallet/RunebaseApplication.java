package org.runebase.wallet;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;

import org.runebase.wallet.datastorage.QStoreStorage;
import org.runebase.wallet.datastorage.RunebaseSettingSharedPreference;
import org.runebase.wallet.utils.FontManager;
import org.runebase.wallet.utils.migration_manager.MigrationManager;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RunebaseApplication extends MultiDexApplication{

    public static RunebaseApplication instance;
    private final String REALM_NAME = "org.runebase.realm";

    private WearableMessagingProvider wearableMessagingProvider;

    public WearableMessagingProvider getWearableMessagingProvider() {
        return wearableMessagingProvider;
    }

    public void setWearableMessagingProvider(WearableMessagingProvider provider){
        this.wearableMessagingProvider = provider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
        FontManager.init(getAssets());
        QStoreStorage.getInstance(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

        try {
            int currentVersion = getCodeVersion();
            RunebaseSettingSharedPreference runebaseSettingSharedPreference = new RunebaseSettingSharedPreference();
            int migrationVersion = runebaseSettingSharedPreference.getCodeVersion(getApplicationContext());
            if (currentVersion > migrationVersion) {
                MigrationManager migrationManager = new MigrationManager();
                int newMigrationVersion = migrationManager.makeMigration(currentVersion, migrationVersion, getApplicationContext());
                runebaseSettingSharedPreference.setMigrationCodeVersionString(getApplicationContext(), newMigrationVersion);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getCodeVersion() throws PackageManager.NameNotFoundException {
        PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        return pInfo.versionCode;
    }
}