/*
 * Copyright (C) 2015 Oleg Kan, @Simplaapliko
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

package com.simplaapliko.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

public class Screen {

    @SuppressWarnings("deprecation")
    public static int getOrientation(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int orientation;

        if (dm.widthPixels > dm.heightPixels) {
            orientation = Configuration.ORIENTATION_LANDSCAPE;
        } else if (dm.widthPixels < dm.heightPixels) {
            orientation = Configuration.ORIENTATION_PORTRAIT;
        } else {
            orientation = Configuration.ORIENTATION_SQUARE;
        }

        return orientation;
    }
}
