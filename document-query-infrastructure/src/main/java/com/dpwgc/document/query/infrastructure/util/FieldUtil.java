package com.dpwgc.document.query.infrastructure.util;

import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class FieldUtil {

    //下划线转驼峰
    public static String a_bc2aBc(String field) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field);
    }

    //驼峰转下划线
    public static String aBc2a_bc(String field) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
    }

    //Object（驼峰字段）转Map（下划线key）
    public static Map<String, Object> object2Map(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String keyName = aBc2a_bc(field.getName()); //key驼峰转下划线
            Object value = field.get(obj);
            if (value == null)
                value = "";
            map.put(keyName, value);
        }
        return map;
    }

    //Map（下划线key）转Object（驼峰字段）
    public static Object map2Object(Map<Object, Object> map, Class<?> cla) throws Exception {
        if (map == null)
            return null;
        Object obj = cla.newInstance();
        cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            //将对象字段名转为下划线格式，再与map key比较
            if (map.containsKey(aBc2a_bc(field.getName()))) {
                field.set(obj, map.get(aBc2a_bc(field.getName())));
            }
        }
        return obj;
    }

}
