package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private EventRepository eventRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
		
		eventRepository.deleteAll();
		
		Event event = new Event();
		GeoJsonPoint location = new GeoJsonPoint(new Point(-93.6072134, 41.5985625));
		event.setLocation(location);
		event.setTitle("The lion, the witch, and the wardrobe");
		event.setDetails("This is the lion witch thing");
		ArrayList<String> emails = new ArrayList<String>();
		emails.add("test@testmail.com");
		event.setEmails(emails);
		ArrayList<String> links = new ArrayList<String>();
		links.add("http://wwww.testlink.com/");
		event.setWebsites(links);
		event.setLocationName("Downtown Des Moines");
		ArrayList<String> phones = new ArrayList<String>();
		phones.add("515-555-5309");
		event.setPhones(phones);
		
		eventRepository.save(event);
		System.out.println("Events found with foundAll()");
		for(Event ev : eventRepository.findAll()){
			System.out.println(ev.getTitle());
			
		}
		System.out.println("Finding all in polygon");
		
		Point nw1 = new Point(-93.6126551, 41.6019953);
		Point ne1 = new Point(-93.6063204, 41.6028277);
		Point sw1 = new Point(-93.6163362, 41.5972054);
		Point se1 = new Point(-93.6038758, 41.5971502);
		
//		Polygon polygon = new Polygon(nw1, ne1, sw1, se1);
//		List<Event> events = eventRepository.findInPolygon(polygon);
//		for(Event ev : events){
//			System.out.println(ev.getTitle());
//		}
		

	}
	
	
//	@Configuration
//	class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//	  @Autowired
//	  AccountRepository accountRepository;
//
//	  @Override
//	  public void init(AuthenticationManagerBuilder auth) throws Exception {
//	    auth.userDetailsService(userDetailsService());
//	  }
//
//	  @Beane
//	  UserDetailsService userDetailsService() {
//	    return new UserDetailsService() {
//
//	      @Override
//	      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	        Account account = accountRepository.findByUsername(username);
//	        if(account != null) {
//	        return new User(account.getUsername(), account.getPassword(), true, true, true, true,
//	                AuthorityUtils.createAuthorityList("USER"));
//	        } else {
//	          throw new UsernameNotFoundException("could not find the user '"
//	                  + username + "'");
//	        }
//	      }
//	      
//	    };
//	  }
//	}
//
//	@EnableWebSecurity
//	@Configuration
//	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	 
//	  @Override
//	  protected void configure(HttpSecurity http) throws Exception {
//	    http.authorizeRequests().anyRequest().fullyAuthenticated().and().
//	    httpBasic().and().
//	    csrf().disable();
//	  }
//	  
//	}
	

}