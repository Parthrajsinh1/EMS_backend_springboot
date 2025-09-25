package com.app.entities;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employees")
@NoArgsConstructor
@Getter
@Setter
public class Employee extends BaseEntity{
	@Column(length=20)
	@NotBlank(message="First name is required")
	private String name;
	@Column(length=20,unique=true)
	@NotBlank(message="Last name is required")
	@Min(value=4,message="Last name min char 4")
	@Max(value=20,message="Last name min char 20")
	private String lastName;
	@Column(length=20)
	@NotBlank(message="Email is required")
	@Email(message="invalid email format")
	private String email;
	@Column(length=20)
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message="Blank or Invalid password")
	private String password;
	@Column(length=20)
	@NotBlank(message="location empty !!")
	private String location;
	@Column(length=20,name="dept_name")
	@JsonProperty(value = "department")
	private String dept;
	@NotNull(message="salary must be supplied")
	@Range(min=10000,max=500000,message="Salary should be in range")
	private double salary;
	@Future(message="join date must be in future")
	private LocalDate joinDate;
}
	
