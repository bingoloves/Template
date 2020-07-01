package com.learn.picker.picker.option;

import com.learn.picker.dataset.OptionDataSet;
import com.learn.picker.picker.OptionPicker;

import java.util.List;

/**
 * description：
 */

public interface IOptionDelegate {
  //void init(int hierarchy, List<PickerView> pickerViews, int[] selectedPosition);
  void init(OptionPicker.Delegate delegate);

  void setData(List<? extends OptionDataSet>... options);

  void setSelectedWithValues(String... values);

  OptionDataSet[] getSelectedOptions();

  void reset();
}
