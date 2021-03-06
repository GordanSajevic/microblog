package controllers;


import play.*;
import play.data.Form;
import play.mvc.*;
import models.*;
import views.html.*;


public class UserController extends Controller
{
	static Form<User> userForm = new Form<User>(User.class);
	
	public static Result create()
	{
		Form<User> filled = userForm.bindFromRequest();
		if(!filled.hasErrors())
		{
			String email = filled.get().email;
			String password = filled.get().password;
			long id = User.createUser(email, password);
			return redirect("/user/"+id);
		}
		return ok(singup.render(userForm));
	}
	
	public static Result newUser()
	{
		return ok(singup.render(userForm));
	}
	
	public static Result show(long id)
	{
		User u = User.find(id);
		return ok(showUser.render(u));
	}
}