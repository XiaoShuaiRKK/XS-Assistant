package com.xs.DAO.DO.customer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
@TableName("customer")
public class CustomerDO implements Serializable {
    @Id
    Integer id;

    @TableField("first_name")
    @NotEmpty(message = "FirstName could not be empty")
    @Length(min = 1,max = 20,message = "FirstName must be no less than one and more characters")
    String firstName;

    @TableField("last_name")
    @NotEmpty(message = "LastName could not be empty")
    @Length(min = 1,max = 20,message = "LastName must be no less than one and more characters")
    String lastName;

    @TableField("email")
    @NotEmpty(message = "Email could not be empty")
    @Email(message = "invalid email")
    String email;

    @TableField("password")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-@.]{8,16}$",message = "Password format error," +
            " no less than 8 characters," +
            " no more than 16 characters," +
            " only allowed to contain numbers, letters, underscores, minus signs, and dots")
    String password;
    @TableField("birth")
    @Past(message = "Must be a past date")
    Date birth;
    @TableField("id_number")
    String idNumber;
    @TableField("area_id")
    Integer areaId;
    @TableField("state_id")
    Integer stateId;
    @TableField("level")
    Integer level;
    @TableField("points_level_id")
    String pointsLevelId;
    @TableField("icon_path")
    String iconPath;
}
