package com.spring.entity;

import lombok.Data;

@Data
public abstract class Entity<ID> {

	private ID id;

	protected Entity() {
	}

	protected Entity(ID id) {
		this.id = id;
	}
}
