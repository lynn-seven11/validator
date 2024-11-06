package link.lynux.validator.impl;

import cn.hutool.core.util.StrUtil;

/**
 * @author lin.lin
 * @Description RegexValidator
 * @Date 2024/5/30
 */
public class RegexValidator extends BaseValidator {
    @Override
    public boolean validate(Object value, String express) {
        try {
            String str = (String) value;
            if (StrUtil.isEmpty(str)) {
                return true;
            }
            if (str.matches(express)) {
                return true;
            }
        } catch (Exception ignore) {
        }
        this.setErrMsg("[" + value + "]不是指定的格式{" + express + "}");
        return false;
    }
}
