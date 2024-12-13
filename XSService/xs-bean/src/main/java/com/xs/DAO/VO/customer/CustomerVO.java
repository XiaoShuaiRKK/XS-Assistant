package com.xs.DAO.VO.customer;

import com.xs.DAO.DO.customer.PointsLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO implements Serializable {
    Integer id;
    @NotEmpty(message = "FirstName could not be empty")
    @Length(min = 1,max = 20,message = "FirstName must be no less than one and more characters")
    String firstName;

    @NotEmpty(message = "LastName could not be empty")
    @Length(min = 1,max = 20,message = "LastName must be no less than one and more characters")
    String lastName;

    @NotEmpty(message = "Email could not be empty")
    @Email(message = "invalid email")
    String email;

    @Pattern(regexp = "^[a-zA-Z0-9_\\-@.]{8,16}$",message = "Password format error," +
            " no less than 8 characters," +
            " no more than 16 characters," +
            " only allowed to contain numbers, letters, underscores, minus signs, and dots")
    String password;
    @Past(message = "Must be a past date")
    Date birth;
    String idNumber;
    Integer areaId;
    Integer stateId;
    Integer level;
    PointsLevel pointsLevel;
    String iconPath;
}
