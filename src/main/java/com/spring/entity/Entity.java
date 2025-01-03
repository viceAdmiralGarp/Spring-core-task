package com.spring.entity;

import lombok.Data;

@Data
public abstract class Entity<ID> {

	private ID id;

	public Entity() {
	}

	public Entity(ID id) {
		this.id = id;
	}


}
