package com.neo.dbf;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBField {
    private String name;
    private char type;
    private int length;
    private int decimalCount;
    private int offset;

    /**
     * 
     * 构造函数 
     * @param name
     * @param type
     * @param length 总位数
     * @param decimalCount 小数位数
     * @param offset
     * @throws JDBFException
     */
    public JDBField(String name, char type, int length, int decimalCount, int offset) throws JDBFException {
        if (name.length() > 10) {
            throw new JDBFException("The field name is more than 10 characters long: " + name);
        }

        if ((type != 'C') && (type != 'N') && (type != 'L') && (type != 'D') && (type != 'F')) {
            throw new JDBFException("The field type is not a valid. Got: " + type);
        }
        if (length < 1) {
            throw new JDBFException("The field length should be a positive integer. Got: " + length);
        }

        if ((type == 'C') && (length >= 255)) {
            throw new JDBFException("The field length should be less than 255 characters for character fields. Got: "
                    + length);
        }

        if ((type == 'N') && (length >= 21)) {
            throw new JDBFException("The field length should be less than 21 digits for numeric fields. Got: " + length);
        }

        if ((type == 'L') && (length != 1)) {
            throw new JDBFException("The field length should be 1 characater for logical fields. Got: " + length);
        }

        if ((type == 'D') && (length != 8)) {
            throw new JDBFException("The field length should be 8 characaters for date fields. Got: " + length);
        }

        if ((type == 'F') && (length >= 21)) {
            throw new JDBFException("The field length should be less than 21 digits for floating point fields. Got: "
                    + length);
        }

        if (decimalCount < 0) {
            throw new JDBFException("The field decimal count should not be a negative integer. Got: " + decimalCount);
        }

        if (((type == 'C') || (type == 'L') || (type == 'D')) && (decimalCount != 0)) {
            throw new JDBFException(
                    "The field decimal count should be 0 for character, logical, and date fields. Got: " + decimalCount);
        }

        if (decimalCount > length - 1) {
            throw new JDBFException("The field decimal count should be less than the length - 1. Got: " + decimalCount);
        }

        this.name = name;
        this.type = type;

        this.decimalCount = decimalCount;
//        if(decimalCount>0){
//            this.length = integerCount+decimalCount+1;//多加点的位数。
//        }else{
            this.length = length;
//        }
        this.offset = offset;
    }

    public String getName() {
        return this.name;
    }

    public char getType() {
        return this.type;
    }

    public int getLength() {
        return this.length;
    }

    public int getDecimalCount() {
        return this.decimalCount;
    }

    public int getOffset() {
        return offset;
    }

    public String format(Object obj) throws JDBFException {
        if ((this.type == 'N') || (this.type == 'F')) {
            if (obj == null) {
                obj = new Double(0D);
            }
            if ((obj instanceof Number)) {
                Number number = (Number) obj;
                StringBuffer stringbuffer = new StringBuffer(getLength());
                for (int i = 0; i < getLength(); i++) {
                    stringbuffer.append("#");
                }
                if (getDecimalCount() > 0) {
                    stringbuffer.setCharAt(getLength() - getDecimalCount() - 1, '.');//用小数点替换
                    
                    stringbuffer.setCharAt(getLength() - getDecimalCount() - 2 , '0');
                }
                // 修改格式化位数
                for (int i = getLength() - getDecimalCount(); i < getLength(); i++) {
                    stringbuffer.setCharAt(i, '0');
                }
                
                
                DecimalFormat decimalformat = new DecimalFormat(stringbuffer.toString());

                String s1 = decimalformat.format(number);
                int k = getLength() - s1.length();
                if (k < 0) {
                    throw new JDBFException("Value " + number + " cannot fit in pattern: '" + stringbuffer + "'.");
                }

                StringBuffer stringbuffer2 = new StringBuffer(k);
                for (int l = 0; l < k; l++) {
                    stringbuffer2.append(" ");
                }

                return stringbuffer2 + s1;
            }

            throw new JDBFException("Expected a Number, got " + obj.getClass() + ".");
        }

        if (this.type == 'C') {
            if (obj == null) {
                obj = "";
            }
            if ((obj instanceof String)) {
                String s = (String) obj;
                if (s.length() > getLength()) {
                    throw new JDBFException("'" + obj + "' is longer than " + getLength() + " characters.");
                }

                StringBuffer stringbuffer1 = new StringBuffer(getLength() - s.length());
                for (int j = 0; j < getLength() - s.length(); j++) {
                    stringbuffer1.append(' ');
                }

                return s + stringbuffer1;
            }

            throw new JDBFException("Expected a String, got " + obj.getClass() + ".");
        }

        if (this.type == 'L') {
            if (obj == null) {
                obj = new Boolean(false);
            }
            if ((obj instanceof Boolean)) {
                Boolean boolean1 = (Boolean) obj;
                return boolean1.booleanValue() ? "Y" : "N";
            }

            throw new JDBFException("Expected a Boolean, got " + obj.getClass() + ".");
        }

        if (this.type == 'D') {
            if (obj == null) {
                obj = new Date();
            }
            if ((obj instanceof Date)) {
                Date date = (Date) obj;
                SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
                return simpledateformat.format(date);
            }

            throw new JDBFException("Expected a Date, got " + obj.getClass() + ".");
        }

        throw new JDBFException("Unrecognized JDBFField type: " + this.type);
    }

    public Object parse(String s) throws JDBFException {
        s = s.trim();
        if ((this.type == 'N') || (this.type == 'F')) {
            if (s.equals(""))
                s = "0";
            try {
                if (getDecimalCount() == 0) {
                    return new Long(s);
                }

                return new Double(s);
            } catch (NumberFormatException numberformatexception) {
                throw new JDBFException(numberformatexception);
            }
        }
        if (this.type == 'C') {
            return s;
        }
        if (this.type == 'L') {
            if ((s.equals("Y")) || (s.equals("y")) || (s.equals("T")) || (s.equals("t"))) {
                return new Boolean(true);
            }
            if ((s.equals("N")) || (s.equals("n")) || (s.equals("F")) || (s.equals("f"))) {
                return new Boolean(false);
            }

            throw new JDBFException("Unrecognized value for logical field: " + s);
        }

        if (this.type == 'D') {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
            try {
                if ("".equals(s)) {
                    return null;
                }

                return simpledateformat.parse(s);
            } catch (ParseException parseexception) {
                throw new JDBFException(parseexception);
            }
        }

        throw new JDBFException("Unrecognized JDBFField type: " + this.type);
    }

    public String toString() {
        return this.name;
    }
}