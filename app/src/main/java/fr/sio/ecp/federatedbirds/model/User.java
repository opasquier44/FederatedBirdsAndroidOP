package fr.sio.ecp.federatedbirds.model;

/**
 * A model class to represent a single message.
 * This entity is inflated by Gson from the API responses.
 */
public class User {

    public long id;
    public String login;
    public String avatar;
    public String coverPicture;
    public String email;

}
