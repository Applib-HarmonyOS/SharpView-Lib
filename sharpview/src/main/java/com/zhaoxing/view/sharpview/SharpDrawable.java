package com.zhaoxing.view.sharpview;

import ohos.agp.colors.RgbColor;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.render.Arc;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.render.Path;
import ohos.agp.utils.Color;
import ohos.agp.utils.Point;
import ohos.agp.utils.RectFloat;

/*
 * SharpDrawable:
 * */
class SharpDrawable extends ShapeElement {

    private float mSharpSize;
    private RgbColor[] mBgColor;
    private RgbColor mBackgroundColor;
    private float mCornerRadius;
    private float[] mCornerRadii;
    private SharpView.ArrowDirection mArrowDirection;
    private float mBorder;
    private RgbColor mBorderColor;
    private float mRelativePosition;
    private Paint mPaint;
    private RectFloat mRect;
    private Path mSharpPath;
    private Point[] mPointFs;
    private RectFloat[] mOvalRect;
    private Component mComponent;

    SharpDrawable() {
        super();
        init();
    }

    SharpDrawable(Orientation orientation, Component component) {
        mComponent = component;
        super.setGradientOrientation(orientation);
        init();
    }

    SharpDrawable(Orientation orientation, Component component, RgbColor[] colors) {
        mComponent = component;
        super.setRgbColors(colors);
        super.setGradientOrientation(orientation);
        init();
    }

    public void setBgColor(RgbColor[] bgColor) {
        mBgColor = bgColor;
        super.setRgbColors(bgColor);
    }

    public void setBgColor(RgbColor bgColor) {
        mBackgroundColor = bgColor;
        super.setRgbColor(mBackgroundColor);
    }

    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
        super.setCornerRadius(cornerRadius);
    }

    public void setCornerRadii(float[] cornerRadii) {
        mCornerRadii = cornerRadii;
        super.setCornerRadiiArray(cornerRadii);
    }

    public void setArrowDirection(SharpView.ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
    }

    public void setRelativePosition(float relativePosition) {
        mRelativePosition = Math.min(Math.max(relativePosition, 0), 1);
    }

    public void setBorder(float border) {
        mBorder = border;
        super.setStroke((int) mBorder, mBorderColor);
    }

    public void setBorderColor(RgbColor borderColor) {
        mBorderColor = borderColor;
        super.setStroke((int) mBorder, mBorderColor);
    }

    public void setSharpSize(float sharpSize) {
        mSharpSize = sharpSize;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    private void init() {
        mSharpSize = 0;
        mBgColor = new RgbColor[]{RgbPalette.CYAN};
        mBackgroundColor = RgbPalette.CYAN;
        mCornerRadius = 0;
        mArrowDirection = SharpView.ArrowDirection.LEFT;
        mBorder = 0;
        mBorderColor = RgbPalette.CYAN;
        mRelativePosition = 0;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRect = new RectFloat();
        mPointFs = new Point[3];
        mSharpPath = new Path();
        mPointFs[0] = new Point();
        mPointFs[1] = new Point();
        mPointFs[2] = new Point();

        mOvalRect = new RectFloat[4];
        mOvalRect[0] = new RectFloat();
        mOvalRect[1] = new RectFloat();
        mOvalRect[2] = new RectFloat();
        mOvalRect[3] = new RectFloat();
    }

    @Override
    public void drawToCanvas(Canvas canvas) {
        if (mComponent != null && mSharpSize != 0) {
            int left = 0;
            int top = 0;
            int right = mComponent.getWidth();
            int bottom = mComponent.getHeight();
            float length;
            int boundLeft = 0;
            int boundTop = 0;
            int boundRight = mComponent.getWidth();
            int boundBottom = mComponent.getHeight();
            switch (mArrowDirection) {
                case LEFT:
                    left += mSharpSize;
                    length = Math.max(mRelativePosition * mComponent.getHeight(), mSharpSize + mCornerRadius);
                    length = Math.min(length, mComponent.getHeight() - mSharpSize - mCornerRadius);
                    mPointFs[0].modify(boundLeft, length + boundTop);
                    mPointFs[1].modify(left, mPointFs[0].getPointY() + mSharpSize);
                    mPointFs[2].modify(left, mPointFs[0].getPointY() - mSharpSize);
                    mRect.modify(left, top, right, bottom);
                    break;
                case TOP:
                    top += mSharpSize;
                    length = Math.max(mRelativePosition * mComponent.getWidth(), mSharpSize + mCornerRadius);
                    length = Math.min(length, mComponent.getWidth() - mSharpSize - mCornerRadius);
                    mPointFs[0].modify(boundLeft + length, boundTop);
                    mPointFs[1].modify(mPointFs[0].getPointX() - mSharpSize, top);
                    mPointFs[2].modify(mPointFs[0].getPointX() + mSharpSize, top);
                    mRect.modify(left, top, right, bottom);
                    break;
                case RIGHT:
                    right -= mSharpSize;
                    length = Math.max(mRelativePosition * mComponent.getHeight(), mSharpSize + mCornerRadius);
                    length = Math.min(length, mComponent.getHeight() - mSharpSize - mCornerRadius);
                    mPointFs[0].modify(boundRight, length + boundTop);
                    mPointFs[1].modify(right, mPointFs[0].getPointY() - mSharpSize);
                    mPointFs[2].modify(right, mPointFs[0].getPointY() + mSharpSize);
                    mRect.modify(left, top, right, bottom);
                    break;
                case BOTTOM:
                    bottom -= mSharpSize;
                    length = Math.max(mRelativePosition * mComponent.getWidth() + boundTop, mSharpSize + mCornerRadius);
                    length = Math.min(length, mComponent.getWidth() - mSharpSize - mCornerRadius);
                    mPointFs[0].modify(boundLeft + length, boundBottom);
                    mPointFs[1].modify(mPointFs[0].getPointX() - mSharpSize, bottom);
                    mPointFs[2].modify(mPointFs[0].getPointX() + mSharpSize, bottom);
                    mRect.modify(left, top, right, bottom);
                    break;
                default:
                    break;
            }
            mSharpPath.reset();
            mSharpPath.addRoundRect(mRect, mCornerRadius, mCornerRadius, Path.Direction.CLOCK_WISE);
            mSharpPath.moveTo(mPointFs[0].getPointX(), mPointFs[0].getPointY());
            mSharpPath.lineTo(mPointFs[1].getPointX(), mPointFs[1].getPointY());
            mSharpPath.lineTo(mPointFs[2].getPointX(), mPointFs[2].getPointY());
            mSharpPath.lineTo(mPointFs[0].getPointX(), mPointFs[0].getPointY());
            mPaint.setColor(new Color(mBackgroundColor.asRgbaInt()));
            canvas.drawPath(mSharpPath, mPaint);

            if (mComponent instanceof Image) {
                return;
            }

            mPaint.setColor(new Color(mBorderColor.asRgbaInt()));
            mPaint.setStyle(Paint.Style.STROKE_STYLE);
            mPaint.setStrokeWidth(mBorder);
            switch (mArrowDirection) {
                case LEFT:
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.top, mRect.right - mCornerRadius,
                            mRect.top, mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.bottom, mRect.right - mCornerRadius,
                            mRect.bottom, mPaint);
                    canvas.drawLine(mRect.right, mRect.top + mCornerRadius, mRect.right, mRect.bottom - mCornerRadius,
                            mPaint);
                    canvas.drawLine(mRect.left, mRect.top + mCornerRadius, mRect.left, mPointFs[1].getPointY(), mPaint);
                    canvas.drawLine(mRect.left, mPointFs[2].getPointY(), mRect.left, mRect.bottom - mCornerRadius,
                            mPaint);
                    break;
                case TOP:
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.bottom, mRect.right - mCornerRadius,
                            mRect.bottom, mPaint);
                    canvas.drawLine(mRect.left, mRect.top + mCornerRadius, mRect.left, mRect.bottom - mCornerRadius,
                            mPaint);
                    canvas.drawLine(mRect.right, mRect.top + mCornerRadius, mRect.right, mRect.bottom - mCornerRadius,
                            mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.top, mPointFs[1].getPointX(), mRect.top, mPaint);
                    canvas.drawLine(mPointFs[2].getPointX(), mRect.top, mRect.right - mCornerRadius, mRect.top, mPaint);
                    break;
                case RIGHT:
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.top, mRect.right - mCornerRadius,
                            mRect.top, mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.bottom, mRect.right - mCornerRadius,
                            mRect.bottom, mPaint);
                    canvas.drawLine(mRect.left, mRect.top + mCornerRadius, mRect.left, mRect.bottom - mCornerRadius,
                            mPaint);
                    canvas.drawLine(mRect.right, mRect.top + mCornerRadius, mRect.right, mPointFs[1].getPointY(),
                            mPaint);
                    canvas.drawLine(mRect.right, mPointFs[2].getPointY(), mRect.right, mRect.bottom - mCornerRadius,
                            mPaint);
                    break;
                case BOTTOM:
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.top, mRect.right - mCornerRadius,
                            mRect.top, mPaint);
                    canvas.drawLine(mRect.left, mRect.top + mCornerRadius, mRect.left, mRect.bottom - mCornerRadius,
                            mPaint);
                    canvas.drawLine(mRect.right, mRect.top + mCornerRadius, mRect.right, mRect.bottom - mCornerRadius,
                            mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius, mRect.bottom, mPointFs[1].getPointX(), mRect.bottom,
                            mPaint);
                    canvas.drawLine(mPointFs[2].getPointX(), mRect.bottom, mRect.right - mCornerRadius, mRect.bottom,
                            mPaint);
                    break;
                default:
                    break;
            }

            if (mCornerRadius > 0) {
                float d = 2 * mCornerRadius;

                mOvalRect[0].modify(mRect.left, mRect.top, mRect.left + d, mRect.top + d);
                mOvalRect[1].modify(mRect.right - d, mRect.top, mRect.right, mRect.top + d);
                mOvalRect[2].modify(mRect.left, mRect.bottom - d, mRect.left + d, mRect.bottom);
                mOvalRect[3].modify(mRect.right - d, mRect.bottom - d, mRect.right, mRect.bottom);
                canvas.drawArc(mOvalRect[0], new Arc(180, 90, false), mPaint);
                canvas.drawArc(mOvalRect[1], new Arc(-90, 90, false), mPaint);
                canvas.drawArc(mOvalRect[2], new Arc(90, 90, false), mPaint);
                canvas.drawArc(mOvalRect[3], new Arc(90, -90, false), mPaint);
            }

            canvas.drawLine(mPointFs[0].getPointX(), mPointFs[0].getPointY(), mPointFs[1].getPointX(),
                    mPointFs[1].getPointY(), mPaint);
            canvas.drawLine(mPointFs[0].getPointX(), mPointFs[0].getPointY(), mPointFs[2].getPointX(),
                    mPointFs[2].getPointY(), mPaint);
        }
    }

    public float getSharpSize() {
        return mSharpSize;
    }

    public RgbColor[] getBgColor() {
        return mBgColor;
    }

    public RgbColor getBackgroundColor() {
        return mBackgroundColor;
    }

    public float getCornerRadius() {
        return mCornerRadius;
    }

    public float[] getCornerRadii() {
        return mCornerRadii;
    }

    public SharpView.ArrowDirection getArrowDirection() {
        return mArrowDirection;
    }

    public float getBorder() {
        return mBorder;
    }

    public RgbColor getBorderColor() {
        return mBorderColor;
    }

    public float getRelativePosition() {
        return mRelativePosition;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public RectFloat getRect() {
        return mRect;
    }

    public Point[] getPointFs() {
        return mPointFs;
    }

    public Component getComponent() {
        return mComponent;
    }
}