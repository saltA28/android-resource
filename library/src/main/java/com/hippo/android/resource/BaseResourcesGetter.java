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

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class BaseResourcesGetter implements ResourcesGetter {

  @SuppressWarnings("deprecation")
  @Override
  public ColorStateList getColorStateList(Context context, int id) {
    return context.getResources().getColorStateList(id);
  }

  @SuppressWarnings("deprecation")
  @Override
  public Drawable getDrawable(Context context, int id) {
    return context.getResources().getDrawable(id);
  }
}
