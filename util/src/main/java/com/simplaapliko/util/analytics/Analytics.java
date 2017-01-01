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

public interface Analytics {

    /**
     * Enum used to identify the tracker that needs to be used for tracking.
     */
    enum TrackerName {
        APP_TRACKER, // Tracker used only in this app.
        GLOBAL_TRACKER // Tracker used by all the apps.
    }

    void sendScreen(TrackerName trackerId, String screen);

    void sendEvent(TrackerName trackerId, String category, String action, String label);

    void sendCampaign(TrackerName trackerId, String campaignData);
}
