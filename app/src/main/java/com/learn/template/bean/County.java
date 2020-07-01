package com.learn.template.bean;

import com.learn.picker.dataset.OptionDataSet;
import java.util.List;

public class County implements OptionDataSet {
  public int id;
  public String name;

  @Override
  public CharSequence getCharSequence() {
    return name;
  }

  @Override
  public List<OptionDataSet> getSubs() {
    return null;
  }

  @Override
  public String getValue() {
    return String.valueOf(id);
  }
}
