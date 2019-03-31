package com.epam.peoplebuilder.main;

public class People {
	private String id;
	private String surname;
	private String name;
	private int phone;

	public People(String id, String surname, String name, int phone) {
		super();
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return id + ", " + surname + ", " + name + ", " + phone + ", ";
	}
}
