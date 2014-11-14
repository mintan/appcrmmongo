package controllers;

import models.TestModel;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

  static Form<TestModel> testModelForm = Form.form(TestModel.class);
  
  public static Result index() {
    return ok(index.render("Your new application is ready! crmapp", TestModel.all(), testModelForm));
  }
  
  public static Result newTestModel() {
    Form<TestModel> filledForm = testModelForm.bindFromRequest();
    if (filledForm.hasErrors()) {
      return badRequest(index.render("error", TestModel.all(), filledForm));
    }
    // Save
    TestModel.create(filledForm.get());
    return redirect(routes.Application.index());
  }
  
  public static Result deleteTestModel(String id) {
    TestModel.delete(id);
    return redirect(routes.Application.index());
  }
}
