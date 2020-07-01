package com.learn.picker.dialog;

public interface OnPickerChooseListener {

  /**
   * @return 是否回调选中关闭dialog
   */
  boolean onConfirm();

  void onCancel();
}