package com.xs.DAO.DO.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class CustomerDO implements Serializable {
    Integer id;
    @NotEmpty(message = "FirstName could not be empty")
    String firstName;

    @NotEmpty(message = "LastName could not be empty")
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
}
