package com.example.app2.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class Search {
    String keyword;
    String type;

    public List<String> getTypes(){
        return new ArrayList<>(Arrays.asList(type.split("")));
    }
}
