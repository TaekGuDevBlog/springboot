package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MenuVO {
    private int menuSeq;
    private String storeName;
    private String menuName;
    private int menuPrice;

    public String toString(MenuVO menuVO) {
        return String.format("\nSTORE INFO \n" +
                        "storeName : %s\n" +
                        "menuName : %s\n" +
                        "menuPrice : %d",
                menuVO.getStoreName(), menuVO.getMenuName(), menuVO.getMenuPrice());
    }
}
