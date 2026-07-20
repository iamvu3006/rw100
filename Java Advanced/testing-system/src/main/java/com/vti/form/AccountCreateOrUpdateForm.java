package com.vti.form;

import com.vti.exception.UsernameExist;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateOrUpdateForm {
    //    @UsernameExist
    @NotBlank(message = "Username phải có giá trị")// null ""  "   ".
    @Length(max = 100, message = "Username ko đc dài quá 100 kí tự")
    private String username;//null   "    "

    @NotBlank(message = "Fullname phải có giá trị")
    @Length(max = 100, message = "Fullname ko đc dài quá 100 kí tự")
    private String fullName;

    @NotBlank(message = "Email phải có giá trị")
    @Pattern(regexp = "^[a-zA-Z0-9_+.-]+@[a-zA-Z0-9.-]+$", message = "Email không đúng định dạng")
    @Length(max = 100, message = "Email ko đc dài quá 100 kí tự")
//    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotNull(message = "Department phải có giá trị")//null
    @PositiveOrZero(message = "DepartmentID phải lớn hơn 0")
    private Integer departmentId;

    @NotNull(message = "Position phải có giá trị")
    @PositiveOrZero(message = "PositionId phải lớn hơn 0")
    private Integer positionId;
}