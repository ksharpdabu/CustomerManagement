package info.dabu.Exception;

/**
 * Created by AlexY on 2016/7/26.
 */
public class DaoException extends RuntimeException {

    public DaoException() {
    }


    public DaoException(String message){
        super(message);
    }


    public DaoException(Throwable e){
        super(e);
    }


    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
