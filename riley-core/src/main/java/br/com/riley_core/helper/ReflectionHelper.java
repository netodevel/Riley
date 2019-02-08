package br.com.riley_core.helper;

import java.lang.reflect.Constructor;

public class ReflectionHelper {

    public static Object createNewInstance(Class<?> clazz) throws Exception {
        try{
            Constructor<?> ctor;
            ctor = clazz.getConstructor();
            Object object = ctor.newInstance();
            return object;
        } catch(Exception e) {
            throw new Exception("error:" + e.getMessage());
        }
    }
}
