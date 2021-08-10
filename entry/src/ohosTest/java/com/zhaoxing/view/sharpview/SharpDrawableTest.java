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

public class SharpDrawableTest {

    private SharpDrawable sharpDrawable;

    @Before
    public void setUp() {
        AttrSet attrSet;
        Context context;
        Image image;
        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        attrSet = new AttrSet() {
            @Override
            public Optional<String> getStyle() {
                return Optional.empty();
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Optional<Attr> getAttr(String s) {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(int i) {
                return Optional.empty();
            }

        };
        image = new Image(context, attrSet);
        sharpDrawable = new SharpDrawable(ShapeElement.Orientation.LEFT_TO_RIGHT, image);
    }

    @Test
    public void setBgColor() {
        RgbColor[] rgbColors = new RgbColor[]{RgbPalette.BLUE, RgbPalette.GREEN};
        sharpDrawable.setBgColor(rgbColors);
        assertArrayEquals(rgbColors, sharpDrawable.getBgColor());
    }

    @Test
    public void setCornerRadius() {
        float radius = 10;
        sharpDrawable.setCornerRadius(radius);
        assertTrue(abs(radius-sharpDrawable.getCornerRadius()) < 0.5);
    }

    @Test
    public void setCornerRadii() {
        float[] radii = {1, 1, 1, 1, 1, 1, 1, 1};
        sharpDrawable.setCornerRadii(radii);
        assertArrayEquals(radii, sharpDrawable.getCornerRadii(), 1);
    }

    @Test
    public void setArrowDirection() {
        sharpDrawable.setArrowDirection(SharpView.ArrowDirection.LEFT);
        assertSame(SharpView.ArrowDirection.LEFT, sharpDrawable.getArrowDirection());
    }

    @Test
    public void setRelativePosition() {
        double relativePosition = 0.5;
        sharpDrawable.setRelativePosition((float) relativePosition);
        assertTrue(abs(relativePosition-sharpDrawable.getRelativePosition()) < 0.5);
    }

    @Test
    public void setBorder() {
        float border = 10;
        sharpDrawable.setBorder(border);
        assertTrue(abs(border-sharpDrawable.getBorder()) < 0.5);
    }

    @Test
    public void setBorderColor() {
        sharpDrawable.setBorderColor(RgbPalette.BLACK);
        assertSame(RgbPalette.BLACK, sharpDrawable.getBorderColor());
    }

    @Test
    public void setSharpSize() {
        float sharpSize = 10;
        sharpDrawable.setSharpSize(sharpSize);
        assertTrue(abs(sharpSize-sharpDrawable.getSharpSize()) < 0.5);
    }
}