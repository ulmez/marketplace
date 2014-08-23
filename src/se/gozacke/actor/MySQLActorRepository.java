package se.gozacke.actor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.gozacke.data.StorageException;
import se.gozacke.database.Database;
import se.gozacke.product.ProductRepository;

public class MySQLActorRepository implements ActorRepository {
	private List<Actor> actors;
	
	public MySQLActorRepository() {
		actors = new ArrayList<>();
	}

	@Override
	public List<Actor> getAllActors() throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    actors.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM actors;";
		
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Actor tempActor = new Actor(rs.getInt("id"));
				tempActor.setFirstName(rs.getString("firstname"));
				tempActor.setSurName(rs.getString("surname"));
				tempActor.setDob(rs.getString("dob"));
				
				actors.add(tempActor);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return actors;
	}

	@Override
	public List<Actor> getActorOnActorId(int actorId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    actors.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM actors WHERE actors.id = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, actorId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Actor tempActor = new Actor(rs.getInt("id"));
				tempActor.setFirstName(rs.getString("firstname"));
				tempActor.setSurName(rs.getString("surname"));
				tempActor.setDob(rs.getString("dob"));
				
				actors.add(tempActor);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return actors;
	}

	@Override
	public List<Actor> getActorOnFirstNameAndSurName(String firstName, String surName) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    actors.clear();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT * FROM actors WHERE actors.firstname = ? AND actors.surname = ?;";
		
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, firstName);
			pstmt.setString(2, surName);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				Actor tempActor = new Actor(rs.getInt("id"));
				tempActor.setFirstName(rs.getString("firstname"));
				tempActor.setSurName(rs.getString("surname"));
				tempActor.setDob(rs.getString("dob"));
				
				actors.add(tempActor);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return actors;
	}

	@Override
	public void insertActor(Actor actor) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO actors VALUES (null, ?, ?, ?);";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, actor.getFirstName());
			pstmt.setString(2, actor.getSurName());
			pstmt.setString(3, actor.getDob());
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public void updateActor(Actor actor) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			conn = Database.getConnection();
			
			conn.setAutoCommit(false);
			
			String query = "UPDATE actors "
						 + "SET actors.firstname = ?, "
						 + "actors.surname = ?, "
						 + "actors.dob = ? "
						 + "WHERE actors.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, actor.getFirstName());
			pstmt.setString(2, actor.getSurName());
			pstmt.setString(3, actor.getDob());
			pstmt.setInt(4, actor.getActorId());
			
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public List<Integer> getActorsFilmIds(Actor actor) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    List<Integer> actorsFilmIds = new ArrayList<>();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT films_actors.films_id "
						 + "FROM films_actors, actors "
						 + "WHERE actors.id = films_actors.actors_id "
						 + "AND actors.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, actor.getActorId());
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				int filmId = rs.getInt("films_id");
				
				actorsFilmIds.add(filmId);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return actorsFilmIds;
	}

	@Override
	public boolean getIfFilmIdStillExistOnAnyActorId(int filmId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    boolean exists = true;
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT count(films_actors.films_id) as film_id_exists "
						 + "FROM films_actors "
						 + "WHERE films_actors.films_id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, filmId);
			
			rs = pstmt.executeQuery();
			
			int countFilmId = 0;
			
			// Display all the data in the table.
			while (rs.next()) {
				countFilmId = rs.getInt("film_id_exists");
	        }
			
			if(countFilmId == 1) {
				exists = false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return exists;
	}

	@Override
	public void deleteActor(Actor actor, ActorRepository ar, ProductRepository pr) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    List<Integer> filmIdsToCheckForDeletion = ar.getActorsFilmIds(actor);
	    
	    try {
			conn = Database.getConnection();
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM films_actors WHERE actors_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, actor.getActorId());
			pstmt.executeUpdate();
			
			query = "DELETE FROM actors WHERE actors.id = ?";
			pstmt.close();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, actor.getActorId());
			pstmt.executeUpdate();
			
			for(int i : filmIdsToCheckForDeletion) {
				boolean exists = ar.getIfFilmIdStillExistOnAnyActorId(i);
				
				if(!exists) {
					int productIdToDelete = pr.getProductIdOnFilmId(i);
					
					query = "DELETE FROM products_categories WHERE products_categories.products_id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
					
					query = "DELETE FROM films WHERE films.products_id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
					
					query = "DELETE FROM shoppingbasket WHERE shoppingbasket.products_id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
					
					query = "DELETE FROM products WHERE products.id = ?;";
					pstmt.close();
					pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, productIdToDelete);
					pstmt.executeUpdate();
				}
			}
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new StorageException(e1.getMessage());
			}
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	}

	@Override
	public List<Integer> getActorsOnProductId(int productId) throws StorageException {
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    List<Integer> actorsIds = new ArrayList<>();
	    
	    try {
			conn = Database.getConnection();
			
			String query = "SELECT actors.id as actor_id "
						 + "FROM actors, films_actors, films, products "
						 + "WHERE actors.id = films_actors.actors_id "
						 + "AND films.id = films_actors.films_id "
						 + "AND products.id = films.products_id "
						 + "AND products.id = ?;";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, productId);
			
			rs = pstmt.executeQuery();
			
			// Display all the data in the table.
			while (rs.next()) {
				int actorId = rs.getInt("actor_id");
				
				actorsIds.add(actorId);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new StorageException(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new StorageException(e.getMessage());
			}
		}
	    
	    return actorsIds;
	}
}