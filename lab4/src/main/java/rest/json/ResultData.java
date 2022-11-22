package rest.json;

import lombok.Data;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import utils.validation.InArray;

@Data
public class ResultData {
    @InArray(array = {-2.0, -1.5, -1.0, -0.5, 0, 0.5, 1.0, 1.5, 2.0}, message = "X_VALUE_NOT_ALLOWED")
    @NotNull(message = "X_MUST_NOT_BE_NULL_OR_EMPTY")
    private Double x;
    @DecimalMax(value = "5", inclusive = false, message = "Y_VALUE_NOT_ALLOWED")
    @DecimalMin(value = "3", inclusive = false, message = "Y_VALUE_NOT_ALLOWED")
    @NotNull(message = "Y_MUST_NOT_BE_NULL_OR_EMPTY")
    private Double y;
    @InArray(array = {-2.0, -1.5, -1.0, -0.5, 0, 0.5, 1.0, 1.5, 2.0}, message = "R_VALUE_NOT_ALLOWED")
    @NotNull(message = "R_MUST_NOT_BE_NULL_OR_EMPTY")
    private Double r;
}
