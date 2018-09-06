package org.runebase.wallet.ui.fragment.contract_function_fragment.contract_default_function_fragment;

import org.runebase.wallet.model.contract.Contract;
import org.runebase.wallet.model.contract.ContractMethod;
import org.runebase.wallet.model.contract.ContractMethodParameter;
import org.runebase.wallet.model.gson.SendRawTransactionResponse;
import org.runebase.wallet.model.gson.UnspentOutput;

import java.math.BigDecimal;
import java.util.List;

import rx.Observable;

public interface ContractFunctionDefaultInteractor {
    List<ContractMethod> getContractMethod(String contractTemplateUiid);

    BigDecimal getFeePerKb();

    int getMinGasPrice();

    Observable<ContractFunctionDefaultInteractorImpl.CallSmartContractRespWrapper> callSmartContractObservable(String methodName,
                                                                                                               List<ContractMethodParameter> contractMethodParameterList,
                                                                                                               Contract contract,
                                                                                                               String addressFrom);

    Observable<List<UnspentOutput>> unspentOutputsForAddressObservable(String address);

    String createTransactionHash(String abiParams, List<UnspentOutput> unspentOutputs, int gasLimit, int gasPrice, BigDecimal feePerKb, String fee, final String contractAddress, String sendToContract);

    Observable<SendRawTransactionResponse> sendRawTransactionObservable(String code);

    Contract getContractByAddress(String address);

    List<String> getAddresses();

    Observable<List<UnspentOutput>> getUnspentOutputs(List<String> addresses);
}
