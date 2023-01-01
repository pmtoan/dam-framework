import AnnotationORM.*;

import java.util.Date;

/**
 * PACKAGE_NAME
 * Create by pmtoan
 * Date 12/5/2022 - 3:25 PM
 * Description: ...
 */

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @Column(name = "actor_id")
    public int id;

    @Column(name = "first_name", nullable = false)
    public String firstName;

    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Temporal(value = Temporal.TemporalType.DATETIME)
    @Column(name = "last_update")
    public Date lastUpdate;

    public Actor(){
    }

    public Actor(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
