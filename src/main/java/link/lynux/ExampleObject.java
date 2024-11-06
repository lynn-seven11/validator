package link.lynux;

import link.lynux.validator.ValidateNote;
import link.lynux.validator.ValidateRule;
import lombok.Builder;
import lombok.Data;

/**
 * @author lin.lin
 * @Description link.lynux.ExampleObject
 * @Date 2024/11/6
 */
@Data
@Builder
public class ExampleObject {
    @ValidateNote(isMandatory = true, rule = ValidateRule.MOBILE)
    private String mobile;
    @ValidateNote(isMandatory = true, rule = ValidateRule.UUID)
    private String uuid;
    @ValidateNote(isMandatory = true, rule = ValidateRule.URL)
    private String url;
    @ValidateNote(rule = ValidateRule.FIX_VALUE, express = "abc|123")
    private String fixValue;
    @ValidateNote(rule = ValidateRule.NUMBER_RANGE, express = "0|10")
    private String numberRangeString;
    @ValidateNote(rule = ValidateRule.NUMBER_RANGE, express = "0|10")
    private Double numberRangeDouble;
    // 同时需要满足多个正则的情况
    @ValidateNote(rule = ValidateRule.REGEX, express = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    @ValidateNote(rule = ValidateRule.REGEX, express = ".{1,32}")
    private String regex;
}
