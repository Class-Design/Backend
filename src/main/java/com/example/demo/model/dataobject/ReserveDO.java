package com.example.demo.model.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fireinsect
 * @create 2023/11/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDO {
    String bookId;
    String userId;

}
