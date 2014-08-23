package se.gozacke.data;

public class StorageException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public StorageException(String errMsg) {
		super(errMsg);
	}
}