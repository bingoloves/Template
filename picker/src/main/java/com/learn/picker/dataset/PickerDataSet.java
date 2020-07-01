package com.learn.picker.dataset;

/**
 * 描述：数据实现接口，用户显示文案
 */

public interface PickerDataSet {
  CharSequence getCharSequence();

  /**
   * @return 上传的value，用于匹配初始化选中的下标
   */
  String getValue();
}
