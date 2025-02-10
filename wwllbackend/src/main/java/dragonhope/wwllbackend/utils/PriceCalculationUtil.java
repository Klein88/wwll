package dragonhope.wwllbackend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculationUtil {
    
    public static final int CALCULATION_SCALE = 8;
    public static final int DISPLAY_SCALE = 4;
    
    public static BigDecimal calculate(BigDecimal value) {
        return value.setScale(CALCULATION_SCALE, RoundingMode.DOWN);
    }
    
    public static BigDecimal display(BigDecimal value) {
        return value.setScale(DISPLAY_SCALE, RoundingMode.DOWN);
    }
} 