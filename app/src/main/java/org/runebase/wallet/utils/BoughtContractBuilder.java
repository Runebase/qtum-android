package org.runebase.wallet.utils;

import android.content.Context;

import com.google.gson.Gson;

import org.runebase.wallet.dataprovider.rest_api.runebase.RunebaseService;
import org.runebase.wallet.datastorage.FileStorageManager;
import org.runebase.wallet.datastorage.QStoreStorage;
import org.runebase.wallet.model.gson.qstore.ContractPurchase;
import org.runebase.wallet.model.gson.qstore.PurchaseItem;
import org.runebase.wallet.model.gson.qstore.QstoreByteCodeResponse;
import org.runebase.wallet.model.gson.qstore.QstoreContract;
import org.runebase.wallet.model.gson.qstore.QstoreSourceCodeResponse;

import rx.Subscriber;

public class BoughtContractBuilder {

    private String sourceContract;
    private String byteCodeContract;
    private String abiContract;
    private String type;
    private String name;
    private String dateString;
    private String uuid;

    public void build(final Context context, ContractPurchase contractPurchase, final ContractBuilderListener listener) {
        final PurchaseItem purchaseItem = QStoreStorage.getInstance(context).getPurchaseByContractId(contractPurchase.getContractId());
        RunebaseService.newInstance()
                .getSourceCode(purchaseItem.getContractId(), purchaseItem.getRequestId(), purchaseItem.getAccessToken())
                .subscribe(new Subscriber<QstoreSourceCodeResponse>() {
                    @Override
                    public void onCompleted() {
                        RunebaseService.newInstance()
                                .getByteCode(purchaseItem.getContractId(), purchaseItem.getRequestId(), purchaseItem.getAccessToken())
                                .subscribe(new Subscriber<QstoreByteCodeResponse>() {
                                    @Override
                                    public void onCompleted() {
                                        RunebaseService.newInstance().getAbiByContractId(purchaseItem.getContractId())
                                                .subscribe(new Subscriber<Object>() {
                                                    @Override
                                                    public void onCompleted() {
                                                        RunebaseService.newInstance().getContractById(purchaseItem.getContractId())
                                                                .subscribe(new Subscriber<QstoreContract>() {
                                                                    @Override
                                                                    public void onCompleted() {
                                                                        FileStorageManager.getInstance().importTemplate(context, sourceContract, byteCodeContract, abiContract, type, name, dateString, uuid);
                                                                        listener.onBuildSuccess();
                                                                    }

                                                                    @Override
                                                                    public void onError(Throwable e) {
                                                                    }

                                                                    @Override
                                                                    public void onNext(QstoreContract qstoreContract) {
                                                                        type = qstoreContract.type;
                                                                        name = qstoreContract.name;
                                                                        dateString = qstoreContract.creationDate;
                                                                        uuid = purchaseItem.getContractId();
                                                                    }
                                                                });
                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {
                                                    }

                                                    @Override
                                                    public void onNext(Object abi) {
                                                        abiContract = (new Gson()).toJson(abi);
                                                    }
                                                });
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                    }

                                    @Override
                                    public void onNext(QstoreByteCodeResponse qstoreByteCodeResponse) {
                                        byteCodeContract = qstoreByteCodeResponse.bytecode;
                                    }
                                });
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(QstoreSourceCodeResponse qstoreSourceCodeResponse) {
                        sourceContract = qstoreSourceCodeResponse.sourceCode;
                    }
                });
    }

    public interface ContractBuilderListener {
        void onBuildSuccess();
    }
}
