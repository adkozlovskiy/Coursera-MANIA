package com.elegion.recyclertest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CardDecoration extends RecyclerView.ItemDecoration {

    private final Paint mYellowPaint;
    private final Paint mRedPaint;

    public CardDecoration() {
        mYellowPaint = new Paint();
        mYellowPaint.setStyle(Paint.Style.FILL);
        mYellowPaint.setAntiAlias(true);
        mYellowPaint.setColor(Color.YELLOW);

        mRedPaint = new Paint();
        mRedPaint.setStyle(Paint.Style.FILL);
        mRedPaint.setAntiAlias(true);
        mRedPaint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int pixelOffset = parent.getContext().getResources().getDimensionPixelOffset(R.dimen.li_margin);

        int viewCount = parent.getChildCount();

        for (int i = 0; i < viewCount; i++) {
            View child = parent.getChildAt(i);

            int top = child.getTop() - pixelOffset / 2;
            int bottom = child.getBottom() + pixelOffset / 2;

            int childAdapterPosition = parent.getChildAdapterPosition(child);
            c.drawRect(left, top, right, bottom, childAdapterPosition % 2 == 0 ? mYellowPaint : mRedPaint);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int pixelOffset = parent.getContext().getResources().getDimensionPixelOffset(R.dimen.li_margin);
        int viewCount = parent.getChildCount();
        int right = parent.getWidth() / 2;
        int left = right - pixelOffset * 2;

        for (int i = 0; i < viewCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getTop() + pixelOffset;
            int bottom = child.getBottom() - pixelOffset;
            int childAdapterPosition = parent.getChildAdapterPosition(child);

            c.drawRect(left, top, right, bottom, childAdapterPosition % 2 != 0 ? mYellowPaint : mRedPaint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pixelOffset = view.getContext().getResources().getDimensionPixelOffset(R.dimen.li_margin);

        outRect.left = pixelOffset;
        outRect.right = pixelOffset;
        outRect.top = pixelOffset / 2;
        outRect.bottom = pixelOffset / 2;
    }
}
