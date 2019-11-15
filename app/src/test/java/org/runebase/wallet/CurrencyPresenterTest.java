package org.runebase.wallet;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.runebase.wallet.model.Currency;
import org.runebase.wallet.ui.fragment.currency_fragment.CurrencyInteractor;
import org.runebase.wallet.ui.fragment.currency_fragment.CurrencyPresenterImpl;
import org.runebase.wallet.ui.fragment.currency_fragment.CurrencyView;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrencyPresenterTest {

    @Mock
    CurrencyView view;
    @Mock
    CurrencyInteractor interactor;
    CurrencyPresenterImpl presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new CurrencyPresenterImpl(view, interactor);
    }

    private final List<Currency> TEST_CURRENCIES = Arrays.asList(new Currency("some name1"), new Currency("some name2"));

    @Test
    public void initialize_Test() {
        when(interactor.getCurrencies()).thenReturn(TEST_CURRENCIES);
        presenter.initializeViews();
        verify(view, times(1)).setCurrencyList(TEST_CURRENCIES);
    }

}
