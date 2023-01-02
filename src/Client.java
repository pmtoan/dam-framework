import Expression.IExpression;
import Expression.Leaf.Equal;
import Expression.Leaf.GreaterOrEqual;
import Expression.Leaf.GreaterThan;
import Wrapper.ORMFactory;
import Wrapper.ORMObject;

import java.util.Date;
import java.util.List;


/**
 * PACKAGE_NAME
 * Create by pmtoan
 * Date 12/11/2022 - 7:46 PM
 * Description: ...
 */
public class Client {
    public static void main(String args[]){
        String host = "localhost";
        String port = "3306";
        String databaseName = "sakila";
        String userName = "root";
        String password = "";

        ORMFactory.setDatabaseInstance(ORMFactory.DATABASE_TYPE.MySQL);
        ORMFactory.configDatabase(host, port, databaseName, userName, password);

        ORMObject<Actor> actorORMObject = ORMFactory.createORMObject(Actor.class);

        // Find
//        Actor rsActor = actorORMObject.findOne(new GreaterThan("Actor_id", 100));
//
//        System.out.print(rsActor.id + " | ");
//        System.out.print(rsActor.firstName + " | ");
//        System.out.print(rsActor.lastName + " | ");
//        System.out.print(rsActor.lastUpdate);
//        System.out.println();

//        actor.setLastUpdate(new Date("2019/2/19"));
//
//        Actor actor = new Actor("Chris", "Hemsworth 2");
//        actor.setId(210);
//        actorORMObject.insert(actor);
////
//        Actor updateActor = actorORMObject.findOne(new Equal("actor_id", 278));
//        updateActor.setLastName("Update date last_update");
//        updateActor.setLastUpdate(new Date());
//        actorORMObject.update(updateActor);
////
//        Actor delActor = actorORMObject.findOne(new Equal("actor_id", 280));
//        actorORMObject.delete(delActor);
////
//        actorORMObject.delete(new GreaterOrEqual( "actor_id", 282));
//

        IExpression where = new GreaterOrEqual("actor_id", 50);
        String[] groupBy = new String[] {"first_name"};
        IExpression having = new Equal("last_name", "GARLAND");
        //List<Actor> listActor = actorORMObject.findAll();
        List<Actor> listActor = actorORMObject.find(where, groupBy, null);

        for(Actor a : listActor){
            System.out.print(a.id + " | ");
            System.out.print(a.firstName + " | ");
            System.out.print(a.lastName + " | ");
            System.out.print(a.lastUpdate);
            System.out.println();
        }
    }
}
