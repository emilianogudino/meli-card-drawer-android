package com.meli.android.carddrawer.model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import com.meli.android.carddrawer.R;
import com.meli.android.carddrawer.format.NumberFormatter;

public class CardDrawerViewLowres extends CardDrawerView {

    private float codeFrontTextSize;

    public CardDrawerViewLowres(@NonNull final Context context) {
        this(context, null);
    }

    public CardDrawerViewLowres(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardDrawerViewLowres(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        codeFrontTextSize = getResources().getDimension(R.dimen.card_drawer_font_size_small);
        super.init(context, attrs);
    }

    @Override
    protected float getCodeFrontTextSize() {
        return codeFrontTextSize;
    }

    @Override
    @LayoutRes
    protected int getLayout() {
        return R.layout.card_drawer_layout_lowres;
    }

    @Override
    @VisibleForTesting
    protected void updateCardInformation() {
        updateNumber();
        updateName();
        updateSecCode();
    }

    @Override
    protected String getFormattedNumber(@NonNull final String input, @NonNull final int... pattern) {
        return NumberFormatter.INSTANCE.formatShort(input, pattern);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final float cardSizeMultiplier = (float) cardFrontLayout.getMeasuredWidth() / defaultCardWidth;
        final float newTextSize = defaultTextSize * cardSizeMultiplier;

        setTextPixelSize(cardNumber, newTextSize);
    }
}