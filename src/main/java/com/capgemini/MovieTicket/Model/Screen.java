package com.capgemini.MovieTicket.Model;

import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="screen")
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int screenId;

	@JsonIgnore
	//@ManyToOne(targetEntity = Theatre.class, cascade = CascadeType.ALL)
	//@JoinColumn(name = "theatre_fk")
	@ManyToOne
	@JoinColumn(name="theatre_theatre_id")
	private Theatre theatre;

	@JsonIgnore
	@OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
	private List<Show> show;

	private String screenName;
	@Column(name = "rows")
	private int rows;
	@Column(name = "columns")
	private int columns;

}
