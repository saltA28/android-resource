/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.android.resource;

/*
 * Created by Hippo on 5/8/2017.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;
import com.hippo.android.resource.test.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AttrResourcesTest {

  @Before
  public void before() {
    Context app = InstrumentationRegistry.getTargetContext();
    app.setTheme(R.style.AppTheme);
  }
  @Test
  public void testGetAttrBoolean() {
    Context app = InstrumentationRegistry.getTargetContext();
    assertEquals(false, AttrResources.getAttrBoolean(app, R.attr.attr_boolean_1));
    assertEquals(true, AttrResources.getAttrBoolean(app, R.attr.attr_boolean_2));
    try {
      assertEquals(false, AttrResources.getAttrBoolean(app, R.attr.attr_float_1));
      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  @Test
  public void testGetAttrInteger() {
    Context app = InstrumentationRegistry.getTargetContext();
    assertEquals(54321, AttrResources.getAttrInteger(app, R.attr.attr_integer_1));
    assertEquals(12345, AttrResources.getAttrInteger(app, R.attr.attr_integer_2));
    try {
      assertEquals(12345, AttrResources.getAttrInteger(app, R.attr.attr_float_1));
      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  @Test
  public void testGetAttrFloat() {
    Context app = InstrumentationRegistry.getTargetContext();
    assertEqualsFloat(6.5f, AttrResources.getAttrFloat(app, R.attr.attr_float_1));
    assertEqualsFloat(5.6f, AttrResources.getAttrFloat(app, R.attr.attr_float_2));
    try {
      assertEqualsFloat(6.5f, AttrResources.getAttrFloat(app, R.attr.attr_integer_1));
      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  @Test
  public void testGetAttrDimension() {
    Context app = InstrumentationRegistry.getTargetContext();
    DisplayMetrics metrics = app.getResources().getDisplayMetrics();
    assertEqualsFloat(20 * metrics.density, AttrResources.getAttrDimension(app, R.attr.attr_dimension_1));
    assertEqualsFloat(10 * metrics.density, AttrResources.getAttrDimension(app, R.attr.attr_dimension_2));
    try {
      assertEqualsFloat(20 * metrics.density, AttrResources.getAttrDimension(app, R.attr.attr_float_1));
      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  @Test
  public void testGetAttrDimensionPixelOffset() {
    Context app = InstrumentationRegistry.getTargetContext();
    DisplayMetrics metrics = app.getResources().getDisplayMetrics();
    assertEquals((int) (20 * metrics.density), AttrResources.getAttrDimensionPixelOffset(app, R.attr.attr_dimension_1));
    assertEquals((int) (10 * metrics.density), AttrResources.getAttrDimensionPixelOffset(app, R.attr.attr_dimension_2));
    try {
      assertEquals((int) (20 * metrics.density), AttrResources.getAttrDimensionPixelOffset(app, R.attr.attr_float_1));
      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  private static int size(float f) {
    final int res = (int) (f + 0.5f);
    if (res != 0) return res;
    return -1;
  }

  @Test
  public void testGetAttrDimensionPixelSize() {
    Context app = InstrumentationRegistry.getTargetContext();
    DisplayMetrics metrics = app.getResources().getDisplayMetrics();
    assertEquals(size(20 * metrics.density), AttrResources.getAttrDimensionPixelSize(app, R.attr.attr_dimension_1));
    assertEquals(size(10 * metrics.density), AttrResources.getAttrDimensionPixelSize(app, R.attr.attr_dimension_2));
    try {
      assertEquals(size(20 * metrics.density), AttrResources.getAttrDimensionPixelSize(app, R.attr.attr_float_1));      fail();
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  @Test
  public void testGetAttrColor() {
    Context app = InstrumentationRegistry.getTargetContext();
    assertEquals(Color.BLUE, AttrResources.getAttrColor(app, R.attr.attr_color_1));
    assertEquals(Color.RED, AttrResources.getAttrColor(app, R.attr.attr_color_2));
    assertEquals(Color.RED, AttrResources.getAttrColor(app, R.attr.attr_color_3));
    assertEquals(Color.GREEN, AttrResources.getAttrColor(app, R.attr.attr_color_4));
    try {
      assertEquals(Color.BLUE, AttrResources.getAttrColor(app, R.attr.attr_float_1));
    } catch (Resources.NotFoundException e) {
      // Ignore
    }
  }

  @Test
  public void testGetAttrColorStateList() {
    Context app = InstrumentationRegistry.getTargetContext();
    ColorStateList colorStateList = AttrResources.getAttrColorStateList(app, R.attr.attr_color_state_list_1);
    assertNotNull(colorStateList);
    assertEquals(Color.GREEN, colorStateList.getDefaultColor());
    assertEquals(Color.RED, colorStateList.getColorForState(new int[]{android.R.attr.state_pressed}, Color.BLACK));
    assertEquals(Color.BLUE, colorStateList.getColorForState(new int[]{android.R.attr.state_focused}, Color.BLACK));
  }

  @Test
  public void testGetAttrDrawable() {
    Context app = InstrumentationRegistry.getTargetContext();

    Drawable drawable1 = AttrResources.getAttrDrawable(app, R.attr.attr_drawable_1);
    assertTrue(drawable1 instanceof ColorDrawable);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      assertEquals(Color.BLUE, ((ColorDrawable) drawable1).getColor());
    }

    Drawable drawable2 = AttrResources.getAttrDrawable(app, R.attr.attr_drawable_2);
    assertTrue(drawable2 instanceof GradientDrawable);

    Drawable drawable3 = AttrResources.getAttrDrawable(app, R.attr.attr_drawable_3);
    assertTrue(drawable3 instanceof BitmapDrawable);
  }


  private static String formatClassAndValue(Object value, String valueString) {
    String className = value == null ? "null" : value.getClass().getName();
    return className + "<" + valueString + ">";
  }

  private static String format(String message, Object expected, Object actual) {
    String formatted = "";
    if (message != null && !message.equals("")) {
      formatted = message + " ";
    }
    String expectedString = String.valueOf(expected);
    String actualString = String.valueOf(actual);
    if (expectedString.equals(actualString)) {
      return formatted + "expected: "
          + formatClassAndValue(expected, expectedString)
          + " but was: " + formatClassAndValue(actual, actualString);
    } else {
      return formatted + "expected:<" + expectedString + "> but was:<"
          + actualString + ">";
    }
  }

  public static void assertEqualsFloat(float expected, float actual) {
    if (expected != actual) {
      throw new AssertionError(format(null, expected, actual));
    }
  }
}
