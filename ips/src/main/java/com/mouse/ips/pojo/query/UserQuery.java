package com.mouse.ips.pojo.query;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 2;
    private String name;
}
