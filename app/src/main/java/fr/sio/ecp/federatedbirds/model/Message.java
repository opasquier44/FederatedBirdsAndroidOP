package fr.sio.ecp.federatedbirds.model;

import java.util.Date;

/**
 * A model class to represent a single message.
 * This entity is inflated by Gson from the API responses.
 */
public class Message {

    public long id;
    public String text;
    public Date date;
    public User user;

}
