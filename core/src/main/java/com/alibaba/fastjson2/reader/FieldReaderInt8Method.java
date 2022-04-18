package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

final class FieldReaderInt8Method<T> extends FieldReaderObjectMethod<T> {
    FieldReaderInt8Method(String fieldName, Type fieldType, Method setter) {
        super(fieldName, fieldType, setter);
    }

    public void readFieldValue(JSONReader jsonReader, T object) {
        Integer fieldValue = jsonReader.readInt32();
        try {
            method.invoke(object, fieldValue == null ? null : fieldValue.byteValue());
        } catch (Exception e) {
            throw new JSONException("set " + fieldName + " error", e);
        }
    }

    public void accept(T object, Object value) {
        try {
            method.invoke(object
                    , TypeUtils.toByte(value));
        } catch (Exception e) {
            throw new JSONException("set " + fieldName + " error", e);
        }
    }

    public Object readFieldValue(JSONReader jsonReader) {
        return jsonReader.readInt32();
    }
}