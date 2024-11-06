package link.lynux.validator.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author lin.lin
 * @Description FixValueValidator
 * @Date 2024/5/30
 */
public class FixValueValidator extends BaseValidator {
    @Override
    public boolean validate(Object value, String express) {
        if (ObjUtil.isEmpty(value) || StrUtil.isEmpty(express)) {
            return true;
        }
        try {
            String[] range = express.split("[|]", -1);
            if (value instanceof String str) {
                for (String s : range) {
                    if (str.equals(s)) {
                        return true;
                    }
                }
            } else if (value instanceof Number num) {
                for (String s : range) {
                    if (num.doubleValue() == Double.parseDouble(s)) {
                        return true;
                    }
                }
            }
        } catch (Exception ignore) {
        }
        this.setErrMsg("[" + value + "]不是固定值{" + express + "}");
        return false;
    }
}
