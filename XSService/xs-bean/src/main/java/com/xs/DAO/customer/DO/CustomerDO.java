package com.xs.DAO.customer.DO;

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
    String firstName;
    String lastName;
    String email;
    String password;
    Date birth;
    String idNumber;
    Integer areaId;
}
