package br.com.curbani.spring.util.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class SampleConverter extends AbstractBeanField<Object, Object>{
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if(s != null && s.equalsIgnoreCase("yes")) {
        	return true;
        }
        return false;
  }

}
