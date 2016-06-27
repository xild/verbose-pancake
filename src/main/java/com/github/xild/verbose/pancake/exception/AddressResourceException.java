/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 26, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.exception;

/**
 * @author Luis Vieira 
 *
 */
public class AddressResourceException extends Exception {
	private static final long serialVersionUID = 2562160459794061576L;

	public AddressResourceException() {
        super();
    }

    public AddressResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressResourceException(String message) {
        super(message);
    }

    public AddressResourceException(Throwable cause) {
        super(cause);
    }

}
