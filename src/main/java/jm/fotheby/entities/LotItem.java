package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotItem implements Serializable
{

    @Id @GeneratedValue
    Long id;
    private String name;
    private Date signingDate;


}
