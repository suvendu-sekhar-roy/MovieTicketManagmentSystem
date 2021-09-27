package com.capgemini.MovieTicket.Model;
import javax.persistence.*;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;
	private String customerName;
	private String address;
	private String mobileNumber;
	private String email;
	private String password;
//	@JsonIgnore
//	@OneToOne
//	private User user;

}
