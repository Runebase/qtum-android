package com.pixelplex.qtum.datastorage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.pixelplex.qtum.BuildConfig;
import com.pixelplex.qtum.model.ContractTemplate;
import com.pixelplex.qtum.model.contract.ContractMethod;
import com.pixelplex.qtum.utils.DateCalculator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class FileStorageManager {

    private static FileStorageManager _instance;

    private final static String TAG = "STORAGE MANAGER: ";

    private final static String prefMigrationBuildVersion = "migration_buid_version";

    private final static String CrowdSale = "Crowdsale";
    private static String[] STANDART_CONTRACTS = {"Standart", "Version1", "Version2", "Crowdsale"};

    private static String abiContract = "abi-contract";
    private static String byteCodeContract = "bitecode-contract";
    private static String sourceContract = "source-contract";

    private static String TYPE = "type";
    private static String CONSTRUCTOR_TYPE = "constructor";
    private static String FUNCTION_TYPE = "function";

    private static String CONTRACTS_PACKAGE = "contracts";

    private int currentContractPackageName = 0;

    public static FileStorageManager getInstance() {
        if(_instance == null){
            _instance = new FileStorageManager();
        }

        return _instance;
    }

    private boolean writeContract(Context context, String fileName, String fileContent) {
           return writeFile(context, fileName, fileContent);
    }

    private String readContract(Context context, long uiid, String fileName) {
            return readFile(context, uiid, fileName);
    }

    private File getPackagePath(ContextWrapper cw, String targetPackage) {
        return cw.getDir(targetPackage, Context.MODE_PRIVATE);
    }

    private boolean writeFile(Context context, String fileName, String fileContent) {

        TinyDB tinyDB = new TinyDB(context);
        currentContractPackageName = tinyDB.getInt(tinyDB.CURRENT_CONTRACT_PACKAGE_NAME);

        ContextWrapper cw = new ContextWrapper(context);
        File contractDir = getPackagePath(cw,CONTRACTS_PACKAGE);
        File mFileDirectory = new File(String.format("%s/%s",contractDir.getAbsolutePath(), currentContractPackageName));

        boolean v = mFileDirectory.mkdir();

        File fullPath = new File(mFileDirectory, fileName);

        if(!fullPath.exists()) {

            try {

                FileOutputStream fOut = new FileOutputStream(fullPath, true);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);

                osw.write(fileContent);
                osw.flush();
                osw.close();
            } catch (IOException e) {

                e.printStackTrace();
                return false;
            }
            Log.d(TAG, "writeFile: Complete");
            return true;
        }else {
            Log.d(TAG, "writeFile: File Exists");
            return true;
        }
    }

    private String readFile(Context context, long uiid, String fileName) {

        String data = "";

        ContextWrapper cw = new ContextWrapper(context);
        File contractDir = getPackagePath(cw,CONTRACTS_PACKAGE);

        FileReader inputFile;
        try {
            inputFile = new FileReader(new File(String.format("%s/%s/%s",contractDir,uiid,fileName)));
            BufferedReader bufferReader = new BufferedReader(inputFile);

            String line;
            while ((line = bufferReader.readLine()) != null)   {
                data+=line;
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String readFromAsset(Context context, String packageName, String fileName) {

        String data = "";
        String mLine;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(String.format("%s/%s/%s",CONTRACTS_PACKAGE,packageName,fileName))));

            while ((mLine = reader.readLine()) != null) {
                data += mLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(TAG, "readFromAsset: "+data);
        return data;
    }

    private boolean writeAbiContract(Context context, String content){
        return writeContract(context, abiContract,content);
    }

    private boolean writeByteCodeContract(Context context, String content){
        return writeContract(context, byteCodeContract,content);
    }

    private boolean writeSourceContract(Context context, String content){
        return writeContract(context, sourceContract,content);
    }

    public String readAbiContract(Context context, long uiid) {
        return readContract(context,uiid, abiContract);
    }

    public String readByteCodeContract(Context context, long uiid) {
        return readContract(context,uiid, byteCodeContract);
    }

    public String readSourceContract(Context context, long uiid) {
        return readContract(context,uiid, sourceContract);
    }

//    READ DEFAULT CONTRACTS
    private String readAbiContractAsset(Context context, String packageName) {
        return readFromAsset(context,packageName, abiContract);
    }

    private String readByteCodeContractAsset(Context context, String packageName) {
        return readFromAsset(context,packageName, byteCodeContract);
    }

    private String readSourceContractAsset(Context context, String packageName) {
        return readFromAsset(context,packageName, sourceContract);
    }
//    READ DEFAULT CONTRACTS

    private boolean migrateContract(Context context, String contractName) {
        boolean result;

        String readData = readAbiContractAsset(context,contractName);
        if(TextUtils.isEmpty(readData)) {
            return false;
        }
       result =  writeAbiContract(context,readData);

        if(result) {
            readData = readByteCodeContractAsset(context, contractName);
            if (TextUtils.isEmpty(readData)) {
                return false;
            }
            result = writeByteCodeContract(context, readData);
        }else {
            return false;
        }

        if(result) {
            readData = readSourceContractAsset(context, contractName);
            if (TextUtils.isEmpty(readData)) {
                return false;
            }
            result = writeSourceContract(context, readData);
        }else {
            return false;
        }

        return result;
    }

    public ContractMethod getContractConstructor(Context context, long uiid) {
      String abiContent = readAbiContract(context,uiid);
        JSONArray array;
        try {
            array = new JSONArray(abiContent);

            for (int i = 0; i < array.length(); i++) {
                JSONObject jb = array.getJSONObject(i);
                if(CONSTRUCTOR_TYPE.equals(jb.getString(TYPE))){
                    Gson gson = new Gson();
                    return gson.fromJson(jb.toString(), ContractMethod.class);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ContractMethod> getContractMethods(final Context context, long uiid) {
        String abiContent = readAbiContract(context,uiid);
        JSONArray array;
        List<ContractMethod> contractMethods = new ArrayList<>();
        try {
            array = new JSONArray(abiContent);

            for (int i = 0; i < array.length(); i++) {
                JSONObject jb = array.getJSONObject(i);
                if(FUNCTION_TYPE.equals(jb.getString(TYPE))){
                    Gson gson = new Gson();
                    contractMethods.add(gson.fromJson(jb.toString(), ContractMethod.class));
                }
            }
            Collections.sort(contractMethods, new Comparator<ContractMethod>() {
                @Override
                public int compare(ContractMethod contractMethod, ContractMethod t1) {
                    if(contractMethod.constant && !t1.constant){
                        return -1;
                    } else if(!contractMethod.constant && t1.constant){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            return contractMethods;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean migrateDefaultContracts(Context context) {
        if(!isDefaultContractsMigrates(context)) {
            TinyDB tinyDB = new TinyDB(context);
            List<ContractTemplate> contractTemplateList = tinyDB.getContractTemplateList();

            for (String contractName : STANDART_CONTRACTS) {
                if (!migrateContract(context, contractName)) {
                    return false;
                }
                contractTemplateList.add(new ContractTemplate(contractName, DateCalculator.getDateInFormat(new Date()),(contractName.equals(CrowdSale) ? "crowdsale" : "token"),currentContractPackageName));
                changeCurrentContractPackageName(context);
            }

            tinyDB.putContractTemplate(contractTemplateList);

            commitDefaultContractsMigration(context);
            Log.d(TAG, "migrateDefaultContracts: Migration Complete");
            return true;
        } else {
            Log.d(TAG, "migrateDefaultContracts: Migration already performed");
            return true;
        }
    }

    private boolean isDefaultContractsMigrates(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return BuildConfig.VERSION_CODE == prefs.getInt(prefMigrationBuildVersion,0);
    }

    private boolean commitDefaultContractsMigration(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.edit().putInt(prefMigrationBuildVersion, BuildConfig.VERSION_CODE).commit();
    }

    private void changeCurrentContractPackageName(Context context){
        TinyDB tinyDB = new TinyDB(context);
        currentContractPackageName++;
        tinyDB.putInt(tinyDB.CURRENT_CONTRACT_PACKAGE_NAME, currentContractPackageName);
    }

    public long importTemplate(Context context, String sourceContract, String byteCodeContract, String abiContract, String type, String name, String dateString){
        TinyDB tinyDB = new TinyDB(context);
        currentContractPackageName = tinyDB.getInt(tinyDB.CURRENT_CONTRACT_PACKAGE_NAME);

        ContractTemplate contractTemplate = new ContractTemplate(name, dateString, type, currentContractPackageName);

        if(sourceContract!=null && !sourceContract.isEmpty()) {
            writeSourceContract(context, sourceContract);
        } else {
            contractTemplate.setFullContractTemplate(false);
        }
        if(abiContract!=null && !abiContract.isEmpty()) {
            writeAbiContract(context, abiContract);
        } else {
            contractTemplate.setFullContractTemplate(false);
        }
        if(byteCodeContract!=null && !byteCodeContract.isEmpty()) {
            writeByteCodeContract(context, byteCodeContract);
        } else{
            contractTemplate.setFullContractTemplate(false);
        }

        List<ContractTemplate> contractTemplateList = tinyDB.getContractTemplateList();
        contractTemplateList.add(contractTemplate);
        tinyDB.putContractTemplate(contractTemplateList);
        changeCurrentContractPackageName(context);

        return currentContractPackageName-1;
    }

}