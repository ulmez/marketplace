package se.gozacke.actor;

import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.product.ProductRepository;

public interface ActorRepository {
	public List<Actor> getAllActors() throws StorageException;
	public List<Actor> getActorOnActorId(int actorId) throws StorageException;
	public List<Actor> getActorOnFirstNameAndSurName(String firstName, String surName) throws StorageException;
	public List<Integer> getActorsFilmIds(Actor actor) throws StorageException;
	public List<Integer> getActorsOnProductId(int productId) throws StorageException;
	public boolean getIfFilmIdStillExistOnAnyActorId(int filmId) throws StorageException;
	public void insertActor(Actor actor) throws StorageException;
	public void updateActor(Actor actor) throws StorageException;
	public void deleteActor(Actor actor, ActorRepository ar, ProductRepository pr) throws StorageException;
}