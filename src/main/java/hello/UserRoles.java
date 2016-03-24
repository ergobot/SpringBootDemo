package hello;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class UserRoles {

    @Id
    private String id;
    private String sub;
    private String role;
    
    public UserRoles() {}
    
    

}