package com.zhaoxing.view.sharpview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.TextField;
import ohos.app.Context;

/**
 * Created by 曾宪梓 on 2017/12/28.
 */
public class SharpEditText extends TextField implements SharpView {

    public SharpEditText(Context context) {
        super(context);
        init(null);
    }

    public SharpEditText(Context context, AttrSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SharpEditText(Context context, AttrSet attrs, String defStyleAttr) {
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
