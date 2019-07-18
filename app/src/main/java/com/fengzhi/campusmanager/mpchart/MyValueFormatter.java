package com.fengzhi.campusmanager.mpchart;

import com.fengzhi.campusmanager.util.StringUtils;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class MyValueFormatter extends ValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        return StringUtils.double2String(value, 2);
    }

}
