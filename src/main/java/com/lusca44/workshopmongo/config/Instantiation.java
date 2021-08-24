package com.lusca44.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lusca44.workshopmongo.domain.Post;
import com.lusca44.workshopmongo.domain.User;
import com.lusca44.workshopmongo.dto.AuthorDTO;
import com.lusca44.workshopmongo.dto.CommentDTO;
import com.lusca44.workshopmongo.repository.PostRepository;
import com.lusca44.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Patiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComment().addAll(Arrays.asList(c1,c2));
		post2.getComment().addAll(Arrays.asList(c3));
		
		postRepo.saveAll(Arrays.asList(post1,post2));
		
		maria.getPost().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
	}

}
