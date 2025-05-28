package com.backendjavatown.services.dto;

import com.backendjavatown.models.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto extends UsersDto {

    public EmployeeDto(Long id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }
}