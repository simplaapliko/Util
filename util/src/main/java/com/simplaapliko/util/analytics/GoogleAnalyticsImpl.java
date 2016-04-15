/*
 * Copyright (C) 2014-2015 Oleg Kan, @Simplaapliko
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

package com.simplaapliko.util.analytics;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.simplaapliko.util.R;

import java.util.HashMap;
import java.util.Map;

public final class GoogleAnalyticsImpl implements Analytics {

    private static final String TAG = "GoogleAnalyticsImpl";

    private Map<TrackerName, Tracker> mTrackers = new HashMap<>();
    private final Context mContext;


    // constructors

    public GoogleAnalyticsImpl(Context context) {
        mContext = context.getApplicationContext();
    }


    // class methods

    @Override
    public void sendScreen(TrackerName trackerId, String screen) {
        Log.d(TAG, "sendScreen(), screen = " + screen);

        Tracker t = getTracker(trackerId);

        // Set screen name.
        t.setScreenName(screen);

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void sendScreen(TrackerName trackerId, int screenId) {
        sendScreen(trackerId, mContext.getString(screenId));
    }

    @Override
    public void sendEvent(TrackerName trackerId, String category, String action, String label) {
        Log.d(TAG, "sendEvent(), category = " + category + ", action = " + action + ", label = " + label);

        Tracker t = getTracker(trackerId);

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }

    @Override
    public void sendEvent(TrackerName trackerId, String category, String action, int labelId) {
        sendEvent(trackerId,
                category,
                action,
                mContext.getString(labelId));
    }

    @Override
    public void sendEvent(TrackerName trackerId, String category, int actionId, String label) {
        sendEvent(trackerId,
                category,
                mContext.getString(actionId),
                label);
    }

    @Override
    public void sendEvent(TrackerName trackerId, String category, int actionId, int labelId) {
        sendEvent(trackerId,
                category,
                mContext.getString(actionId),
                mContext.getString(labelId));
    }

    @Override
    public void sendEvent(TrackerName trackerId, int categoryId, int actionId, int labelId) {
        sendEvent(trackerId,
                mContext.getString(categoryId),
                mContext.getString(actionId),
                mContext.getString(labelId));
    }

    @Override
    public void sendEvent(TrackerName trackerId, int categoryId, int actionId, String label) {
        sendEvent(trackerId,
                mContext.getString(categoryId),
                mContext.getString(actionId),
                label);
    }

    @Override
    public void sendEvent(TrackerName trackerId, int categoryId, String action, int labelId) {
        sendEvent(trackerId,
                mContext.getString(categoryId),
                action,
                mContext.getString(labelId));
    }

    @Override
    public void sendEvent(TrackerName trackerId, int categoryId, String action, String label) {
        sendEvent(trackerId,
                mContext.getString(categoryId),
                action,
                label);
    }

    private synchronized Tracker getTracker(TrackerName trackerId) {
        if (!mTrackers.containsKey(trackerId)) {

            Tracker tracker = null;
            if (trackerId == TrackerName.APP_TRACKER) {
                tracker = GoogleAnalytics.getInstance(mContext).newTracker(R.xml.u_google_analytics_app_tracker);
            } else if (trackerId == TrackerName.GLOBAL_TRACKER) {
                tracker = GoogleAnalytics.getInstance(mContext).newTracker(R.xml.u_google_analytics_global_tracker);
            }

            mTrackers.put(trackerId, tracker);
        }

        return mTrackers.get(trackerId);
    }

}