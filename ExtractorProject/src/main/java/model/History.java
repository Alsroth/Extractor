package model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class History {

    private History() {}
    public static List<String> createdOutPutFiles = new ArrayList<>();

}
