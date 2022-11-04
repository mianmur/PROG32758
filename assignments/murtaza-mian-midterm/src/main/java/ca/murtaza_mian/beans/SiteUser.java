package ca.murtaza_mian.beans;

import lombok.Data;

@Data
public class SiteUser {

    private Long id;
    private String name;
    private String password;
    private String phone;
}
