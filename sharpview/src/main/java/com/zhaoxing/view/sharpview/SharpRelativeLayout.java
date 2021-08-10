package com.zhaoxing.view.sharpview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.DependentLayout;
import ohos.app.Context;

/**
 * SharpRelativeLayout.
 */
public class SharpRelativeLayout extends DependentLayout implements SharpView {

    public SharpRelativeLayout(Context context) {
        super(context);
        init(null);
    }

    public SharpRelativeLayout(Context context, AttrSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SharpRelativeLayout(Context context, AttrSet attrs, String defStyleAttr) {
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
