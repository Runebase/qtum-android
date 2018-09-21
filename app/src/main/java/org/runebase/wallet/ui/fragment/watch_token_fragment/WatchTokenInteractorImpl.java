package org.runebase.wallet.ui.fragment.watch_token_fragment;

import android.content.Context;

import org.runebase.wallet.dataprovider.rest_api.runebase.RunebaseService;
import org.runebase.wallet.datastorage.FileStorageManager;
import org.runebase.wallet.datastorage.TinyDB;
import org.runebase.wallet.model.ContractTemplate;
import org.runebase.wallet.model.contract.Contract;
import org.runebase.wallet.model.contract.ContractCreationStatus;
import org.runebase.wallet.model.contract.Token;
import org.runebase.wallet.model.gson.ContractParams;
import org.runebase.wallet.utils.ContractBuilder;
import org.runebase.wallet.utils.DateCalculator;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;

public class WatchTokenInteractorImpl implements WatchTokenInteractor {

    private WeakReference<Context> mContext;

    public WatchTokenInteractorImpl(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    @Override
    public List<ContractTemplate> getContractTemplates() {
        TinyDB tinyDB = new TinyDB(mContext.get());
        return tinyDB.getContractTemplateList();
    }

    @Override
    public List<Contract> getContracts() {
        TinyDB tinyDB = new TinyDB(mContext.get());
        return tinyDB.getContractList();
    }

    @Override
    public int compareDates(String date, String date1) {
        return DateCalculator.equals(date, date1);
    }

    @Override
    public String readAbiContract(String uuid) {
        return FileStorageManager.getInstance().readAbiContract(mContext.get(), uuid);
    }

    @Override
    public boolean isValidContractAddress(String address) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{40,}$");
        Matcher m = p.matcher(address);
        return m.matches();
    }

    @Override
    public String handleContractWithToken(String name, String address, String ABIInterface) {
        ContractTemplate contractTemplate = FileStorageManager.getInstance().importTemplate(mContext.get(), null, null, ABIInterface, "token", "no_name",
                DateCalculator.getCurrentDate(), UUID.randomUUID().toString());
        TinyDB tinyDB = new TinyDB(mContext.get());
        List<Token> tokenList = tinyDB.getTokenList();
        Token token = new Token(address, contractTemplate.getUuid(), ContractCreationStatus.Created, DateCalculator.getCurrentDate(), "", name);
        tokenList.add(token);
        tinyDB.putTokenList(tokenList);

        return token.getContractAddress();
    }

    @Override
    public String getQRC20TokenStandardAbi() {
        TinyDB tinyDB = new TinyDB(mContext.get());
        for(ContractTemplate template: tinyDB.getContractTemplateList()){
            if(template.getName().equals("QRC20TokenStandard")) {
                return FileStorageManager.getInstance().readAbiContract(mContext.get(), template.getUuid());
            }
        }
        return null;
    }

    @Override
    public Observable<ContractParams> getContractParams(String contractAddress) {
        return RunebaseService.newInstance().getContractParams(contractAddress);
    }
}
