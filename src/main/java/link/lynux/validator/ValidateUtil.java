package link.lynux.validator;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import link.lynux.validator.impl.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lin.lin
 * @Description ValidateUtil
 * @Date 2024/5/30
 */
public class ValidateUtil {
    public static void validate(Object obj) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                String key = StrUtil.upperFirst(field.getName());
                Object value = obj.getClass().getMethod("get" + key).invoke(obj);
                ValidateNotes notes = field.getAnnotation(ValidateNotes.class);
                if (ObjUtil.isNotNull(notes)) {
                    // 双注解
                    for (ValidateNote annotation : notes.value()) {
                        singleValidate(annotation, key, value);
                    }
                }
                ValidateNote note = field.getAnnotation(ValidateNote.class);
                if (ObjUtil.isNotNull(note)) {
                    // 单注解
                    singleValidate(note, key, value);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("对象字段校验异常");
        }
    }

    private static void singleValidate(ValidateNote annotation, String key, Object value) {
        if (value instanceof String) {
            // 字符串校验
            if (annotation.isMandatory() && ObjUtil.isEmpty(value)) {
                throw new IllegalArgumentException("必填参数不允许为空: [" + key + "]");
            }
            if (ObjUtil.isNotEmpty(value)) {
                validate(key, value, annotation.express(), annotation.rule());
            }
        } else if (value instanceof Number) {
            // 数字校验
            if (annotation.isMandatory() && ObjUtil.isNull(value)) {
                throw new IllegalArgumentException("必填参数不允许为空: [" + key + "]");
            }
            if (ObjUtil.isNotNull(value)) {
                validate(key, value, annotation.express(), annotation.rule());
            }
        } else if (value instanceof Enum) {
            // 枚举校验
            if (annotation.isMandatory() && ObjUtil.isNull(value)) {
                throw new IllegalArgumentException("必填参数不允许为空: [" + key + "]");
            }
        } else if (value instanceof JSONObject) {
            // json校验
            if (annotation.isMandatory() && ObjUtil.isNull(value)) {
                throw new IllegalArgumentException("必填参数不允许为空: [" + key + "]");
            }
        } else {
            // 对象校验
            if (annotation.isMandatory() && ObjUtil.isNull(value)) {
                throw new IllegalArgumentException("必填参数不允许为空: [" + key + "]");
            }
            if (ObjUtil.isNotNull(value)) {
                validate(value);
            }
        }
    }

    private static void validate(String key, Object value, String express, ValidateRule rule) {
        BaseValidator validator = null;
        if (ValidateRule.REGEX.equals(rule)) {
            validator = new RegexValidator();
        } else if (ValidateRule.FIX_VALUE.equals(rule)) {
            validator = new FixValueValidator();
        } else if (ValidateRule.NUMBER_RANGE.equals(rule)) {
            validator = new NumberRangeValidator();
        } else if (ValidateRule.URL.equals(rule)) {
            validator = new UrlValidator();
        } else if (ValidateRule.MOBILE.equals(rule)) {
            validator = new MobileValidator();
        } else if (ValidateRule.UUID.equals(rule)) {
            validator = new UUIDValidator();
        }

        if (ObjUtil.isNotNull(validator) && !validator.validate(value, express)) {
            throw new IllegalArgumentException("参数校验失败: [" + key + "], " + validator.getErrMsg());
        }
    }
}
