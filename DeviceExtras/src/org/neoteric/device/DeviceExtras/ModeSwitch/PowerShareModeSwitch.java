/*
* Copyright (C) 2016 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.neoteric.device.DeviceExtras;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;

import org.neoteric.device.DeviceExtras.FileUtils;

public class PowerShareModeSwitch implements OnPreferenceChangeListener {

    public static final String FILE = "/sys/class/qcom-battery/wireless_boost_en";

    public static boolean isSupported() {
        return FileUtils.fileWritable(FILE);
    }

    public static boolean isCurrentlyEnabled() {
        return (isSupported() && FileUtils.getFileValueAsBoolean(FILE, false));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Boolean enabled = (Boolean) newValue;
        FileUtils.writeValue(FILE, enabled ? "1" : "0");
        return true;
    }
}
