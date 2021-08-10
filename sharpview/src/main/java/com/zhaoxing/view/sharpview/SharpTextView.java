package com.zhaoxing.view.sharpview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Text;
import ohos.app.Context;

/**
 * SharpTextView.
 */
public class SharpTextView extends Text implements SharpView {

    public SharpTextView(Context context) {
        super(context);
        init(null);
    }

    public SharpTextView(Context context, AttrSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SharpTextView(Context context, AttrSet attrs, String defStyleAttr) {
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
