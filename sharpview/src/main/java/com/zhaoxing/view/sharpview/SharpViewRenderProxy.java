package com.zhaoxing.view.sharpview;

import static ohos.agp.components.Component.DrawTask.BETWEEN_BACKGROUND_AND_CONTENT;
import ohos.agp.colors.RgbColor;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.render.Canvas;

/**
 * SharpViewRenderProxy.
 */
public class SharpViewRenderProxy {

    private static final float DEFAULT_RADIUS = 0;
    private static final float DEFAULT_CORNERRADII = 0;
    private static final float DEFAULT_BORDER = 0;
    private static final RgbColor DEFAULT_BACKGROUNDCOLOR = RgbPalette.TRANSPARENT;
    private static final RgbColor DEFAULT_BORDERCOLOR = RgbPalette.TRANSPARENT;
    private static final float DEFAULT_RELATIVEPOSITION = (float) 0.5;
    private static final float DEFAULT_SHARPSIZE = 0;
    public static final String RADIUS = "radius";
    public static final String LEFT_TOP_RADIUS = "left_top_radius";
    public static final String RIGHT_TOP_RADIUS = "right_top_radius";
    public static final String RIGHT_BOTTOM_RADIUS = "right_bottom_radius";
    public static final String LEFT_BOTTOM_RADIUS = "left_bottom_radius";
    public static final String BORDER = "border";
    public static final String BORDER_COLOR = "borderColor";
    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String START_BG_COLOR = "startBgColor";
    public static final String MIDDLE_BG_COLOR = "middleBgColor";
    public static final String END_BG_COLOR = "endBgColor";
    public static final String ARROW_DIRECTION = "arrowDirection";
    public static final String RELATIVE_POSITION = "relativePosition";
    public static final String SHARP_SIZE = "sharpSize";
    private Component mComponent;
    private float mRadius;
    private RgbColor mBackgroundColor;
    private float mRelativePosition;
    private float mSharpSize;
    private float mBorder;
    private RgbColor mBorderColor;
    private RgbColor[] mBgColors;
    private float[] mCornerRadii = new float[8];
    private SharpView.ArrowDirection mArrowDirection = SharpView.ArrowDirection.LEFT;
    private SharpDrawable mSharpDrawable;

    SharpViewRenderProxy(Component component, AttrSet attrs) {
        mComponent = component;
        mRadius = DEFAULT_RADIUS;
        mCornerRadii[0] = mCornerRadii[1] = DEFAULT_CORNERRADII;
        mCornerRadii[2] = mCornerRadii[3] = DEFAULT_CORNERRADII;
        mCornerRadii[4] = mCornerRadii[5] = DEFAULT_CORNERRADII;
        mCornerRadii[6] = mCornerRadii[7] = DEFAULT_CORNERRADII;
        mBorder = DEFAULT_BORDER;
        mBackgroundColor = DEFAULT_BACKGROUNDCOLOR;
        mBorderColor = DEFAULT_BORDERCOLOR;
        mRelativePosition = DEFAULT_RELATIVEPOSITION;
        mSharpSize = DEFAULT_SHARPSIZE;

        if (attrs != null) {
            initAttributes(attrs);
        }
    }

    public SharpDrawable getSharpDrawable() {
        return mSharpDrawable;
    }

    public float getRadius() {
        return mRadius;
    }

    public RgbColor getBackgroundColor() {
        return mBackgroundColor;
    }

    public float getRelativePosition() {
        return mRelativePosition;
    }

    public float getSharpSize() {
        return mSharpSize;
    }

    public float getBorder() {
        return mBorder;
    }

    public RgbColor getBorderColor() {
        return mBorderColor;
    }

    public RgbColor[] getBgColors() {
        return mBgColors;
    }

    public float[] getCornerRadii() {
        return mCornerRadii;
    }

    public SharpView.ArrowDirection getArrowDirection() {
        return mArrowDirection;
    }

    public void setBgColor(RgbColor[] bgColor) {
        mBgColors = bgColor;
        refreshView();
    }

    public void setSharpDrawable(SharpDrawable sharpDrawable) {
        mSharpDrawable = sharpDrawable;
    }

    /**
     * setCornerRadii.
     *
     * @param leftTop top left radius.
     * @param rightTop top right radius.
     * @param rightBottom bottom right radius.
     * @param leftBottom bottom left radius.
     */
    public void setCornerRadii(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        mCornerRadii[0] = leftTop;
        mCornerRadii[1] = leftTop;
        mCornerRadii[2] = rightTop;
        mCornerRadii[3] = rightTop;
        mCornerRadii[4] = rightBottom;
        mCornerRadii[5] = rightBottom;
        mCornerRadii[6] = leftBottom;
        mCornerRadii[7] = leftBottom;

    }

    public void setBorder(float border) {
        mBorder = border;
        refreshView();
    }

    public void setBorderColor(RgbColor borderColor) {
        mBorderColor = borderColor;
        refreshView();
    }

    public void setRadius(float radius) {
        mRadius = radius;
        refreshView();
    }

    /**
     * setBackgroundColor.
     *
     * @param backgroundColor background color in Rgba format.
     */
    public void setBackgroundColor(RgbColor backgroundColor) {
        mBackgroundColor = backgroundColor;
        mBgColors = null;
        refreshView();
    }

    public void setRelativePosition(float relativePosition) {
        mRelativePosition = relativePosition;
        refreshView();
    }

    public void setSharpSize(float sharpSize) {
        mSharpSize = sharpSize;
        refreshView();
    }

    public void setArrowDirection(SharpView.ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
        refreshView();
    }

    private void checkRadiusAttr(AttrSet attrs) {
        if (attrs.getAttr(RADIUS).isPresent() && attrs.getAttr(RADIUS).get() != null) {
            mRadius = attrs.getAttr(RADIUS).get().getDimensionValue();
        }

        if (attrs.getAttr(LEFT_TOP_RADIUS).isPresent() && attrs.getAttr(LEFT_TOP_RADIUS).get() != null) {
            mCornerRadii[0] = mCornerRadii[1] = attrs.getAttr(LEFT_TOP_RADIUS).get().getDimensionValue();
        }

        if (attrs.getAttr(RIGHT_TOP_RADIUS).isPresent() && attrs.getAttr(RIGHT_TOP_RADIUS).get() != null) {
            mCornerRadii[2] = mCornerRadii[3] = attrs.getAttr(RIGHT_TOP_RADIUS).get().getDimensionValue();
        }

        if (attrs.getAttr(RIGHT_BOTTOM_RADIUS).isPresent() && attrs.getAttr(RIGHT_BOTTOM_RADIUS).get() != null) {
            mCornerRadii[4] = mCornerRadii[5] = attrs.getAttr(RIGHT_BOTTOM_RADIUS).get().getDimensionValue();
        }

        if (attrs.getAttr(LEFT_BOTTOM_RADIUS).isPresent() && attrs.getAttr(LEFT_BOTTOM_RADIUS).get() != null) {
            mCornerRadii[6] = mCornerRadii[7] = attrs.getAttr(LEFT_BOTTOM_RADIUS).get().getDimensionValue();
        }
    }

    private void checkBorderAttr(AttrSet attrs) {
        if (attrs.getAttr(BORDER).isPresent() && attrs.getAttr(BORDER).get() != null) {
            mBorder = attrs.getAttr(BORDER).get().getDimensionValue();
        }

        if (attrs.getAttr(BORDER_COLOR).isPresent() && attrs.getAttr(BORDER_COLOR).get() != null) {
            mBorderColor = new RgbColor(attrs.getAttr(BORDER_COLOR).get().getIntegerValue());
        }
    }

    private void checkColorAttr(AttrSet attrs) {
        if (attrs.getAttr(BACKGROUND_COLOR).isPresent() && attrs.getAttr(BACKGROUND_COLOR).get() != null) {
            mBackgroundColor = new RgbColor(attrs.getAttr(BACKGROUND_COLOR).get().getIntegerValue());
        }

        RgbColor start = RgbPalette.TRANSPARENT;
        RgbColor middle = RgbPalette.TRANSPARENT;
        RgbColor end = RgbPalette.TRANSPARENT;
        if (attrs.getAttr(START_BG_COLOR).isPresent() && attrs.getAttr(START_BG_COLOR).get() != null) {
            start = new RgbColor(attrs.getAttr(START_BG_COLOR).get().getIntegerValue());
        }
        if (attrs.getAttr(MIDDLE_BG_COLOR).isPresent() && attrs.getAttr(MIDDLE_BG_COLOR).get() != null) {
            middle  = new RgbColor(attrs.getAttr(MIDDLE_BG_COLOR).get().getIntegerValue());
        }
        if (attrs.getAttr(END_BG_COLOR).isPresent() && attrs.getAttr(END_BG_COLOR).get() != null) {
            end  = new RgbColor(attrs.getAttr(END_BG_COLOR).get().getIntegerValue());
        }
        if (start != RgbPalette.TRANSPARENT  && end != RgbPalette.TRANSPARENT) {
            if (middle != RgbPalette.TRANSPARENT) {
                mBgColors = new RgbColor[]{start, middle, end};
            } else {
                mBgColors = new RgbColor[]{start, end};
            }
        }
    }

    private void checkSharpAttr(AttrSet attrs) {
        if (attrs.getAttr(SHARP_SIZE).isPresent() && attrs.getAttr(SHARP_SIZE).get() != null) {
            mSharpSize = attrs.getAttr(SHARP_SIZE).get().getDimensionValue();
        }

        if (attrs.getAttr(ARROW_DIRECTION).isPresent() && attrs.getAttr(ARROW_DIRECTION).get() != null) {
            String direction = attrs.getAttr(ARROW_DIRECTION).get().getStringValue();
            switch (direction) {
                case "left":
                    mArrowDirection = SharpView.ArrowDirection.LEFT;
                    mComponent.setPaddingLeft((int) mSharpSize);
                    break;
                case "top":
                    mArrowDirection = SharpView.ArrowDirection.TOP;
                    mComponent.setPaddingTop((int) mSharpSize);
                    break;
                case "right":
                    mArrowDirection =  SharpView.ArrowDirection.RIGHT;
                    mComponent.setPaddingRight((int) mSharpSize);
                    break;
                case "bottom":
                    mArrowDirection = SharpView.ArrowDirection.BOTTOM;
                    mComponent.setPaddingBottom((int) mSharpSize);
                    break;
                default:
                    mArrowDirection = SharpView.ArrowDirection.LEFT;
                    mComponent.setPaddingLeft((int) mSharpSize);
            }
        }

        if (attrs.getAttr(RELATIVE_POSITION).isPresent() && attrs.getAttr(RELATIVE_POSITION).get() != null) {
            mRelativePosition = attrs.getAttr(RELATIVE_POSITION).get().getFloatValue();
        }
    }

    private void initAttributes(AttrSet attrs) {
        checkSharpAttr(attrs);
        checkColorAttr(attrs);
        checkBorderAttr(attrs);
        checkRadiusAttr(attrs);
        refreshView();
    }

    private void refreshView() {
        SharpDrawable bd = new SharpDrawable(ShapeElement.Orientation.LEFT_TO_RIGHT, mComponent);
        setSharpDrawable(bd);
        if (mBgColors != null) {
            bd.setBgColor(mBgColors);
        } else {
            bd.setBgColor(mBackgroundColor);
        }
        bd.setSharpSize(mSharpSize);
        bd.setArrowDirection(mArrowDirection);
        bd.setBorder(mBorder);
        bd.setBorderColor(mBorderColor);
        bd.setRelativePosition(mRelativePosition);
        bd.setCornerRadius(mRadius);
        if (mRadius == 0) {
            bd.setCornerRadii(mCornerRadii);
        }
        if (mComponent instanceof SharpImageView) {
            mComponent.invalidate();
        } else {
            mComponent.setBackground(bd);
            mComponent.addDrawTask(new Component.DrawTask() {
                @Override
                public void onDraw(Component component, Canvas canvas) {
                    bd.drawToCanvas(canvas);
                }
            }, BETWEEN_BACKGROUND_AND_CONTENT);
            mComponent.invalidate();
        }
    }
}
