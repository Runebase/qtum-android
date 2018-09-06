package org.runebase.wallet.ui.fragment.token_cash_management_fragment.dark;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import org.runebase.wallet.model.DeterministicKeyWithTokenBalance;
import org.runebase.wallet.ui.fragment.token_cash_management_fragment.AddressesListFragmentToken;
import org.runebase.wallet.ui.fragment.token_cash_management_fragment.TokenAddressesAdapter;
import org.runebase.wallet.utils.CurrentNetParams;
import org.runebase.wallet.utils.FontTextView;

import java.util.ArrayList;
import java.util.List;

public class AddressesListFragmentTokenDark extends AddressesListFragmentToken {
    @Override
    protected int getLayout() {
        return org.runebase.wallet.R.layout.fragment_address_list;
    }

    @Override
    public void updateAddressList(List<DeterministicKeyWithTokenBalance> deterministicKeyWithBalance, String currency) {
        if (mRecyclerView != null) {
            adapter = new TokenAddressesAdapter(deterministicKeyWithBalance, org.runebase.wallet.R.layout.item_address, this, currency, getPresenter().getDecimalUnits());
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(DeterministicKeyWithTokenBalance item) {
        List<DeterministicKeyWithTokenBalance> deterministicKeyWithBalances = new ArrayList<>(getPresenter().getKeysWithTokenBalance());
        deterministicKeyWithBalances.remove(item);
        showTransferDialogFragment(item, deterministicKeyWithBalances, getPresenter().getDecimalUnits());
    }

    protected void showTransferDialogFragment(final DeterministicKeyWithTokenBalance keyWithBalanceTo, List<DeterministicKeyWithTokenBalance> keyWithBalanceList, int decimalUnits) {
        View view = LayoutInflater.from(getContext()).inflate(org.runebase.wallet.R.layout.dialog_transfer_balance_fragment, null);
        final TextInputEditText mEditTextAmount = (TextInputEditText) view.findViewById(org.runebase.wallet.R.id.et_amount);
        final Spinner spinner = (Spinner) view.findViewById(org.runebase.wallet.R.id.spinner_transfer);
        FontTextView mEditTextAddressTo = (FontTextView) view.findViewById(org.runebase.wallet.R.id.tv_address_to);
        mEditTextAddressTo.setText(keyWithBalanceTo.getKey().toAddress(CurrentNetParams.getNetParams()).toString());
        AddressesWithTokenBalanceSpinnerAdapterDark spinnerAdapter = new AddressesWithTokenBalanceSpinnerAdapterDark(getContext(), keyWithBalanceList, getPresenter().getCurrency(), decimalUnits);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getPresenter().setKeyWithTokenBalanceFrom((DeterministicKeyWithTokenBalance) spinner.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        view.findViewById(org.runebase.wallet.R.id.bt_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTransferDialog.dismiss();
            }
        });

        view.findViewById(org.runebase.wallet.R.id.bt_transfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressDialog();
                getPresenter().transfer(keyWithBalanceTo, getPresenter().getKeyWithTokenBalanceFrom(), mEditTextAmount.getText().toString());
            }
        });

        mTransferDialog = new AlertDialog
                .Builder(getContext())
                .setView(view)
                .create();

        if (mTransferDialog.getWindow() != null) {
            mTransferDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        mTransferDialog.setCanceledOnTouchOutside(false);
        mTransferDialog.show();
    }
}
