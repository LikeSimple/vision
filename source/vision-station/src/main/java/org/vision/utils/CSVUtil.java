package org.vision.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Vector;

public class CSVUtil {

    public static String fieldToHeader(Class c) {
        Vector<String> filedIgnore = new Vector<>();
        StringBuilder buffer = new StringBuilder();

        Annotation annotation = c.getAnnotation(FieldIgnore.class);
        if (null != annotation) {
            filedIgnore = new Vector<>(Arrays.asList(((FieldIgnore) annotation).value()));
        }

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            if (!filedIgnore.contains(field.getName())
                    && ((field.getModifiers() & java.lang.reflect.Modifier.STATIC) != java.lang.reflect.Modifier.STATIC)) {
                buffer.append(",").append(field.getName());
            }
        }
        return buffer.substring(1);
    }
}
