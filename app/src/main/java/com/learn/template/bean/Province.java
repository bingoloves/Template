package com.learn.template.bean;

import com.learn.picker.dataset.OptionDataSet;

import java.util.List;

public class Province implements OptionDataSet {
  public int id;
  public String name;
  public List<City> citys;

  @Override
  public CharSequence getCharSequence() {
    return name;
  }

  @Override
  public List<City> getSubs() {
    return citys;
  }

  @Override
  public String getValue() {
    return String.valueOf(id);
  }
}
