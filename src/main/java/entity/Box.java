package entity;

import javax.persistence.*;

@Entity
@Table(name = "box")
public class Box {

    @Id
    private String id;

    @Column
    private String valability;

    public Box(){}

    public Box(String val) {
        this.valability = val;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getValability() {
        return valability;
    }

    public void setValability(String valability) {
        this.valability = valability;
    }
}
