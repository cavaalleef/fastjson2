package com.alibaba.fastjson2.jsonpath;

import com.alibaba.fastjson2.JSONPath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONPath_enum {
    @Test
    public void test_name() {
        Model model = new Model();
        model.size = Size.Small;

        assertEquals(Size.Small.name(), JSONPath.of("$.size.name").eval(model));
    }

    @Test
    public void test_orginal() {
        Model model = new Model();
        model.size = Size.Small;

        assertEquals(Size.Small.ordinal(), JSONPath.of("$.size.ordinal").eval(model));
    }

    public static class Model {
        public Size size;
    }

    public enum Size {
        Big, Median, Small
    }
}
