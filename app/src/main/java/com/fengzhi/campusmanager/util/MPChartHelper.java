package com.fengzhi.campusmanager.util;

import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.fengzhi.campusmanager.R;
import com.fengzhi.campusmanager.mpchart.MPChartMarkerView;
import com.fengzhi.campusmanager.mpchart.MyValueFormatter;
import com.fengzhi.campusmanager.mpchart.StringAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MPChartHelper {

    public static final int[] PIE_COLORS = {Color.rgb(181, 194, 202), Color.rgb(129, 216, 200),
            Color.rgb(241, 214, 145), Color.rgb(108, 176, 223), Color.rgb(195, 221, 155),
            Color.rgb(251, 215, 191), Color.rgb(237, 189, 189), Color.rgb(172, 217, 243)};

    public static final int[] LINE_COLORS = {Color.rgb(140, 210, 118), Color.rgb(159, 143, 186),
            Color.rgb(233, 197, 23)};//绿色，紫色，黄色

    public static final int[] LINE_FILL_COLORS = {Color.rgb(222, 239, 228), Color.rgb(246, 234,
            208), Color.rgb(235, 228, 248)};


    /**
     * 单数据集。设置柱状图样式，X轴为字符串，Y轴为数值
     *
     * @param barChart
     * @param xAxisValue
     * @param yAxisValue
     * @param title         图例文字
     * @param xAxisTextSize x轴标签字体大小
     * @param barColor
     */
    public static void setBarChart(BarChart barChart, List<String> xAxisValue,
                                   List<Float> yAxisValue, String title, float xAxisTextSize,
                                   Integer barColor) {
        barChart.getDescription().setEnabled(false);//设置描述  在右下角的描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图

        //设置自定义的markerView
        MPChartMarkerView markerView = new MPChartMarkerView(barChart.getContext(),
                R.layout.custom_marker_view);
        barChart.setMarker(markerView);

        //x坐标轴设置
        ValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);//设置自定义的x轴值格式化器
        XAxis xAxis = barChart.getXAxis();//获取x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴标签显示位置
        xAxis.setDrawGridLines(false);//不绘制网格
        xAxis.setGranularity(1f);//设置最小间隔，防止当放大时，出现重复标签。
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setTextSize(xAxisTextSize);//设置标签字体大小
        xAxis.setLabelCount(xAxisValue.size());//设置标签显示的个数
        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();//获取左侧y轴
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);//设置y轴标签显示在内侧
        leftAxis.setAxisMinimum(0f);//设置Y轴最小值
        leftAxis.setDrawGridLines(true);//绘制网格
        leftAxis.setDrawLabels(true);//绘制y轴标签
        leftAxis.setDrawAxisLine(true);//绘制y轴


        barChart.getAxisRight().setEnabled(false);//禁用右侧y轴


        //设置默认高光
//        Highlight highlight1 = new Highlight(1, 0, 0);
//        barChart.highlightValues(new Highlight[]{highlight1});

        //图例设置
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//图例水平居中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例的方向为水平
        legend.setDrawInside(false);//绘制在chart的外侧
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//图例中的文字方向
        legend.setForm(Legend.LegendForm.SQUARE);//图例窗体的形状
        legend.setFormSize(0f);//图例窗体的大小
        legend.setTextSize(12f);//图例文字的大小
        //legend.setYOffset(-2f);

        //设置柱状图数据
        setBarChartData(barChart, yAxisValue, title, barColor);

        barChart.setExtraBottomOffset(10);//距视图窗口底部的偏移，类似与paddingbottom
        barChart.setExtraTopOffset(10);//距视图窗口顶部的偏移，类似与paddingtop
        barChart.setScaleEnabled(false);//禁止缩放
        barChart.setDoubleTapToZoomEnabled(false);//禁止双击放大
        barChart.setFitBars(true);//使两侧的柱图完全显示
//        barChart.animateX(1500);//数据显示动画，从左往右依次显示
        barChart.setDrawValueAboveBar(true);
//        barChart.invalidate();
    }

    /**
     * 设置柱图
     *
     * @param barChart
     * @param yAxisValue
     * @param title
     * @param barColor
     */
    private static void setBarChartData(BarChart barChart, List<Float> yAxisValue, String title,
                                        Integer barColor) {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0, n = yAxisValue.size(); i < n; ++i) {
            entries.add(new BarEntry(i, yAxisValue.get(i)));
        }
        BarDataSet set1;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(entries);
            set1.setHighLightAlpha(37);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(entries, title);
            if (barColor == null) {
                set1.setColor(ContextCompat.getColor(barChart.getContext(), R.color.bar));
                //设置set1的柱的颜色
//                set1.setColors(new int[]{Color.rgb(255, 241, 226),Color.rgb(155, 241, 226),
//                Color.rgb(255, 21, 226),Color.rgb(255, 241, 26)}, 0);//设置set1的柱的颜色
            } else {
//                set1.setColors(new int[]{Color.rgb(255, 241, 226),Color.rgb(155, 241, 226),
//                Color.rgb(255, 21, 226),Color.rgb(255, 241, 26)}, 0);//设置set1的柱的颜色
                set1.setColor(barColor);
            }

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.8f);
            data.setValueFormatter(new MyValueFormatter());
            data.setValueTextSize(8f);//设置字体大小
            barChart.setData(data);

        }
    }


    /**
     * 设置双柱状图样式
     *
     * @param barChart
     * @param xAxisValue
     * @param yAxisValue1
     * @param yAxisValue2
     * @param bartilte1
     * @param bartitle2
     */
    public static void setTwoBarChart(BarChart barChart, List<Integer> xAxisValue,
                                      List<Float> yAxisValue1, List<Float> yAxisValue2,
                                      String bartilte1, String bartitle2) {
        barChart.getDescription().setEnabled(false);//设置描述
        barChart.setPinchZoom(true);//设置按比例放缩柱状图
        barChart.setExtraBottomOffset(10);
        barChart.setExtraTopOffset(30);

        MPChartMarkerView markerView = new MPChartMarkerView(barChart.getContext(),
                R.layout.custom_marker_view);
        barChart.setMarker(markerView);

        //x坐标轴设置
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        xAxis.setCenterAxisLabels(true);//设置标签居中
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return String.valueOf((int) v);
            }
        });

        //y轴设置
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawAxisLine(false);

        //设置坐标轴最大最小值
        Float yMin1 = Collections.min(yAxisValue1);
        Float yMin2 = Collections.min(yAxisValue2);
        Float yMax1 = Collections.max(yAxisValue1);
        Float yMax2 = Collections.max(yAxisValue2);
        Float yMin = Double.valueOf((yMin1 < yMin2 ? yMin1 : yMin2) * 0.1).floatValue();
        Float yMax = Double.valueOf((yMax1 > yMax2 ? yMax1 : yMax2) * 1.1).floatValue();
        leftAxis.setAxisMaximum(yMax);
        leftAxis.setAxisMinimum(yMin);

        barChart.getAxisRight().setEnabled(false);


        //图例设置
        Legend legend = barChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);

        //设置柱状图数据
        setTwoBarChartData(barChart, xAxisValue, yAxisValue1, yAxisValue2, bartilte1, bartitle2);

        barChart.animateX(1500);//数据显示动画，从左往右依次显示
        barChart.invalidate();
    }

    /**
     * 设置柱状图数据源
     */
    private static void setTwoBarChartData(BarChart barChart, List<Integer> xAxisValue,
                                           List<Float> yAxisValue1, List<Float> yAxisValue2,
                                           String bartilte1, String bartitle2) {
        float groupSpace = 0.04f;
        float barSpace = 0.03f;
        float barWidth = 0.45f;
        // (0.45 + 0.03) * 2 + 0.04 = 1，即一个间隔为一组，包含两个柱图 -> interval per "group"

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        for (int i = 0, n = yAxisValue1.size(); i < n; ++i) {
            entries1.add(new BarEntry(xAxisValue.get(i), yAxisValue1.get(i)));
            entries2.add(new BarEntry(xAxisValue.get(i), yAxisValue2.get(i)));
        }

        BarDataSet dataset1, dataset2;

        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            dataset1 = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            dataset2 = (BarDataSet) barChart.getData().getDataSetByIndex(1);
            dataset1.setValues(entries1);
            dataset2.setValues(entries2);
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            dataset1 = new BarDataSet(entries1, bartilte1);
            dataset2 = new BarDataSet(entries2, bartitle2);

            dataset1.setColor(Color.rgb(129, 216, 200));
            dataset2.setColor(Color.rgb(181, 194, 202));

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataset1);
            dataSets.add(dataset2);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            data.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int i,
                                                ViewPortHandler viewPortHandler) {
                    return StringUtils.double2String(value, 2);
                }
            });

            barChart.setData(data);
        }

        barChart.getBarData().setBarWidth(barWidth);
        barChart.getXAxis().setAxisMinimum(xAxisValue.get(0));
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based
        // on the provided parameters
        barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace,
                barSpace) * xAxisValue.size() + xAxisValue.get(0));
        barChart.groupBars(xAxisValue.get(0), groupSpace, barSpace);
    }


    /**
     * 单线单y轴图。
     *
     * @param lineChart
     * @param xAxisValue
     * @param yAxisValue
     * @param title
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     */
    public static void setLineChart(LineChart lineChart, List<String> xAxisValue,
                                    List<Float> yAxisValue, String title, boolean showSetValues) {
        List<List<Float>> entriesList = new ArrayList<>();
        entriesList.add(yAxisValue);

        List<String> titles = new ArrayList<>();
        titles.add(title);

        setLinesChart(lineChart, xAxisValue, entriesList, titles, showSetValues, null);
    }

    /**
     * 绘制线图，默认最多绘制三种颜色。所有线均依赖左侧y轴显示。
     *
     * @param lineChart
     * @param xAxisValue    x轴的轴
     * @param yXAxisValues  y轴的值
     * @param titles        每一个数据系列的标题
     * @param showSetValues 是否在折线上显示数据集的值。true为显示，此时y轴上的数值不可见，否则相反。
     * @param lineColors    线的颜色数组。为null时取默认颜色，此时最多绘制三种颜色。
     */
    public static void setLinesChart(LineChart lineChart, List<String> xAxisValue,
                                     List<List<Float>> yXAxisValues, List<String> titles,
                                     boolean showSetValues, int[] lineColors) {
        lineChart.getDescription().setEnabled(false);//设置描述
        lineChart.setPinchZoom(true);//设置按比例放缩柱状图

        MPChartMarkerView markerView = new MPChartMarkerView(lineChart.getContext(),
                R.layout.custom_marker_view);
        lineChart.setMarker(markerView);


        //x坐标轴设置
        ValueFormatter xAxisFormatter = new StringAxisValueFormatter(xAxisValue);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValue.size());
        /*xAxis.setAxisLineWidth(2f);*/
        xAxis.setValueFormatter(xAxisFormatter);

        //y轴设置
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        if (showSetValues) {
            leftAxis.setDrawLabels(false);//折线上显示值，则不显示坐标轴上的值
        }
        //leftAxis.setDrawZeroLine(true);
        /*leftAxis.setAxisMinimum(0f);*/
        /*leftAxis.setAxisLineWidth(2f);*/

        lineChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = lineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);

        //设置柱状图数据
        setLinesChartData(lineChart, yXAxisValues, titles, showSetValues, lineColors);

        lineChart.setExtraOffsets(10, 30, 20, 10);
        lineChart.animateX(1500);//数据显示动画，从左往右依次显示
    }

    private static void setLinesChartData(LineChart lineChart, List<List<Float>> yXAxisValues,
                                          List<String> titles, boolean showSetValues,
                                          int[] lineColors) {

        List<List<Entry>> entriesList = new ArrayList<>();
        for (int i = 0; i < yXAxisValues.size(); ++i) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0, n = yXAxisValues.get(i).size(); j < n; j++) {
                entries.add(new Entry(j, yXAxisValues.get(i).get(j)));
            }
            entriesList.add(entries);
        }

        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {

            for (int i = 0; i < lineChart.getData().getDataSetCount(); ++i) {
                LineDataSet set = (LineDataSet) lineChart.getData().getDataSetByIndex(i);
                set.setValues(entriesList.get(i));
                set.setLabel(titles.get(i));
            }

            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();

            for (int i = 0; i < entriesList.size(); ++i) {
                LineDataSet set = new LineDataSet(entriesList.get(i), titles.get(i));
                if (lineColors != null) {
                    set.setColor(lineColors[i % entriesList.size()]);
                    set.setCircleColor(lineColors[i % entriesList.size()]);
                    set.setCircleHoleColor(Color.WHITE);
                } else {
                    set.setColor(LINE_COLORS[i % 3]);
                    set.setCircleColor(LINE_COLORS[i % 3]);
                    set.setCircleHoleColor(Color.WHITE);
                }

                if (entriesList.size() == 1) {
                    set.setDrawFilled(true);
                    set.setFillColor(LINE_FILL_COLORS[i % 3]);
                }
                dataSets.add(set);
            }

            LineData data = new LineData(dataSets);
            if (showSetValues) {
                data.setValueTextSize(10f);
                data.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int i,
                                                    ViewPortHandler viewPortHandler) {
                        return StringUtils.double2String(value, 1);
                    }
                });
            } else {
                data.setDrawValues(true);
            }

            lineChart.setData(data);
        }
    }


    /**
     * 设置柱线组合图样式，柱图依赖左侧y轴，线图依赖右侧y轴
     */
    public static void setCombineChart(CombinedChart combineChart, final List<String> xAxisValues
            , List<Float> lineValues, List<Float> barValues, String lineTitle, String barTitle) {
        combineChart.getDescription().setEnabled(false);//设置描述
        combineChart.setPinchZoom(true);//设置按比例放缩柱状图

        MPChartMarkerView markerView = new MPChartMarkerView(combineChart.getContext(),
                R.layout.custom_marker_view);
        combineChart.setMarker(markerView);

        //设置绘制顺序，让线在柱的上层
        combineChart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR,
                CombinedChart.DrawOrder.LINE});

        //x坐标轴设置
        XAxis xAxis = combineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(xAxisValues.size() + 2);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if (v < 0 || v > (xAxisValues.size() - 1))//使得两侧柱子完全显示
                    return "";
                return xAxisValues.get((int) v);
            }
        });

        //y轴设置
        YAxis leftAxis = combineChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setDrawGridLines(false);
        /*leftAxis.setAxisMinimum(0f);*/

        Float yMin = Double.valueOf(Collections.min(barValues) * 0.9).floatValue();
        Float yMax = Double.valueOf(Collections.max(barValues) * 1.1).floatValue();
        leftAxis.setAxisMaximum(yMax);
        leftAxis.setAxisMinimum(yMin);


        YAxis rightAxis = combineChart.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setDrawGridLines(false);

        //图例设置
        Legend legend = combineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextSize(12f);

        //设置组合图数据
        CombinedData data = new CombinedData();
        data.setData(generateLineData(lineValues, lineTitle));
        data.setData(generateBarData(barValues, barTitle));

        combineChart.setData(data);//设置组合图数据源


        //使得两侧柱子完全显示
        xAxis.setAxisMinimum(combineChart.getCombinedData().getXMin() - 1f);
        xAxis.setAxisMaximum(combineChart.getCombinedData().getXMax() + 1f);

        combineChart.setExtraTopOffset(30);
        combineChart.setExtraBottomOffset(10);
        combineChart.animateX(1500);//数据显示动画，从左往右依次显示
        combineChart.invalidate();
    }

    /**
     * 生成线图数据
     */
    private static LineData generateLineData(List<Float> lineValues, String lineTitle) {
        ArrayList<Entry> lineEntries = new ArrayList<>();

        for (int i = 0, n = lineValues.size(); i < n; ++i) {
            lineEntries.add(new Entry(i, lineValues.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, lineTitle);
        lineDataSet.setColor(Color.rgb(233, 196, 21));
        lineDataSet.setLineWidth(2.5f);//设置线的宽度
        lineDataSet.setCircleColor(Color.rgb(244, 219, 100));//设置圆圈的颜色
        lineDataSet.setCircleHoleColor(Color.WHITE);//设置圆圈内部洞的颜色
        //lineDataSet.setValueTextColor(Color.rgb(254,116,139));
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于右侧y轴
        lineDataSet.setDrawValues(true);//绘制线的数据

        LineData lineData = new LineData(lineDataSet);
        lineData.setValueTextSize(10f);
        lineData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int i,
                                            ViewPortHandler viewPortHandler) {
                return StringUtils.double2String(value, 2);
            }
        });

        return lineData;
    }

    /**
     * 生成柱图数据
     *
     * @param barValues
     * @return
     */
    private static BarData generateBarData(List<Float> barValues, String barTitle) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0, n = barValues.size(); i < n; ++i) {
            barEntries.add(new BarEntry(i, barValues.get(i)));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, barTitle);
        barDataSet.setColor(Color.rgb(159, 143, 186));
        barDataSet.setValueTextColor(Color.rgb(159, 143, 186));
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(10f);
        barData.setBarWidth(0.9f);
        barData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int i,
                                            ViewPortHandler viewPortHandler) {
                return StringUtils.double2String(value, 2);
            }
        });

        return barData;
    }

}
