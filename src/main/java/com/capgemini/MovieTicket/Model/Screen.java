package com.capgemini.MovieTicket.Model;

import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
 * @author Thejesh
 * @category ScreenPojo
 *
 */

@Data
@NoArgsConstructor
@Entity
@Table(name="screen")
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int screenId;

	@JsonIgnore
	@ManyToOne
	private Theatre theatre;
	/*
	@OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
	private List<Show> show;*/
	private String screenName;
	@Column(name = "rows")
	private int rows;
	@Column(name = "columns")
	private int columns;

}