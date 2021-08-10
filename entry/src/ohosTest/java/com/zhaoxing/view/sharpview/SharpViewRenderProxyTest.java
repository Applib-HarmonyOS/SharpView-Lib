/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhaoxing.view.sharpview;

import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.colors.RgbColor;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Image;
import ohos.agp.components.element.ShapeElement;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

public class SharpViewRenderProxyTest {

    private SharpViewRenderProxy sharpViewRenderProxy;

    @Before
    public void setUp() {
        Context context;
        AttrSet attrSet;
        Image image;
        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        attrSet = new AttrSet() {
            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Optional<String> getStyle() {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(int i) {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(String s) {
                return Optional.empty();
            }
        };
        image = new Image(context, attrSet);
        sharpViewRenderProxy = new SharpViewRenderProxy(image, attrSet);
    }

    @Test
    public void setBgColor() {
        RgbColor[] rgbColors = new RgbColor[]{RgbPalette.BLUE, RgbPalette.GREEN};
        sharpViewRenderProxy.setBgColor(rgbColors);
        assertArrayEquals(rgbColors, sharpViewRenderProxy.getBgColors());
    }

    @Test
    public void setCornerRadii() {
        float radius = 1;
        float[] radii = {1, 1, 1, 1, 1, 1, 1, 1};
        sharpViewRenderProxy.setCornerRadii(radius, radius, radius, radius);
        assertArrayEquals(radii, sharpViewRenderProxy.getCornerRadii(), 1);
    }

    @Test
    public void setBorder() {
        float border = 10;
        sharpViewRenderProxy.setBorder(border);
        assertTrue(abs(border-sharpViewRenderProxy.getBorder()) < 0.5);
    }

    @Test
    public void setBorderColor() {
        sharpViewRenderProxy.setBorderColor(RgbPalette.BLACK);
        assertSame(RgbPalette.BLACK, sharpViewRenderProxy.getBorderColor());
    }

    @Test
    public void setRadius() {
        float radius = 10;
        sharpViewRenderProxy.setRadius(radius);
        assertTrue(abs(radius-sharpViewRenderProxy.getRadius()) < 0.5);
    }

    @Test
    public void setBackgroundColor() {
        sharpViewRenderProxy.setBackgroundColor(RgbPalette.BLACK);
        assertSame(RgbPalette.BLACK, sharpViewRenderProxy.getBackgroundColor());
    }

    @Test
    public void setRelativePosition() {
        float relativePosition = 10;
        sharpViewRenderProxy.setRelativePosition(relativePosition);
        assertTrue(abs(relativePosition-sharpViewRenderProxy.getRelativePosition()) < 0.5);
    }

    @Test
    public void setSharpSize() {
        float sharpSize = 10;
        sharpViewRenderProxy.setSharpSize(sharpSize);
        assertTrue(abs(sharpSize-sharpViewRenderProxy.getSharpSize()) < 0.5);
    }

    @Test
    public void setArrowDirection() {
        sharpViewRenderProxy.setArrowDirection(SharpView.ArrowDirection.LEFT);
        assertSame(SharpView.ArrowDirection.LEFT, sharpViewRenderProxy.getArrowDirection());
    }
}