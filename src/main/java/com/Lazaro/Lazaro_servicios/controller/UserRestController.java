package com.Lazaro.Lazaro_servicios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Lazaro.Lazaro_servicios.entity.User;
import com.Lazaro.Lazaro_servicios.entity.UserRepository;
import com.Lazaro.Lazaro_servicios.service.UserService;



//Indiciamos que es un controlador rest
@RestController
@ResponseBody@RequestMapping("/api") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/api/

public class UserRestController {
	
	@Autowired
    UserRepository UserRepository;

	//Inyectamos el servicio para poder hacer uso de el
	@Autowired
	private UserService userService;

	/*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url 
	http://127.0.0.1:8080/api/users*/
	@ResponseBody
	@GetMapping("/users")
	public List<User> findAll(){
		//retornará todos los usuarios
		return userService.findAll();
	}

	@GetMapping("/user")
    @ResponseBody
    public ModelAndView User() {

        ModelAndView modelAndView=new ModelAndView("user");
        modelAndView.addObject("mensaje", "");
        return modelAndView;
    }

    @PostMapping("/user")
    public ModelAndView UserPost(
		@RequestParam("id") Integer id,
        @RequestParam("email") String email,
		@RequestParam("password") String password){    
        ModelAndView modelAndView=new ModelAndView("user");
		
		
		
		User user = new User(id, email, password);
        
		UserRepository.save(user);

        return modelAndView;
    }
	
	/*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
	http://127.0.0.1:8080/api/users/1*/
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId){
		User user = userService.findById(userId);
		
		if(user == null) {
			throw new RuntimeException("User id not found -"+userId);
		}
		//retornará al usuario con id pasado en la url
		return user;
	}
	
	/*Este método se hará cuando por una petición POST (como indica la anotación) se llame a la url
	http://127.0.0.1:8080/api/users/  */
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		user.setId(0);
		
		//Este metodo guardará al usuario enviado
		userService.save(user);
		
		return user;
		
	}
	/*Este método se hará cuando por una petición PUT (como indica la anotación) se llame a la url
	http://127.0.0.1:8080/api/users/  */
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		
		userService.save(user);
		
		//este metodo actualizará al usuario enviado
		
		return user;
	}
	
	/*Este método se hará cuando por una petición DELETE (como indica la anotación) se llame a la url + id del usuario
	http://127.0.0.1:8080/api/users/1  */
	@DeleteMapping("users/{userId}")
	public String deteteUser(@PathVariable int userId) {
		
		User user = userService.findById(userId);
		
		if(user == null) {
			throw new RuntimeException("User id not found -"+userId);
		}
		
		userService.deleteById(userId);
		
		//Esto método, recibira el id de un usuario por URL y se borrará de la bd.
		return "Deleted user id - "+userId;
	}
	
}