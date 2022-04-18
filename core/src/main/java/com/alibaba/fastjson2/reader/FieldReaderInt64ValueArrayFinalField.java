package com.alibaba.fastjson2.reader;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONReader;

import java.lang.reflect.Field;

final class FieldReaderInt64ValueArrayFinalField<T> extends FieldReaderObjectField<T> {
    FieldReaderInt64ValueArrayFinalField(String fieldName, Class fieldType, int ordinal, Field field) {
        super(fieldName, fieldType, fieldType, ordinal, 0, null, field);
    }

    public boolean isReadOnly() {
        return true;
    }

    public void readFieldValue(JSONReader jsonReader, T object) {
        if (jsonReader.readIfNull()) {
            return;
        }

        long[] array;
        try {
            array = (long[]) field.get(object);
        } catch (Exception e) {
            throw new JSONException("set " + fieldName + " error", e);
        }

        if (jsonReader.nextIfMatch('[')) {
            for (int i = 0; ; ++i) {
                if (jsonReader.nextIfMatch(']')) {
                    break;
                }

                long value = jsonReader.readInt64Value();
                if (array != null && i < array.length) {
                    array[i] = value;
                }
            }
        }
    }
}