package com.learn.template.bean;

import com.learn.picker.dataset.OptionDataSet;

import java.util.List;

/**
 * description：
 */

public class City implements OptionDataSet {

  public int id;
  public String name;
  public List<County> counties;

  @Override
  public CharSequence getCharSequence() {
    return name;
  }

  @Override
  public List<County> getSubs() {
    return counties;
  }

  @Override
  public String getValue() {
    return String.valueOf(id);
  }
}
