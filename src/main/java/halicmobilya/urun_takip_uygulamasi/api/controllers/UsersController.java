package halicmobilya.urun_takip_uygulamasi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halicmobilya.urun_takip_uygulamasi.business.abstracts.UserService;
import halicmobilya.urun_takip_uygulamasi.core.entities.concretes.User;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.DataResult;
import halicmobilya.urun_takip_uygulamasi.core.utilities.results.Result;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	
	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}



	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody User user) {
		return this.userService.add(user);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody int id) {
		return this.userService.deleteById(id);
	}
	
	@PostMapping("/find")
	public Result find(@RequestBody int id) {
		return this.userService.findById(id);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody User user) {
		return this.userService.update(user);
	}
	
}
