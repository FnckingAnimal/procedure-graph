package com.example.graph.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String employeeId;
    private String name;
    private String role;
}
