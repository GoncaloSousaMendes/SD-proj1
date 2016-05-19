package sd.tp1;

import java.util.List;

public interface RequestInterface {

	/**
	 * @return the list with the names of albuns
	 */
	List<String> getAlbums();
	
	/**
	 * @param album
	 * @return the list with the names of pictures
	 */
	List<String> getPictures(String album);
	
	/**
	 * @param album
	 * @param picture
	 * @return the byte[] of the picture
	 */
	byte[] getPicture(String album, String picture);
	
	/**
	 * @param album
	 * @return true if the album was created 
	 */
	boolean createAlbum(String album);
	
	/**
	 * @param album
	 * @return true if the album was deleted 
	 */
	boolean deleteAlbum(String album);
	
	/**
	 * @param album
	 * @param picture
	 * @return true if the picture was deleted
	 */
	boolean deletePicture(String album, String picture);
	
	/**
	 * @param album
	 * @param picture
	 * @param data
	 * @return true if the picture was upload 
	 */
	boolean uploadPicture(String album, String picture, byte[] data);

}
