package com.zhaoxing.view.sharpview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.DirectionalLayout;
import ohos.app.Context;

/**
 * SharpLinearLayout.
 */
public class SharpLinearLayout extends DirectionalLayout implements SharpView {

    public SharpLinearLayout(Context context) {
        super(context);
        init(null);
    }

    public SharpLinearLayout(Context context, AttrSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SharpLinearLayout(Context context, AttrSet attrs, String defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttrSet attrs) {
        mSharpViewRenderProxy = new SharpViewRenderProxy(this, attrs);
    }

    private SharpViewRenderProxy mSharpViewRenderProxy;

    @Override
    public SharpViewRenderProxy getRenderProxy() {
        return mSharpViewRenderProxy;
    }
}
