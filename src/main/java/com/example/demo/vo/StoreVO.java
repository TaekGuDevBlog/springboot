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
public class StoreVO {
    private int storeSeq;
    private String storeName;
    private String storeLocation;
    private String branchName;
    public String toString(StoreVO storeVO) {
        return String.format("\nSTORE INFO \n" +
                        "storeName : %s\n" +
                        "storeLocation : %s\n" +
                        "storeBranch : %s",
                storeVO.getStoreName(), storeVO.getStoreLocation(), storeVO.getBranchName());
    }
}
