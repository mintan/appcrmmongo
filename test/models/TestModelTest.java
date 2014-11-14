package models;

import static org.fest.assertions.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.code.morphia.Morphia;
import com.mongodb.BasicDBObject;

import controllers.MorphiaObject;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
//import play.modules.morphia.MorphiaPlugin;

public class TestModelTest extends AbstractMongoDbTest {

  MongodProcess mongod;
  static int PORT = 12345;
  
  @Before
  public void beforeTest() {
    MorphiaObject.mongo = getMongoClient();
    MorphiaObject.datastore = new Morphia().createDatastore(getMongoClient(), "test");
  }
  
  @Test
  public void 初期値は空リスト() {
    assertThat(TestModel.all()).isEmpty();
  }
  
  
  @Test
  public void addしたものが順番に取り出せる() {
    TestModel ts0 = new TestModel();
    ts0.name = "ほとココア";
    ts0.value = 15;
    TestModel.create(ts0);
    
    TestModel ts1 = new TestModel();
    ts1.name = "かふうチノ";
    ts1.value = 13;
    TestModel.create(ts1);
    
    TestModel ts2 = new TestModel();
    ts2.name = "てでざリゼ";
    ts2.value = 16;
    TestModel.create(ts2);
    
    TestModel ts3 = new TestModel();
    ts3.name = "うじまつチヤ";
    ts3.value = 15;
    TestModel.create(ts3);
    
    TestModel ts4 = new TestModel();
    ts4.name = "きりまシャロ";
    ts4.value = 15;
    TestModel.create(ts4);
    
    assertThat(TestModel.all()).hasSize(5);
    assertThat(TestModel.all().get(0).name).isEqualTo("ほとココア");
    assertThat(TestModel.all().get(1).name).isEqualTo("かふうチノ");
    assertThat(TestModel.all().get(2).name).isEqualTo("てでざリゼ");
    assertThat(TestModel.all().get(3).name).isEqualTo("うじまつチヤ");
    assertThat(TestModel.all().get(4).name).isEqualTo("きりまシャロ");
  }

}
