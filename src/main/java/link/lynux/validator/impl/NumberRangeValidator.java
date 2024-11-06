package link.lynux.validator.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

/**
 * @author lin.lin
 * @Description NumberRangeValidator
 * @Date 2024/5/30
 */
public class NumberRangeValidator extends BaseValidator {
    @Override
    public boolean validate(Object value, String express) {
        try {
            if (StrUtil.isEmpty(express)) {
                return true;
            }
            String[] range = express.split("[|]", -1);
            double min = Double.parseDouble(range[0]);
            double max = Double.parseDouble(range[1]);
            double number = Double.parseDouble(value.toString());
            if (Validator.isBetween(number, min, max)) {
                return true;
            }
        } catch (Exception ignore) {
        }
        this.setErrMsg("[" + value + "]不在范围内{" + express + "}");
        return false;
    }
}
