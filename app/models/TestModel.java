package models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import play.Logger;
import play.data.validation.Constraints.Required;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import controllers.MorphiaObject;

@Entity
public class TestModel {

  @Id
  public ObjectId id;
  
  @Required
  public String name;
  
  @Required
  public int value;
  
  public static List<TestModel> all() {
    if (MorphiaObject.datastore != null) {
      return MorphiaObject.datastore.find(TestModel.class).asList();
    }
    return new ArrayList<TestModel>();
  }
  
  public static void create(TestModel testModel) {
    MorphiaObject.datastore.save(testModel);
  }
  
  public static void delete(String id) {
    ObjectId target = new ObjectId(id);
    TestModel toDelete = 
        MorphiaObject.datastore.find(TestModel.class).field("_id").equal(target).get();
    if (toDelete != null) {
      Logger.info("toDelete:" + toDelete);
      MorphiaObject.datastore.delete(toDelete);
    } else {
      Logger.debug("ID No Found:" + id);
    }
  }
  
  @Override
  public String toString() {
    return id + ":" + name;
  }
}
