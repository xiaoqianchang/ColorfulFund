package com.zritc.colorfulfund.converter;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.zritc.colorfulfund.utils.ZRDeviceInfo;
import com.zritc.colorfulfund.utils.ZRErrors;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义解析JSON
 * <p>
 * Created by Chang.Xiao on 2016/7/29.
 *
 * @version 1.0
 */
public final class JsonConverterFactory extends Converter.Factory {

    private final String TAG = JsonConverterFactory.class.getSimpleName();

    public static JsonConverterFactory create() {
        return new JsonConverterFactory ();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        // 进行条件判断，如果传进来的Type不是class，则匹配失败
        if (!(type instanceof Class<?>)) {
            return null;
        }
        TypeToken<?> typeToken = TypeToken.get(type);
        Class<?> c = (Class<?>) type;

        return new JsonResponseBodyConverter<>(c);
    }

    final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        private final Class<?> c;

        public JsonResponseBodyConverter(Class<?> c) {
            this.c = c;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            // 自己解析
            String jsonStr = value.string();
            try {
                Method parseJson = c.getMethod("parseJson", String.class);
                Object object = parseJson.invoke(c.newInstance(), new String(jsonStr));
                return (T) object;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // if an exception was thrown by the invoked method
                Log.e(TAG, "json解析异常，" + e.getCause().getMessage());
                // JSON异常或解析异常，构造一个异常对象return交给ResponseCallBack
                try {
                    Object instance = c.newInstance();
                    c.getField("sid").set(instance, ZRDeviceInfo.getSid());
                    c.getField("rid").set(instance, ZRDeviceInfo.getRid());
                    c.getField("code").set(instance, ZRErrors.JSON_EXCEPTION);
                    c.getField("msg").set(instance, jsonStr);
                    return (T) instance;
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        }
    }

}
