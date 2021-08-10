package com.zhaoxing.view.sharpview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.render.BlendMode;
import ohos.agp.render.Canvas;
import ohos.agp.render.Canvas.PorterDuffMode;
import ohos.agp.render.Paint;
import ohos.agp.render.PixelMapHolder;
import ohos.agp.render.Texture;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;
import ohos.media.image.PixelMap;
import ohos.media.image.common.AlphaType;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Size;
import java.lang.ref.SoftReference;

/**
 * Created by 曾宪梓 on 2018/6/23.
 */
public class SharpImageView extends Image implements SharpView, Component.DrawTask {

    private SoftReference<PixelMap> mSoftBitmap;
    private SoftReference<PixelMap> mSoftOutBitmap;
    private Canvas mOutCanvas;
    private Paint mPaint;
    private RectFloat rect;
    private SharpViewRenderProxy mSharpViewRenderProxy;

    public SharpImageView(Context context) {
        super(context);
        init(null);
    }

    public SharpImageView(Context context, AttrSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    public SharpViewRenderProxy getRenderProxy() {
        return mSharpViewRenderProxy;
    }

    private void init(AttrSet attrs) {
        mPaint = new Paint();
        rect = new RectFloat();
        mSharpViewRenderProxy = new SharpViewRenderProxy(this, attrs);
        onSizeChanged();
        addDrawTask(this::onDraw, BETWEEN_BACKGROUND_AND_CONTENT);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        if (mSoftBitmap == null || mSoftOutBitmap == null) {
            initBitmap();
        }
        mPaint.setAntiAlias(true);
        mOutCanvas.drawColor(Color.TRANSPARENT.getValue(), PorterDuffMode.CLEAR);
        mSharpViewRenderProxy.getSharpDrawable().setPaint(mPaint);
        mSharpViewRenderProxy.getSharpDrawable().drawToCanvas(mOutCanvas);
        mPaint.setBlendMode(BlendMode.SRC_IN);
        mOutCanvas.drawPixelMapHolderRect(getPixelMapHolder(), rect, mPaint);
        mPaint.setBlendMode(null);
        setPixelMap(null);
        canvas.drawPixelMapHolderRect(new PixelMapHolder(mSoftOutBitmap.get()), rect, rect, mPaint);
    }

    private void initBitmap() {
        PixelMap.InitializationOptions initializationOptions = new PixelMap.InitializationOptions();
        initializationOptions.size = new Size(getWidth(), getHeight());
        initializationOptions.pixelFormat = PixelFormat.ARGB_8888;
        initializationOptions.scaleMode = ohos.media.image.common.ScaleMode.FIT_TARGET_SIZE;
        PixelMap inBitmap = PixelMap.create(initializationOptions);
        PixelMap outBitmap = PixelMap.create(initializationOptions);
        inBitmap.setAlphaType(AlphaType.UNPREMUL);
        outBitmap.setAlphaType(AlphaType.UNPREMUL);
        mOutCanvas = new Canvas(new Texture(outBitmap));
        mSoftBitmap = new SoftReference<>(inBitmap);
        mSoftOutBitmap = new SoftReference<>(outBitmap);
    }

    private void onSizeChanged() {
        initBitmap();
        rect.modify(0, 0, getWidth(), getHeight());
    }

}
