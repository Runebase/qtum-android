package com.pixelplex.qtum.ui.fragment.AddressListFragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.pixelplex.qtum.R;
import com.pixelplex.qtum.dataprovider.restAPI.QtumService;
import com.pixelplex.qtum.datastorage.KeyStorage;
import com.pixelplex.qtum.model.DeterministicKeyWithBalance;
import com.pixelplex.qtum.model.gson.SendRawTransactionRequest;
import com.pixelplex.qtum.model.gson.SendRawTransactionResponse;
import com.pixelplex.qtum.model.gson.UnspentOutput;
import com.pixelplex.qtum.ui.fragment.BaseFragment.BaseFragment;
import com.pixelplex.qtum.ui.fragment.BaseFragment.BaseFragmentPresenterImpl;
import com.pixelplex.qtum.utils.CurrentNetParams;
import com.pixelplex.qtum.utils.FontTextView;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.script.Script;
import org.spongycastle.util.encoders.Hex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class AddressListFragmentPresenter extends BaseFragmentPresenterImpl{

    private AddressListFragmentView mAddressListFragmentView;
    private AddressListFragmentInteractor mAddressListFragmentInteractor;
    private Context mContext;
    private List<DeterministicKeyWithBalance> mKeyWithBalanceList = new ArrayList<>();
    private int balanceCountToReceive;
    private DeterministicKeyWithBalance keyWithBalanceFrom;


    private AlertDialog mTransferDialog;

    AddressListFragmentPresenter(AddressListFragmentView addressListFragmentView){
        mAddressListFragmentView = addressListFragmentView;
        mContext = getView().getContext();
        mAddressListFragmentInteractor = new AddressListFragmentInteractor(mContext);
    }

    @Override
    public AddressListFragmentView getView() {
        return mAddressListFragmentView;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        balanceCountToReceive = getInteractor().getKeyList().size();
        getView().setProgressDialog();
        for(final DeterministicKey deterministicKey : getInteractor().getKeyList()){
            final DeterministicKeyWithBalance deterministicKeyWithBalance = new DeterministicKeyWithBalance(deterministicKey);
            QtumService.newInstance().getUnspentOutputs(deterministicKeyWithBalance.getAddress())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<UnspentOutput>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<UnspentOutput> unspentOutputs) {
                            for(Iterator<UnspentOutput> iterator = unspentOutputs.iterator(); iterator.hasNext();){
                                UnspentOutput unspentOutput = iterator.next();
                                if(!unspentOutput.isOutputAvailableToPay()){
                                    iterator.remove();
                                }
                            }
                            Collections.sort(unspentOutputs, new Comparator<UnspentOutput>() {
                                @Override
                                public int compare(UnspentOutput unspentOutput, UnspentOutput t1) {
                                    return unspentOutput.getAmount().doubleValue() < t1.getAmount().doubleValue() ? 1 : unspentOutput.getAmount().doubleValue() > t1.getAmount().doubleValue() ? -1 : 0;
                                }
                            });
                            deterministicKeyWithBalance.setUnspentOutputList(unspentOutputs);
                            BigDecimal balance = new BigDecimal("0");
                            BigDecimal amount;
                            for(UnspentOutput unspentOutput : unspentOutputs){
                                amount = new BigDecimal(String.valueOf(unspentOutput.getAmount()));
                                balance = balance.add(amount);
                            }
                            deterministicKeyWithBalance.setBalance(balance);
                            mKeyWithBalanceList.add(deterministicKeyWithBalance);
                            balanceCountToReceive--;
                            if(balanceCountToReceive==0){
                                getView().dismissProgressDialog();
                                getView().updateAddressList(mKeyWithBalanceList, new OnAddressClickListener() {
                                    @Override
                                    public void onItemClick(DeterministicKeyWithBalance deterministicKeyWithBalance) {
                                        List<DeterministicKeyWithBalance> deterministicKeyWithBalances = new ArrayList<DeterministicKeyWithBalance>(mKeyWithBalanceList);
                                        deterministicKeyWithBalances.remove(deterministicKeyWithBalance);
                                        showRestoreDialogFragment(deterministicKeyWithBalance, deterministicKeyWithBalances);
                                    }
                                });
                            }
                        }
                    });

        }

    }

    private void showRestoreDialogFragment(final DeterministicKeyWithBalance keyWithBalanceTo, List<DeterministicKeyWithBalance> keyWithBalanceList){
        View view = LayoutInflater.from(getView().getMainActivity()).inflate(R.layout.dialog_transfer_balance_fragment,null);

        final TextInputEditText mEditTextAmount = (TextInputEditText)view.findViewById(R.id.et_amount);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_transfer);
        FontTextView mEditTextAddressTo = (FontTextView)view.findViewById(R.id.tv_address_to);

        mEditTextAddressTo.setText(keyWithBalanceTo.getKey().toAddress(CurrentNetParams.getNetParams()).toString());

        AddressesWithBalanceSpinnerAdapter spinnerAdapter = new AddressesWithBalanceSpinnerAdapter(mContext,keyWithBalanceList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                keyWithBalanceFrom = (DeterministicKeyWithBalance) spinner.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        view.findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTransferDialog.dismiss();
            }
        });

        view.findViewById(R.id.bt_transfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().setProgressDialog();
               transfer(keyWithBalanceTo, keyWithBalanceFrom, mEditTextAmount.getText().toString(), new TransferListener(){
                   @Override
                   public void onError(String errorText) {
                        getView().setAlertDialog(mContext.getString(R.string.error),mContext.getString(R.string.ok),errorText, BaseFragment.PopUpType.error);
                   }

                   @Override
                   public void onSuccess() {
                       getView().dismissProgressDialog();
                       mTransferDialog.dismiss();
                       getView().setAlertDialog(mContext.getString(R.string.payment_completed_successfully), "","Ok", BaseFragment.PopUpType.confirm,new BaseFragment.AlertDialogCallBack(){
                           @Override
                           public void onOkClick() {
                               getView().getMainActivity().onBackPressed();
                           }
                       });
                   }
               });
            }
        });

        mTransferDialog = new AlertDialog
                .Builder(mContext)
                .setView(view)
                .create();

        if(mTransferDialog.getWindow() != null) {
            mTransferDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        mTransferDialog.setCanceledOnTouchOutside(false);
        mTransferDialog.show();
    }

    private void transfer(DeterministicKeyWithBalance keyWithBalanceTo, DeterministicKeyWithBalance keyWithBalanceFrom, String amountString, TransferListener listener){

        List<UnspentOutput> unspentOutputs = keyWithBalanceFrom.getUnspentOutputList();

        Transaction transaction = new Transaction(CurrentNetParams.getNetParams());
        Address addressToSend;
        BigDecimal bitcoin = new BigDecimal(100000000);
        try {
            addressToSend = keyWithBalanceTo.getKey().toAddress(CurrentNetParams.getNetParams());
        } catch (AddressFormatException a) {
            listener.onError("Incorrect Address");
            return;
        }
        ECKey myKey = KeyStorage.getInstance().getCurrentKey();
        BigDecimal amount = new BigDecimal(amountString);
        BigDecimal fee = new BigDecimal("0.1");

        BigDecimal amountFromOutput = new BigDecimal("0.0");
        BigDecimal overFlow = new BigDecimal("0.0");
        transaction.addOutput(Coin.valueOf((long)(amount.multiply(bitcoin).doubleValue())), addressToSend);

        amount = amount.add(fee);

        for (UnspentOutput unspentOutput : unspentOutputs) {
            overFlow = overFlow.add(unspentOutput.getAmount());
            if (overFlow.doubleValue() >= amount.doubleValue()) {
                break;
            }
        }
        if (overFlow.doubleValue() < amount.doubleValue()) {
            listener.onError("You have insufficient funds for this transaction");
            return;
        }
        BigDecimal delivery = overFlow.subtract(amount);
        if (delivery.doubleValue() != 0.0) {
            transaction.addOutput(Coin.valueOf((long)(delivery.multiply(bitcoin).doubleValue())), myKey.toAddress(CurrentNetParams.getNetParams()));
        }

        for (UnspentOutput unspentOutput : unspentOutputs) {
            if(unspentOutput.getAmount().doubleValue() != 0.0)
                for (DeterministicKey deterministicKey : KeyStorage.getInstance().getKeyList(100)) {
                    if (Hex.toHexString(deterministicKey.getPubKeyHash()).equals(unspentOutput.getPubkeyHash())) {
                        Sha256Hash sha256Hash = new Sha256Hash(Utils.parseAsHexOrBase58(unspentOutput.getTxHash()));
                        TransactionOutPoint outPoint = new TransactionOutPoint(CurrentNetParams.getNetParams(), unspentOutput.getVout(), sha256Hash);
                        Script script = new Script(Utils.parseAsHexOrBase58(unspentOutput.getTxoutScriptPubKey()));
                        transaction.addSignedInput(outPoint, script, deterministicKey, Transaction.SigHash.ALL, true);
                        amountFromOutput = amountFromOutput.add(unspentOutput.getAmount());
                        break;
                    }
                }
            if (amountFromOutput.doubleValue() >= amount.doubleValue()) {
                break;
            }
        }


        transaction.getConfidence().setSource(TransactionConfidence.Source.SELF);
        transaction.setPurpose(Transaction.Purpose.USER_PAYMENT);

        byte[] bytes = transaction.unsafeBitcoinSerialize();

        String transactionHex = Hex.toHexString(bytes);

        sendTx(transactionHex,listener);
    }

    private void sendTx(String txHex, final TransferListener listener){
        QtumService.newInstance().sendRawTransaction(new SendRawTransactionRequest(txHex, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SendRawTransactionResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(SendRawTransactionResponse sendRawTransactionResponse) {
                        listener.onSuccess();
                    }
                });
    }

    public AddressListFragmentInteractor getInteractor() {
        return mAddressListFragmentInteractor;
    }

    interface TransferListener{
        void onSuccess();
        void onError(String errorText);
    }
}
